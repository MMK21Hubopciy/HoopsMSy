package youtubetrender;

import java.util.Comparator;

public class YouTubeVideoLikeComparator implements Comparator<YouTubeVideo> {

    @Override
    public int compare(YouTubeVideo o1, YouTubeVideo o2) {
        return o1.getLikeCount() - o2.getLikeCount();
    }
}
