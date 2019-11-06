package com.ahmedelsayed.aboutmovies.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeopleModel {

    @SerializedName("imdb_id")
    private String imdb_id;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("profile_path")
    private String profile_path;
    @SerializedName("place_of_birth")
    private String place_of_birth;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("biography")
    private String biography;
    @SerializedName("gender")
    private int gender;
    @SerializedName("also_known_as")
    private List<String> also_known_as;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;
    @SerializedName("known_for_department")
    private String known_for_department;
    @SerializedName("birthday")
    private String birthday;

    public String getImdb_id() {
        return imdb_id;
    }

    public boolean getAdult() {
        return adult;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getBiography() {
        return biography;
    }

    public int getGender() {
        return gender;
    }

    public List<String> getAlso_known_as() {
        return also_known_as;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public String getBirthday() {
        return birthday;
    }
}
