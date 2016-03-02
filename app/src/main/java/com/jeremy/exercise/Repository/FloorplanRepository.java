package com.jeremy.exercise.Repository;


import com.jeremy.exercise.help.GsonHelper;
import com.jeremy.exercise.moudle.Asset;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jeremy on 2016/1/26.
 * Mail:jyan.lin@foxmail.com
 */
public class FloorplanRepository extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String firstName;
    private AssetRepository asset;
    private String parentId;

//    public static FloorplanRepository save(FloorplanRepository repository){
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public AssetRepository getAsset() {
        return asset;
    }

    public void setAsset(AssetRepository asset) {
        this.asset = asset;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    //保存至Realm数据库，并返回一个FloorplanRepository对象
    public static FloorplanRepository save(FloorplanRepository repository){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FloorplanRepository floorplanRepository = realm.copyToRealmOrUpdate(repository);
        realm.commitTransaction();
        return floorplanRepository;
    }

    //保存至Realm数据库，并返回一个FloorplanRepository对象
    public static FloorplanRepository save(String json){
        return save(GsonHelper.fromJson(json,FloorplanRepository.class));
    }

    public static List<FloorplanRepository> findAll() {
        return Realm.getDefaultInstance().where(FloorplanRepository.class).findAll();
    }
}
