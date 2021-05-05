/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubetrender;

import java.util.*;

/**
 *
 */
public class YouTubeVideoIndexer {

    private List<YouTubeVideo> items;
    private Map<String, YouTubeWordItem> words;

    private ArrayList<String> info = new ArrayList<>();
    private StringBuilder userTextVideoInfo;

    public YouTubeVideoIndexer(List<YouTubeVideo> items) {
        this.items = items;
    }

    public YouTubeVideoIndexer() {
    }

    public void index() {

        words = new HashMap<>();

        for (int p = 0; p < items.size(); p++) {

            YouTubeVideo item = items.get(p);
            String text = item.getTitle() + " " + item.getDescription();

            if (text != null) {
                Scanner ws = new Scanner(text);
                while (ws.hasNext()) {
                    String w = ws.next();
                    if (words.containsKey(w)) {

                        YouTubeWordItem tempWI = words.get(w);
                        tempWI.add(item);
                        words.put(w, tempWI);

                    } else {

                        YouTubeWordItem tempWI = new YouTubeWordItem(w);
                        tempWI.add(item);
                        words.put(w, tempWI);
                    }
                }
            }

        }

    }

    public List<YouTubeWordItem> getSortedYouTubeWordItems() {

        List<YouTubeWordItem> items = new ArrayList<>(words.values());

        Collections.sort(items);

        return items;
    }

    public YouTubeWordItem getWordItem(String word) {
        return words.get(word);
    }

    public int getWordCount(String word) {
        return words.get(word).getCount();
    }

    public Set<YouTubeVideo> getWordVideos(String word) {
        return words.get(word).getVideos();
    }

    public String WordSearch(String userText) throws YouTubeDataParserException {

        userTextVideoInfo = new StringBuilder();
        int i = 0;
        try {
            info = new ArrayList<>();
            // Adding each video associated with the user text input to an ArrayList of Strings.
            for (YouTubeVideo a : words.get(userText).getVideos()) {
                info.add(a.getTitle());
                // Sorting them alphabetically.
                Collections.sort(info, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareToIgnoreCase(o2);
                    }
                });
            }
            for (String a : info) {
                i++;
                userTextVideoInfo.append(i + ": ").append(a).append("\n");
                //Appending each element in the ArrayList of Strings to a String builder so they can be properly listed out.
                // Shows the video count as well.
            }

        } catch (NullPointerException | StringIndexOutOfBoundsException a) {
            System.out.println("Word does not exist. Please try again");
        }
        return userTextVideoInfo.substring(0, userTextVideoInfo.length() - 1);
    }
}

