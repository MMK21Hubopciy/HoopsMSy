package youtubetrender;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class YouTubeWordItemTest {

    YouTubeWordItem WordTester;
    YouTubeWordItem WordCompareTest;
    YouTubeVideo video1, video2, video3;

    YouTubeDataParser parser;
    List<YouTubeVideo> list;
    YouTubeVideoIndexer ytv;


    @Before
    public void setUp() throws Exception {

        //Setting up the instances so I don't have to repeat this for every test.
        // Majority of the expected output comes from the _info.txt files given to us.

        WordTester = new YouTubeWordItem("loremipsum");
        video1 = new YouTubeVideo();
        WordTester.add(video1);

        WordCompareTest = new YouTubeWordItem("notloremipsum");

        video2 = new YouTubeVideo();
        video3 = new YouTubeVideo();

        WordCompareTest.add(video2);
        WordCompareTest.add(video3);

    }

    @Test
    public void AddVideoTest() {
        System.out.println("Testing for adding a video");

        YouTubeVideo video4 = new YouTubeVideo();
        WordTester.add(video4);

        int result = 2;
        int expResult = WordTester.getVideos().size();
        assertEquals(expResult, result);
    }

    @Test
    public void GetCountTest() {
        System.out.println("Testing for video count");

        int result = WordTester.getCount();
        int expResult = 1;

        assertEquals(expResult, result);
    }

    @Test
    public void GetVideosTest() {
        System.out.println("Testing getting the videos");

        int result = 1;
        // Using arbitrary values in the setup() method. Just to test if the method is actually working. Very basic.
        int expResult = WordTester.getVideos().size();
        assertEquals(expResult, result);

    }

    @Test
    public void GetVideosTest2() throws Exception {
        System.out.println("Testing getting the videos again ");
        parser = new YouTubeDataParser();
        list = parser.parse("src/data/youtubedata_15_50.json");
        ytv = new YouTubeVideoIndexer(list);
        ytv.index();
        //First word in the sorted list is 'the' and has 27 associated videos with that word.
        int expResult = 27;
        // This is returning the amount of videos that contain the first word ('the) of the sorted list.
        int result = ytv.getSortedYouTubeWordItems().get(0).getVideos().size();
        assertEquals(expResult, result);
    }

    @Test
    public void GetVideosTest3() throws Exception {
        System.out.println("Testing getVideos again.");
        parser = new YouTubeDataParser();
        list = parser.parse("src/data/youtubedata_indextest.json");
        ytv = new YouTubeVideoIndexer(list);
        ytv.index();

        //Getting the channel and video title of the first word of the sorted list.
        String result = ytv.getSortedYouTubeWordItems().get(0).getVideos().toString();
        String expResult = "[Joe Bloggs: ONE TWO TWO THREE THREE THREE]";

        assertEquals(expResult, result);
    }

    @Test
    public void GetVideosTest4() throws Exception {
        System.out.println("Testing getting the videos associated with a word at the wrong index.");
        parser = new YouTubeDataParser();
        list = parser.parse("src/data/youtubedata_15_50.json");
        ytv = new YouTubeVideoIndexer(list);
        ytv.index();
        try {
            //indexed amount of words only goes up to index of 2517.
            int result = ytv.getSortedYouTubeWordItems().get(2518).getVideos().size();
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void GetWordTest() {
        System.out.println("Testing for getting the word");
        String result = "loremipsum";

        String expResult = WordTester.getWord();

        assertEquals(expResult, result);
    }

    @Test
    public void getWordTest2() throws Exception {
        System.out.println("Testing for getting the word again using parsed data.");

        parser = new YouTubeDataParser();
        list = parser.parse("src/data/youtubedata_15_50.json");
        ytv = new YouTubeVideoIndexer(list);
        ytv.index();

        String result = "the";
        String expResult = ytv.getSortedYouTubeWordItems().get(0).getWord();

        assertEquals(result, expResult);
    }

    @Test
    public void ToStringTest() {
        System.out.println("Testing the toString");

        String result = "loremipsum[1]";
        String expResult = WordTester.toString();

        assertEquals(expResult, result);

    }

    @Test
    public void CompareToTest() {
        System.out.println("Comparing the video counts");
        int result = 1;
        //2 - 1 @ line 30.
        int expResult = WordTester.compareTo(WordCompareTest);

        assertEquals(expResult, result);

    }
}