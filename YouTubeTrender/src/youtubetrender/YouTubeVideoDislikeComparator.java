package youtubetrender;

import java.util.Comparator;

public class YouTubeVideoDislikeComparator implements Comparator<YouTubeVideo> {

    @Override
    public int compare(YouTubeVideo o1, YouTubeVideo o2) {
        return o1.getDislikeCount() - o2.getDislikeCount();
    }
}




