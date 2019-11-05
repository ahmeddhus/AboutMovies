package com.ahmedelsayed.aboutmovies.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosModel {

    @SerializedName("results")
    private List<Results> results;
    @SerializedName("id")
    private int id;

    public List<Results> getResults() {
        return results;
    }

    public int getId() {
        return id;
    }

    public static class Results {
        @SerializedName("type")
        private String type;
        @SerializedName("size")
        private int size;
        @SerializedName("site")
        private String site;
        @SerializedName("name")
        private String name;
        @SerializedName("key")
        private String key;
        @SerializedName("iso_3166_1")
        private String iso_3166_1;
        @SerializedName("iso_639_1")
        private String iso_639_1;
        @SerializedName("id")
        private String id;

        public String getType() {
            return type;
        }

        public int getSize() {
            return size;
        }

        public String getSite() {
            return site;
        }

        public String getName() {
            return name;
        }

        public String getKey() {
            return key;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public String getId() {
            return id;
        }
    }
}
