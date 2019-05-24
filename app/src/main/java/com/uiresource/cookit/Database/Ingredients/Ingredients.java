package com.uiresource.cookit.Database.Ingredients;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class Ingredients {

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeFoodId() {
        return typeFoodId;
    }

    public void setTypeFoodId(String typeFoodId) {
        this.typeFoodId = typeFoodId;
    }

    public String getPathToIcon() {
        return pathToIcon;
    }

    public void setPathToIcon(String pathToIcon) {
        this.pathToIcon = pathToIcon;
    }

    public int getRecipiesCount() {
        return recipiesCount;
    }

    public void setRecipiesCount(int recipiesCount) {
        this.recipiesCount = recipiesCount;
    }

    @PrimaryKey
    @NonNull
    public String id;

    @SerializedName("id")
    public String idServer;

    @SerializedName("name")
    public String name;

    @SerializedName("typeFoodId")
    public String typeFoodId;

    @SerializedName("pathToIcon")
    public String pathToIcon;

    @SerializedName("recipiesCount")
    public int recipiesCount;
}
