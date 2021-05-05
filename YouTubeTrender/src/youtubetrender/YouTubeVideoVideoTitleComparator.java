package youtubetrender;

import java.util.Comparator;

public class YouTubeVideoVideoTitleComparator implements Comparator<YouTubeVideo> {

    @Override
    public int compare(YouTubeVideo o1, YouTubeVideo o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
