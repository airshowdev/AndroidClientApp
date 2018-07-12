package localhost3000.airshowapplication;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by USAF_Admin on 7/10/2018.
 */

public class Message {

    private String body;
    private String date;
    private String id;
    private String title;
    private String uid;

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String value) {
        this.body = value;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String value) {
        this.date = value;
    }

    @JsonProperty("id")
    public String getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(String value) {
        this.id = value;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.title = value;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String value) {
        this.uid = value;
    }


}
