package com.jeremy.exercise;

import com.jeremy.exercise.moudle.Floorplan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by Jeremy on 2016/1/23.
 * Mail:jyan.lin@foxmail.com
 */
public interface ApiService {
    @GET("questcms/floorplan")
    Call<List<Floorplan>> listFloorplan();

    @GET("{path}")
    Call<Response> getFile(@Path("path") String path);

}
