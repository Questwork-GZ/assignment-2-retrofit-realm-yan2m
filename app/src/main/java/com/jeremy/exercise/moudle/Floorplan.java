package com.jeremy.exercise.moudle;

import com.jeremy.exercise.ApiService;
import com.jeremy.exercise.Repository.FloorplanRepository;
import com.jeremy.exercise.Repository.RepositoryHandler;
import com.jeremy.exercise.ResponseHandler;
import com.jeremy.exercise.help.GsonHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.annotations.PrimaryKey;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jeremy on 2016/1/23.
 * Mail:jyan.lin@foxmail.com
 */
public class Floorplan implements RepositoryHandler<Floorplan> {
    private String id;
    private String name;
    private String firstName;
    private Asset asset;
    private String parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    //请求web backend
    public static void loadFromWeb(final ResponseHandler handler) {
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.12:8028/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);


        Call<List<Floorplan>> floorplans = apiService.listFloorplan();
        floorplans.enqueue(new Callback<List<Floorplan>>() {
            @Override
            public void onResponse(Call<List<Floorplan>> call, Response<List<Floorplan>> response) {
                List<Floorplan> floorplanList = new ArrayList<Floorplan>();
                for (Floorplan floorplan:response.body()){
                    floorplanList.add(floorplan.savaToRepository());
                }
                handler.onSuccess(floorplanList);
            }

            @Override
            public void onFailure(Call<List<Floorplan>> call, Throwable t) {
                handler.onFail(t);
            }
        });
    }

    public String toJson() {
        return GsonHelper.toJson(this);
    }

    //保存至Realm数据库，并返回一个Floorplan对象
    @Override
    public Floorplan savaToRepository() {
        return objectTranslate(FloorplanRepository.save(this.toJson()));
    }

    //从Realm数据库中返回一个Floorplan对象
    public static Floorplan objectTranslate(FloorplanRepository repository) {
        Floorplan floorplan = new Floorplan();
        floorplan.setId(repository.getId());
        floorplan.setName(repository.getName());
        floorplan.setParentId(repository.getParentId());
        floorplan.setAsset(Asset.objectTranslate(repository.getAsset()));
        return floorplan;
    }

    public static List<Floorplan> listTranslate(List<FloorplanRepository> repositories){
        List<Floorplan> floorplans = new ArrayList<>(repositories.size());
        for (FloorplanRepository floorplanRepository : repositories ){
            floorplans.add(objectTranslate(floorplanRepository));
        }
        return floorplans;
    }

    public static List<Floorplan> findAll(){
        return listTranslate(FloorplanRepository.findAll());
    }
}
