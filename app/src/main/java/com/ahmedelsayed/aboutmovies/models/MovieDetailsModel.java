package com.ahmedelsayed.aboutmovies.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieDetailsModel {

    @SerializedName("vote_count")
    private int vote_count;
    @SerializedName("vote_average")
    private double vote_average;
    @SerializedName("video")
    private boolean video;
    @SerializedName("title")
    private String title;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("status")
    private String status;
    @SerializedName("spoken_languages")
    private List<Spoken_languages> spoken_languages;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("revenue")
    private int revenue;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("production_countries")
    private List<Production_countries> production_countries;
    @SerializedName("production_companies")
    private List<Production_companies> production_companies;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("overview")
    private String overview;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("original_language")
    private String original_language;
    @SerializedName("imdb_id")
    private String imdb_id;
    @SerializedName("id")
    private int id;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("genres")
    private List<Genres> genres;
    @SerializedName("budget")
    private int budget;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("adult")
    private boolean adult;

    public int getVote_count() {
        return vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public boolean getVideo() {
        return video;
    }

    public String getTitle() {
        return title;
    }

    public String getTagline() {
        return "\""+tagline+"\"";
    }

    public String getStatus() {
        return status;
    }

    public List<Spoken_languages> getSpoken_languages() {
        return spoken_languages;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getRevenue() {
        return revenue;
    }

    public String getRelease_date() {
        return release_date;
    }

    public List<Production_countries> getProduction_countries() {
        return production_countries;
    }

    public List<Production_companies> getProduction_companies() {
        return production_companies;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public int getId() {
        return id;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getOverview() {
        return overview;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public int getBudget() {
        return budget;
    }


    public String getPoster_path() {
        return "https://image.tmdb.org/t/p/w500" + poster_path;
    }

    @BindingAdapter({"bind:poster_path"})
    public static void load_poster(ImageView view, String poster_path) {
        Picasso.get()
                .load(poster_path)
                .into(view);
    }

    public String getBackdrop_path() {
        return "https://image.tmdb.org/t/p/w500" + backdrop_path;
    }

    @BindingAdapter({"bind:backdrop_path"})
    public static void load_cover(ImageView view, String backdrop_path) {
        Picasso.get()
                .load(backdrop_path)
                .into(view);
    }

    public boolean getAdult() {
        return adult;
    }

    public static class Spoken_languages {
        @SerializedName("name")
        private String name;
        @SerializedName("iso_639_1")
        private String iso_639_1;

        public String getName() {
            return name;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }
    }

    public static class Production_countries {
        @SerializedName("name")
        private String name;
        @SerializedName("iso_3166_1")
        private String iso_3166_1;

        public String getName() {
            return name;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }
    }

    public static class Production_companies {
        @SerializedName("origin_country")
        private String origin_country;
        @SerializedName("name")
        private String name;
        @SerializedName("logo_path")
        private String logo_path;
        @SerializedName("id")
        private int id;

        public String getOrigin_country() {
            return origin_country;
        }

        public String getName() {
            return name;
        }

        public String getLogo_path() {
            return logo_path;
        }

        public int getId() {
            return id;
        }
    }

    public static class Genres {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private int id;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }
}
