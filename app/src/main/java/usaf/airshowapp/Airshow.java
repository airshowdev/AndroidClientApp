
package localhost3000.airshowapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.io.FileUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Base",
    "Date",
    "Directions",
    "Foods",
    "Name",
    "Performers",
    "Statics"
})
public class Airshow {

    @JsonProperty("Base")
    private String base;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Directions")
    private ArrayList<ParkingSpot> directions = null;
    @JsonProperty("Foods")
    private ArrayList<Food> foods = null;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Performers")
    private ArrayList<Performer> performers = null;
    @JsonProperty("Statics")
    private ArrayList<Static> statics = null;
    @JsonProperty("Facebook Link")
    private String facebookLink;
    @JsonProperty("Twitter Link")
    private String twitterLink;
    @JsonProperty("Website Link")
    private String websiteLink;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Instagram Link")
    private String igLink;


    @JsonIgnore
    private ArrayList<Bitmap> perfBitmaps, staticBitmaps;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Last Updated By")
    private String updatedBy;

    @JsonProperty("Last Updated By")
    private void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }


    public ArrayList<Bitmap> getStaticBitmaps() {
        return staticBitmaps;
    }

    public ArrayList<Bitmap> getPerfBitmaps() {
        return perfBitmaps;
    }

    public void setPerfBitmaps(ArrayList<Bitmap> perfBitmaps) {
        this.perfBitmaps = perfBitmaps;
    }

    public void setStaticBitmaps(ArrayList<Bitmap> staticBitmaps) {
        this.staticBitmaps = staticBitmaps;
    }

    @JsonProperty("Instagram Link")
    public String getIgLink(){return igLink;}

    @JsonProperty("Instagram Link")
    public void setIgLink(String igLink){this.igLink = igLink;}

    @JsonProperty("Base")
    public String getBase() {
        return base;
    }

    @JsonProperty("Base")
    public void setBase(String base) {
        this.base = base;
    }

    @JsonProperty("Date")
    public String getDate() {
        return date;
    }

    @JsonProperty("Date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("Directions")
    public ArrayList<ParkingSpot> getDirections() {
        return directions;
    }

    @JsonProperty("Directions")
    public void setDirections(ArrayList<ParkingSpot> directions) {
        this.directions = directions;
    }

    @JsonProperty("Foods")
    public ArrayList<Food> getFoods() {
        return foods;
    }

    @JsonProperty("Foods")
    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Performers")
    public ArrayList<Performer> getPerformers() {
        return performers;
    }

    @JsonProperty("Performers")
    public void setPerformers(ArrayList<Performer> performers) {
        this.performers = performers;
    }

    @JsonProperty("Statics")
    public ArrayList<Static> getStatics() {
        return statics;
    }

    @JsonProperty("Statics")
    public void setStatics(ArrayList<Static> statics) {
        this.statics = statics;
    }

    @JsonProperty("Facebook Link")
    public String getFacebookLink() {
        return facebookLink;
    }

    @JsonProperty("Facebook Link")
    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    @JsonProperty("Twitter Link")
    public String getTwitterLink() {
        return twitterLink;
    }

    @JsonProperty("Twitter Link")
    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    @JsonProperty("Website Link")
    public String getWebsiteLink() {
        return websiteLink;
    }

    @JsonProperty("Website Link")
    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Sponsors")
    private String sponsors;

    @JsonProperty("Sponsors")
    public String getSponsors()
    {
        return sponsors;
    }

    public void setSponsors(String sponsors)
    {
        this.sponsors = sponsors;
    }

    @JsonProperty("Last Update")
    private String lastupdate;

    @JsonProperty("Last Update")
    public String getLastupdate()
    {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate)
    {
        this.lastupdate = lastupdate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
