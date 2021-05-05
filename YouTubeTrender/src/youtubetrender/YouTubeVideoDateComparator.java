/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubetrender;

import java.util.Comparator;

/**
 *
 * 
 */
public class YouTubeVideoDateComparator implements Comparator<YouTubeVideo> {

    @Override
    public int compare(YouTubeVideo o1, YouTubeVideo o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
