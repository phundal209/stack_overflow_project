package com.example.phundal.stackoverflow.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class UserProfileModel extends RealmObject {
    @SerializedName("badge_counts")
    private BadgeCounts badgeCounts;

    @SerializedName("reputation")
    private long reputation;

    @SerializedName("display_name")
    private String display_name;

    @SerializedName("profile_image")
    private String profile_image;

    @SerializedName("link")
    private String link;

    @SerializedName("age")
    private int age;

    public BadgeCounts getBadgeCounts() {
        return badgeCounts;
    }

    public void setBadgeCounts(BadgeCounts badgeCounts) {
        this.badgeCounts = badgeCounts;
    }

    public long getReputation() {
        return reputation;
    }

    public void setReputation(long reputation) {
        this.reputation = reputation;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
