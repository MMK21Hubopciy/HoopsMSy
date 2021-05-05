/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubetrender;

import javax.json.*;
import javax.json.stream.JsonParsingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class YouTubeDataParser {

    public List<YouTubeVideo> parse(String fileName) throws YouTubeDataParserException {

        List<YouTubeVideo> list = new ArrayList<>();
        JsonReader jsonReader;
        JsonValue nullFinder;

        try {
            //read data
            jsonReader = Json.createReader(new FileInputStream(fileName));
        } catch (FileNotFoundException fnfe) {
            throw new YouTubeDataParserException("File not found: " + fileName);
        }

        JsonObject jobj = null;
        try {
            jobj = jsonReader.readObject();
        } catch (JsonParsingException jpe) {
            throw new YouTubeDataParserException("Parsing exception: " + jpe.getMessage());
        }

        // read the values of the item field
        JsonArray items = jobj.getJsonArray("items");

        for (int i = 0; i < items.size(); i++) {
            JsonObject video = items.getJsonObject(i);
            JsonObject snippet = video.getJsonObject("snippet");

            // parsing to Youtube video
            String channelId = snippet.getString("channelId");
            String channelTitle = snippet.getString("channelTitle");
            String publishAt = snippet.getString("publishedAt");
            String title = snippet.getString("title");
            String description = snippet.getString("description");

            // parsing statistics
            JsonObject statistics = video.getJsonObject("statistics");
            int viewCount = Integer.valueOf(statistics.getString("viewCount"));


            YouTubeVideo ytv = new YouTubeVideo(channelTitle, publishAt, title, description, viewCount, channelId);

            // extra features for sorting (implemented in phase 2). Part of 'How to get HD'.
            // likes, dislikes and comment count.

            nullFinder = statistics.get("likeCount");
            if (nullFinder == null) {
                ytv.setLikeCount(0);
            } else {
                ytv.setLikeCount(Integer.parseInt(statistics.getString("likeCount")));
            }
            nullFinder = statistics.get("dislikeCount");
            if (nullFinder == null) {
                ytv.setDislikeCount(0);
            } else {
                ytv.setDislikeCount(Integer.parseInt(statistics.getString("dislikeCount")));
            }
            nullFinder = statistics.get("commentCount");
            if (nullFinder == null) {
                ytv.setCommentCount(0);
            } else {
                ytv.setCommentCount(Integer.parseInt(statistics.getString("commentCount")));
            }
            list.add(ytv);
        }
        return list;
    }
}
