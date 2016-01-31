package com.jeremy.exercise.help;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import io.realm.RealmObject;

/**
 * Created by gd on 2015/8/13.
 */
public class GsonHelper {
    private static Type token = new TypeToken<List<String>>() {
    }.getType();
    private static Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getDeclaringClass().equals(RealmObject.class);
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    }).setDateFormat(DateFormat.LONG).registerTypeAdapter(Date.class, new DateTypeAdapter()).registerTypeAdapter(token, new JsonSerializer<List<String>>() {
        @Override
        public JsonElement serialize(List<String> src, Type typeOfSrc, JsonSerializationContext context) {
            JsonArray array = new JsonArray();
            for (String str : src) {
                JsonObject object = new JsonObject();
                object.addProperty("value", str);
                array.add(object);
            }
            return array;
        }
    }).create();

//    public static Gson getDefaultGson() {
//        return gson;
//    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(JsonElement element, Class<T> classOfT) {
        return gson.fromJson(element, classOfT);
    }

}
