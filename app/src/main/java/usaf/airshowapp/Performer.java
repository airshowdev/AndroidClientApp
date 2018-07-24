
package localhost3000.airshowapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Description",
    "Image",
    "In Air",
    "Name"
})
public class Performer extends Object {

    @JsonProperty("Description")
    private String description;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("In Air")
    private String inAir;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Order Number")
    private int orderIndex;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Last Updated By")
    private String updatedBy;

    @JsonProperty("Last Updated By")
    private void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    @JsonIgnore
    private Bitmap bitmap;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @JsonProperty("Order Number")
    public int getOrderIndex()
    {
        return orderIndex;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Image")
    public String getImage() {
        return image;
    }

    @JsonProperty("Image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("In Air")
    public String getInAir() {
        return inAir;
    }

    @JsonProperty("In Air")
    public void setInAir(String inAir) {
        this.inAir = inAir;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
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
