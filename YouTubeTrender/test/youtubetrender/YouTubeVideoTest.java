package youtubetrender;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class YouTubeVideoTest {


    public YouTubeVideoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    YouTubeVideo tester = new YouTubeVideo();
    YouTubeDataParser instance = new YouTubeDataParser();

    @Before
    // This is setting up a tester video with expected output like what we had.
    // It basically forms the tester for the ToString method in terms of what to expect etc. Saves some time.
    public void setUp() throws Exception {
        tester.setChannel("App Dev Tutorials For All");
        tester.setTitle("How to parse JSON data");
        tester.setID("AAABBBCCC11122233");
        tester.setDate("21-05-20");
        tester.setDescription("Supplementary video demonstrating how to parse JSON data into a Java object for App Dev");
        tester.setViewCount(666);
        tester.setLikeCount(666);
        tester.setDislikeCount(666);
        tester.setCommentCount(666);
    }

    @Test
    public void ToStringTest() {

        //Calling the toString from the YouTubeVideo class Channel + Title.
        System.out.println("toString method test");
        String expResult = "App Dev Tutorials For All: How to parse JSON data";
        String result = tester.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void GetChannelTest() {
        System.out.println("getChannel method test");
        String expResult = "App Dev Tutorials For All";
        String result = tester.getChannel();
        assertEquals(expResult, result);
    }

    @Test
    public void SetChannelTest() {
        System.out.println("setChannel method test");
        String expResult = "Carl Mooney";
        tester.setChannel("Carl Mooney");
        String result = tester.getChannel();
        assertEquals(expResult, result);
    }


    @Test
    public void GetDateTest() {
        System.out.println("getDate method test");
        String expResult = "21-05-20";
        String result = tester.getDate();
        assertEquals(expResult, result);
    }

    @Test
    public void SetDateTest() {
        System.out.println("setDate method test");
        String expResult = "12-12-12";
        tester.setDate("12-12-12");
        String result = tester.getDate();
        assertEquals(expResult, result);
    }

    @Test
    public void GetTitleTest() {
        System.out.println("getTitle method test");
        String expResult = "How to parse JSON data";
        String result = tester.getTitle();
        assertEquals(expResult, result);
    }

    @Test
    public void SetTitleTest() {
        System.out.println("setTitle method test");
        String expResult = "App Dev  very fun indeed!";
        tester.setTitle("App Dev  very fun indeed!");
        String result = tester.getTitle();
        assertEquals(expResult, result);
    }

    @Test
    public void GetDescriptionTest() {
        System.out.println("getDescription method test");
        String expResult = "Supplementary video demonstrating how to parse JSON data into a Java object for App Dev";
        String result = tester.getDescription();
        assertEquals(expResult, result);
    }

    @Test
    public void SetDescriptionTest() {
        System.out.println("setDescription method test");
        String expResult = "JoeBananasBossman5000yeet";
        tester.setDescription("JoeBananasBossman5000yeet");
        String result = tester.getDescription();
        assertEquals(expResult, result);
    }

    @Test
    public void GetViewCountTest() {
        System.out.println("getViewCount method test");
        int expResult = 666;
        int result = tester.getViewCount();
        assertEquals(expResult, result);
    }

    @Test
    public void SetViewCountTest() {
        System.out.println("setViewCount method test");
        int expResult = 69;
        tester.setViewCount(69);
        int result = tester.getViewCount();
        assertEquals(expResult, result);
    }

    @Test
    public void GetIDTest() {
        System.out.println("getID method test");
        String expResult = "AAABBBCCC11122233";
        String result = tester.getID();
        assertEquals(expResult, result);
    }

    @Test
    public void SetLikeCountTest() {
        System.out.println("setLikeCount method test");
        int expResult = 54;
        tester.setLikeCount(54);
        int result = tester.getLikeCount();
        assertEquals(expResult, result);
    }

    @Test
    public void SetDislikeCountTest() {
        System.out.println("setDislikeCount method test");
        int expResult = 54;
        tester.setDislikeCount(54);
        int result = tester.getDislikeCount();
        assertEquals(expResult, result);
    }

    @Test
    public void SetCommentCountTest() {
        System.out.println("setCommentCount method test");
        int expResult = 54;
        tester.setCommentCount(54);
        int result = tester.getCommentCount();
        assertEquals(expResult, result);
    }

    @Test
    public void GetLikeCountTest() {
        System.out.println("getLikeCount method test");
        int expResult = 666;
        int result = tester.getLikeCount();
        assertEquals(expResult, result);
    }

    @Test
    public void GetDislikeCountTest() {
        System.out.println("getDisLike method test");
        int expResult = 666;
        int result = tester.getDislikeCount();
        assertEquals(expResult, result);
    }

    @Test
    public void GetCommentCountTest() {
        System.out.println("getCommentCount method test");
        int expResult = 666;
        int result = tester.getCommentCount();
        assertEquals(expResult, result);
    }
}

