package info.androidhive.customlistviewvolley.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ahnjeongki on 2016. 1. 23..
 */
public class Music {

    private String title, thumbnailUrl;
    private String artist;
    private int ranking;
    private ArrayList<String> genre;

    public Music() {
    }

    public Music(String name, String thumbnailUrl, String artist, int ranking,
                 ArrayList<String> genre) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.artist = artist;
        this.ranking = ranking;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getRank() {
        return ranking;
    }

    public void setRank(int ranking) {
        this.ranking = ranking;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

}


