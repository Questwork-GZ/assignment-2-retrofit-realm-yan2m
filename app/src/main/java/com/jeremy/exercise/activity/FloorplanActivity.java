package com.jeremy.exercise.activity;

import android.app.Activity;
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
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jeremy on 2016/1/26.
 * Mail:jyan.lin@foxmail.com
 */
public class FloorplanActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplan);
        Floorplan.loadFromWeb(new ResponseHandler<List<Floorplan>>() {

            @Override
            public void onSuccess(List<Floorplan> floorplans) {
                for (Floorplan floorplan : floorplans){
                    Log.e("name:",floorplan.getName());
                    Log.e("asset:", floorplan.getAsset().getFullpath());
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }

    public String getFileName(String fullPath) {
        String[] path = fullPath.split("/");
        return path[path.length - 1];
    }
}
