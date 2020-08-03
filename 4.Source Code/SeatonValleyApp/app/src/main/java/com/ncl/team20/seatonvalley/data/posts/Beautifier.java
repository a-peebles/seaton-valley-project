package com.ncl.team20.seatonvalley.data.posts;

import org.jsoup.Jsoup;

/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * <p>
 * This class is used to beautify the title and description of the post's.
 * Removes HTLM attributes, limits the description length to 100 characters
 * and the title to 45,and encodes to a readable format.
 */
public class Beautifier {

    private final String title;
    private final String description;

    public Beautifier(String title, String description) {

        title = Jsoup.parse(title).text();

        description = description.replace("<p>", "");
        description = description.replace("</p>", "");
        description = description.replace("[&hellip;]", "");
        description = description.replace("&nbsp;", "");
        description = Jsoup.parse(description).text();

        if (description.length() > 100) {
            description = description.substring(0, 100) + "...";
        }

        if (title.length() > 45) {
            title = title.substring(0, 45) + "...";
        }
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
