package com.jeremy.exercise.activity;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jeremy.exercise.ApiService;
import com.jeremy.exercise.R;
import com.jeremy.exercise.Repository.AssetRepository;
import com.jeremy.exercise.Repository.FloorplanRepository;
import com.jeremy.exercise.ResponseHandler;
import com.jeremy.exercise.help.GsonHelper;
import com.jeremy.exercise.moudle.Asset;
import com.jeremy.exercise.moudle.Floorplan;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Jeremy on 2016/1/26.
 * Mail:jyan.lin@foxmail.com
 */
public class FloorplanActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);

            //Retrofit
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.2:8028/").addConverterFactory(GsonConverterFactory.create()).build();
            ApiService apiService = retrofit.create(ApiService.class);



            Call<List<Floorplan>> floorplans = apiService.listFloorplan();
            floorplans.enqueue(new Callback<List<Floorplan>>() {
                @Override
                public void onResponse(Response<List<Floorplan>> response) {

//                    for (int i = 0; i < response.body().size(); i++) {
//                        Realm realm = Realm.getDefaultInstance();
//                        realm.beginTransaction();
//                        FloorplanRepository floorplanRepository = realm.createOrUpdateObjectFromJson(FloorplanRepository.class, GsonHelper.toJson(response.body().get(i)));
//                        realm.commitTransaction();
//                    }
                    for (Floorplan floorplan:response.body()){
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        FloorplanRepository floorplanRepository = realm.createOrUpdateObjectFromJson(FloorplanRepository.class,
                                GsonHelper.toJson(floorplan));
                        realm.commitTransaction();
                    }
                    List<FloorplanRepository> list = Realm.getDefaultInstance().where(FloorplanRepository.class).findAll();
                    String name = list.get(0).getName();
                    Log.e("name:",name);
                    String path = list.get(0).getAsset().getFullpath(); //is NULL
                    Log.e("path:",path);
//                    List<AssetRepository> assetRepositoryList = Realm.getDefaultInstance().where(AssetRepository.class).findAll();
//                    String path = assetRepositoryList.get(0).getFullpath();//why == null??
//                    Log.e("path",path);
                    Toast.makeText(FloorplanActivity.this, "Success!", Toast.LENGTH_LONG).show();
                }


                @Override
                public void onFailure(Throwable t) {

                }
            });
//        } else if (TEST_TYPE == 1) {
//            Floorplan.loadFromWeb(new ResponseHandler<List<Floorplan>>() {
//                                      @Override
//                                      public void onSuccess(List<Floorplan> object) {
//                                          for (final Floorplan floorplan : object) {
//                                                      Asset.loadFromWeb(floorplan.getAsset().getFullpath().substring(1), new ResponseHandler<Response>() {
//                                                          @Override
//                                                          public void onSuccess(Response object) {
////                                    Asset.saveToLocal(this,getFileName(floorplan.getAsset().getFullpath()),((bytearra)object.body().getBytes()));
//                                                          }
//
//                                                          @Override
//                                                          public void onFail(Throwable t) {
//
//                                                          }
//                                                      });
//                                              List<AssetRepository> assetRepositoryList = Realm.getDefaultInstance().where(AssetRepository.class).findAll();
//                                              String path = assetRepositoryList.get(0).getFullpath();
//                                          }
//                                      }
//
//                                      @Override
//                                      public void onFail(Throwable t) {
//
//                                      }
//                                  }
//            );

        }

    public String getFileName(String fullPath) {
        String[] path = fullPath.split("/");
        return path[path.length - 1];
    }
}
