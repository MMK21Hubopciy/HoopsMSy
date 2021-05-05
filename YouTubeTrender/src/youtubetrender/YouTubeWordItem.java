package youtubetrender;

import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 */
public class YouTubeWordItem implements Comparable<YouTubeWordItem> {

    private String word;
    private int count;
    private Set<YouTubeVideo> videos;

    public YouTubeWordItem(String word) {
        this.word = word;
        count = 0;
        videos = new HashSet<YouTubeVideo>();
    }

    public YouTubeWordItem() {
    }

    public void add(YouTubeVideo t) {
        count++;
        videos.add(t);
    }

    public int getCount() {
        return count;
    }

    public Set<YouTubeVideo> getVideos() {

        return videos;
    }

    public String getWord() {
        return word;
    }

    public String toString() {
        return word + "[" + count + "]";
    }

    public int compareTo(YouTubeWordItem t) {
        return t.count - this.count;
    }
}
