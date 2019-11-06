package com.ahmedelsayed.aboutmovies.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditsModel {

    @SerializedName("crew")
    private List<Crew> crew;
    @SerializedName("cast")
    private List<Cast> cast;
    @SerializedName("id")
    private int id;

    public List<Crew> getCrew() {
        return crew;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public int getId() {
        return id;
    }

    public static class Crew {
        @SerializedName("profile_path")
        private String profile_path;
        @SerializedName("name")
        private String name;
        @SerializedName("job")
        private String job;
        @SerializedName("id")
        private int id;
        @SerializedName("gender")
        private int gender;
        @SerializedName("department")
        private String department;
        @SerializedName("credit_id")
        private String credit_id;

        public String getProfile_path() {
            return profile_path;
        }

        public String getName() {
            return name;
        }

        public String getJob() {
            return job;
        }

        public int getId() {
            return id;
        }

        public int getGender() {
            return gender;
        }

        public String getDepartment() {
            return department;
        }

        public String getCredit_id() {
            return credit_id;
        }
    }

    public static class Cast {
        @SerializedName("profile_path")
        private String profile_path;
        @SerializedName("order")
        private int order;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private int id;
        @SerializedName("gender")
        private int gender;
        @SerializedName("credit_id")
        private String credit_id;
        @SerializedName("character")
        private String character;
        @SerializedName("cast_id")
        private int cast_id;

        public String getProfile_path() {
            return profile_path;
        }

        public int getOrder() {
            return order;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getGender() {
            return gender;
        }

        public String getCredit_id() {
            return credit_id;
        }

        public String getCharacter() {
            return character;
        }

        public int getCast_id() {
            return cast_id;
        }
    }
}
