package com.jeremy.exercise.moudle;

import android.content.Context;

import com.jeremy.exercise.ApiService;
import com.jeremy.exercise.Repository.AssetRepository;
import com.jeremy.exercise.ResponseHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Jeremy on 2016/1/23.
 * Mail:jyan.lin@foxmail.com
 */
public class Asset {
    private long timeStamp;
    private String fullpath;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

//    public Asset(long timeStamp, String fullpath) {
//        this.timeStamp = timeStamp;
//        this.fullpath = fullpath;
//    }


    //返回Asset对象
    public static Asset objectTranslate(AssetRepository repository){
//        if (repository != null){
//            return new Asset(repository.getTimeStamp(),repository.getFullpath());
//        }
//        return null;
        if (repository != null){
            Asset asset = new Asset();
            asset.setFullpath(repository.getFullpath());
            asset.setTimeStamp(repository.getTimeStamp());
            return asset;
        }
        return null;
    }

    //下载文件
//    public static void loadFromWeb(String url, final ResponseHandler<Response> handler){
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://mobile.apaophth.org:8028/").build();
//        ApiService apiService = retrofit.create(ApiService.class);
//        Call<Response> response = apiService.getFile(url);
//        response.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Response<Response> response) {
//                handler.onSuccess(response);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                t.printStackTrace();
//                handler.onFail(t);
//            }
//        });
//    }

    //保存文件到本地
    public static void saveToLocal(Context context,String fileName, byte[] file) throws IOException {
        FileOutputStream stream = null;
        try{
            stream = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            stream.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (stream != null){
                stream.flush();
                stream.close();
            }
        }
    }
}
