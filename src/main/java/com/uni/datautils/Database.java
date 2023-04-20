package com.uni.datautils;

import java.util.HashMap;

public class Database {
    private static HashMap<String, DatabaseStore> database = null;
    public static HashMap getDatabase(){
        if(database == null){
            database = new HashMap<>();
            generateDatabase();
        }
        return database;
    }

    private Database(){}

    private static void generateDatabase(){
        for(int i = 0;i<100;i++){
            database.put(String.valueOf(i), new DatabaseStore());
        }
    }
}
