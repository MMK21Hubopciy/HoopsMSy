/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubetrender;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Includes tests for the YouTubeDataParser and YouTubeDataParserException class.
 */

public class YouTubeDataParserTest {

    YouTubeDataParser instance;

    public YouTubeDataParserTest() {
    }

    @Before
    public void setUp() {
        instance = new YouTubeDataParser();
    }

    @Test
    public void parserMethodTest() throws Exception {
        System.out.println("Testing for file parsing");
        String filename = "src/data/youtubedata_15_50.json";
        int expResult = 50;
        List<YouTubeVideo> videos = instance.parse(filename);

        int result = videos.size();
        try {
            instance.parse(filename);
        } catch (YouTubeDataParserException e) {
        }
        assertEquals(expResult, result);
    }

    @Test
    public void malformedJSONFileTest() throws Exception {
        System.out.println("Testing for a malformed JSON file");
        String filename = "src/data/youtubedata_malformed.json";
        try {
            instance.parse(filename);
        } catch (YouTubeDataParserException e) {
            assertTrue(true);
        }
    }

    @Test
    public void fileNameErrorTest() throws Exception {

        System.out.println("Testing for incorrect filename");
        String filename = "src/data/youtubedata_yehaw.json";
        try {
            instance.parse(filename);
        } catch (YouTubeDataParserException e) {
            assertTrue(true);
        }
    }

    @Test
    public void SingleItemVideoErrorTest() throws Exception {

        System.out.println("Testing for an error (null pointer exception) in a single video");
        String filename = "src/data/youtubedata_singleitem.json";
        try {
            instance.parse(filename);
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }
}
