package com.seidelsoft.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class JsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> Object fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static JsonObject toJsonObject(String json, Class<JsonObject> clazz) {
        return gson.fromJson(json, clazz);
    }
}
