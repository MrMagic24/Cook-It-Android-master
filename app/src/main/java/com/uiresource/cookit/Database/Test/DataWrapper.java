package com.uiresource.cookit.Database.Test;

import com.google.gson.*;

public class DataWrapper {
    public Data data;

    public DataWrapper kek(String s) {
        return new Gson().fromJson(s, DataWrapper.class);
    }
    /*public String toString() {
        return new Gson().toJson(this);
    }*/
}
