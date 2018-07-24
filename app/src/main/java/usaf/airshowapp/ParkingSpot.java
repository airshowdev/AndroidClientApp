
package localhost3000.airshowapplication;

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
    "Full",
    "Name",
    "X-Coord",
    "Y-Coord"
})
public class ParkingSpot {

    @JsonProperty("Full")
    private Boolean full;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("X-Coord")
    private Double xCoord;
    @JsonProperty("Y-Coord")
    private Double yCoord;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("Last Updated By")
    private String updatedBy;

    @JsonProperty("Last Updated By")
    private void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    @JsonProperty("Full")
    public Boolean getFull() {
        return full;
    }

    @JsonProperty("Full")
    public void setFull(Boolean full) {
        this.full = full;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("X-Coord")
    public Double getXCoord() {
        return xCoord;
    }

    @JsonProperty("X-Coord")
    public void setXCoord(Double xCoord) {
        this.xCoord = xCoord;
    }

    @JsonProperty("Y-Coord")
    public Double getYCoord() {
        return yCoord;
    }

    @JsonProperty("Y-Coord")
    public void setYCoord(Double yCoord) {
        this.yCoord = yCoord;
    }

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Type")
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
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
