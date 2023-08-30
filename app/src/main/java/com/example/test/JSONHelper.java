package com.example.test;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.util.List;

public class JSONHelper {
    private final static String FILE_NAME = "coordinates.json";
    private static boolean exportToJSON(Context context, List<Data> dataList){
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setData(dataList);
        String jsonString = gson.toJson(dataItems);
        try(FileOutputStream fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)){
            fileOutputStream.write(jsonString.getBytes());
            return true;
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return false;
    }
    private static class DataItems{
        List<Data> data;

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }
    }
}
