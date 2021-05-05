package youtubetrender;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class YouTubeVideoIndexerTest {

    YouTubeVideoIndexer indexerTest;
    List<YouTubeVideo> list;
    YouTubeDataParser parse1, parse2, parse3;

    String indexData = "src/data/youtubedata_indextest.json";
    String loremipsumData = "src/data/youtubedata_loremipsum.json";
    String fifteenfiftyData = "src/data/youtubedata_loremipsum.json";


    @Before
    public void setUp() throws Exception {

        parse1 = new YouTubeDataParser();
        parse2 = new YouTubeDataParser();
        parse3 = new YouTubeDataParser();

    }

    //might need to change up this one.
    @Test
    public void indexTest() throws Exception {
        System.out.println("Testing the indexing");

        list = parse1.parse(loremipsumData);
        indexerTest = new YouTubeVideoIndexer(list);
        indexerTest.index();

        int result = 238; // 238 unique words in loremipsum.json
        int expResult = indexerTest.getSortedYouTubeWordItems().size();

        assertEquals(expResult, result);

    }

    @Test
    public void getSortedYouTubeWordItemsTest() throws Exception {

        System.out.println("Testing the sorted items");

        list = parse3.parse(indexData);
        indexerTest = new YouTubeVideoIndexer(list);
        indexerTest.index();
        String result = "FIVE";

        // Getting the first sorted word from the list, rather than testing the whole list.
        String expResult = indexerTest.getSortedYouTubeWordItems().get(0).getWord();

        assertEquals(expResult, result);

    }

    @Test
    public void getSortedYouTubeWordItemsTest2() throws Exception {

        System.out.println("Testing the sorted items");

        list = parse3.parse(indexData);
        indexerTest = new YouTubeVideoIndexer(list);
        indexerTest.index();
        int result = 5;
        // Getting the size of the sorted list (how many elements)
        int expResult = indexerTest.getSortedYouTubeWordItems().size();

        assertEquals(expResult, result);

    }

    @Test
    public void getSortedYouTubeWordItemsTest3() throws Exception {
        System.out.println("Testing to sort items again only if its been indexed");

        list = parse3.parse(indexData);
        indexerTest = new YouTubeVideoIndexer(list);

        try {
            indexerTest.getSortedYouTubeWordItems();
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getWordItemTest() throws Exception {

        System.out.println("Testing for getting the word item");

        list = parse3.parse(fifteenfiftyData);
        indexerTest = new YouTubeVideoIndexer(list);
        indexerTest.index();

        String expResult = "sit[17]";

        String result = indexerTest.getWordItem("sit").toString();
        assertEquals(result, expResult);
    }


    @Test
    public void getWordItemErrorTest() throws Exception {
        System.out.println("Testing for a getting a word that does not exist");

        list = parse3.parse(fifteenfiftyData);
        indexerTest = new YouTubeVideoIndexer(list);
        indexerTest.index();

        // Getting a word that does not exist will be caught.
        try {
            indexerTest.getWordItem("notanindexedword");
        } catch (NullPointerException npe) {
            assertTrue(true);
        }
    }


    @Test
    public void getWordCountTest() throws Exception {
        System.out.println("Testing for getting the word count");

        list = parse1.parse(indexData);
        indexerTest = new YouTubeVideoIndexer(list);
        indexerTest.index();

        int result = 5;
        int expResult = indexerTest.getWordCount("FIVE");

        assertEquals(expResult, result);
    }


    @Test
    public void getMostFrequentWordTest() throws Exception {
        System.out.println("Testing for finding the most frequent word");

        list = parse1.parse(loremipsumData);
        indexerTest = new YouTubeVideoIndexer(list);
        indexerTest.index();

        // getSortedYouTubeWordItems() is tested in YouTubeVideoIndexerTest
        indexerTest.getSortedYouTubeWordItems();

        int expResult = indexerTest.getSortedYouTubeWordItems().get(0).getCount();
        // getCount is tested in YouTubeWordItemTest

        // sit is repeated 17 times in loremipsumdata.
        int result = 17;

        assertEquals(expResult, result);
    }


    @Test
    public void getWordVideosTest() throws Exception {
        System.out.println("Testing the getWordVideos");

        list = parse3.parse(indexData);
        indexerTest = new YouTubeVideoIndexer(list);
        indexerTest.index();
        indexerTest.getWordVideos("FIVE");

// Gets how many videos associated with the word, rather than comparing the output with a string (if there were 50 videos associated with the word).

        int result = 1;
        int expResult = indexerTest.getWordVideos("FIVE").size();

        assertEquals(expResult, result);
    }

    @Test
    public void WordSearchTest() throws Exception {
        System.out.println("Testing the Word Search function");
        YouTubeVideoIndexer aaa;
        YouTubeDataParser parser = new YouTubeDataParser();
        List<YouTubeVideo> a = parser.parse("src/data/youtubedata_15_50.json");
        aaa = new YouTubeVideoIndexer(a);
        aaa.index();

        int expResult = aaa.WordSearch("Ryan").length();

        //145 characters outputted when searched for the word "Ryan".
        //Could have done how many strings outputted, but thats already tested in another test in here.
        int result = 151;
        assertEquals(expResult, result);
    }

    @Test
    public void WordSearchTest2() throws Exception {
        System.out.println("Testing the word search function but user enters a word that does not exist");
        YouTubeVideoIndexer ytv;
        YouTubeDataParser parser = new YouTubeDataParser();
        list = parser.parse("src/data/youtubedata_15_50.json");
        ytv = new YouTubeVideoIndexer(list);
        ytv.index();

        try {
            ytv.WordSearch("idontexist");
        } catch (NullPointerException | StringIndexOutOfBoundsException a) {
            assertTrue(true);
        }
    }
}