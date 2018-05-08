package com.example.phundal.stackoverflow.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by phundal on 5/7/18.
 */

public class BadeCounts extends RealmObject {
    @SerializedName("bronze")
    private long bronze;

    @SerializedName("silver")
    private long silver;

    @SerializedName("gold")
    private long gold;

    public long getBronze() {
        return bronze;
    }

    public void setBronze(long bronze) {
        this.bronze = bronze;
    }

    public long getSilver() {
        return silver;
    }

    public void setSilver(long silver) {
        this.silver = silver;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }
}
