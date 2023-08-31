package com.example.test;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JSONHelper {
    public final static String FILE_NAME = "data.json";
    public static boolean exportToJSON(Context context, List<Data> dataList){
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

    static List<Data> importFromJSON(Context context) {

        try(FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return dataItems.getData();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
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
