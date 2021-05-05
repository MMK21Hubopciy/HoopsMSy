package youtubetrender;

import java.util.Comparator;

public class YouTubeVideoCommentComparator implements Comparator<YouTubeVideo> {

    @Override
    public int compare(YouTubeVideo o1, YouTubeVideo o2) {
        return o1.getCommentCount() - o2.getCommentCount();
    }
}
