package youtubetrender;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class YouTubeVideoComparatorTest {

    YouTubeDataParser instance = new YouTubeDataParser();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SortByChannelTitleTest() throws Exception {
        System.out.println("Testing for sorting by Channel Title");
        String filename = "src/data/youtubedata_15_50.json";

        List<YouTubeVideo> videos = instance.parse(filename);
        videos.sort(new YouTubeVideoChannelComparator());

        //Getting the first video from the sorted list.
        String result = videos.get(0).getChannel();
        String expResult = "AllHamishandAndyVids"; // As per the _info.txt file for 15_15 - this is the expected result.

        assertEquals(expResult, result);
    }

    @Test
    public void SortByDateTest() throws Exception {
        System.out.println("Testing for sorting by Date of publication");
        String filename = "src/data/youtubedata_15_50.json";

        List<YouTubeVideo> videos = instance.parse(filename);
        videos.sort(new YouTubeVideoDateComparator());

        //Getting the first video from the sorted list.
        String result = videos.get(0).getDate();
        String expResult = "2008-02-11T22:59:52.000Z"; //As per the _info.txt file for 15_15 - this is the expected result.

        assertEquals(expResult, result);
    }

    @Test
    public void SortByDescriptionLengthTest() throws Exception {
        System.out.println("Testing for sorting by Description length");
        String filename = "src/data/youtubedata_15_50.json";

        List<YouTubeVideo> videos = instance.parse(filename);
        videos.sort(new YouTubeVideoDescriptionComparator());

        //Getting the first video from the sorted list.
        int result = videos.get(0).getDescription().length();
        int expResult = 0; // As per _15_50_info.txt

        assertEquals(expResult, result);
    }


    @Test
    public void SortByViewCountTest() throws Exception {
        System.out.println("Testing for sorting by View Count");
        String filename = "src/data/youtubedata_15_50.json";

        List<YouTubeVideo> videos = instance.parse(filename);
        videos.sort(new YouTubeVideoViewComparator());

        int result = videos.get(0).getViewCount();
        int expResult = 4406; // As per the _info.txt file for 15_15 - this is the expected result.
        assertEquals(expResult, result);

    }

    // Extra Sorting below [how to get hd part]

    @Test
    public void SortByVideoTitleTest() throws Exception {
        System.out.println("Testing for sorting by Video Title");
        String filename = "src/data/youtubedata_15_50.json";

        List<YouTubeVideo> videos = instance.parse(filename);
        videos.sort(new YouTubeVideoVideoTitleComparator());

        //Getting the first video from the sorted list.
        String result = videos.get(0).getTitle();
        String expResult = "06 19 15   Hummingbird Tormenting Rex"; // This is an extra sorting feature that was not required for in phase 2.

        assertEquals(expResult, result);
    }

    @Test
    public void SortByLikeCountTest() throws Exception {
        System.out.println("Testing for sorting by number of likes");
        String filename = "src/data/youtubedata_15_50.json";
        List<YouTubeVideo> videos = instance.parse(filename);
        videos.sort(new YouTubeVideoLikeComparator());
        int result = videos.get(0).getLikeCount();
        int expResult = 0;
        assertEquals(expResult, result);
    }

    @Test
    public void SortByDislikeCountTest() throws Exception {
        System.out.println("Testing for sorting by number of dislikes");
        String filename = "src/data/youtubedata_15_50.json";

        List<YouTubeVideo> videos = instance.parse(filename);
        videos.sort(new YouTubeVideoLikeComparator());

        //Getting the first video from the sorted list.
        int result = videos.get(0).getDislikeCount();
        int expResult = 0;

        assertEquals(expResult, result);
    }

    @Test
    public void SortByCommentCountTest() throws Exception {
        System.out.println("Testing for sorting by number of dislikes");
        String filename = "src/data/youtubedata_15_50.json";

        List<YouTubeVideo> videos = instance.parse(filename);
        videos.sort(new YouTubeVideoLikeComparator());

        //Getting the first video from the sorted list.
        int result = videos.get(0).getCommentCount();
        int expResult = 18;

        assertEquals(expResult, result);
    }
}