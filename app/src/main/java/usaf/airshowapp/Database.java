package localhost3000.airshowapplication;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Airshows",
        "Messages",
        "Questions"
})
public class Database {

    @JsonProperty("Airshows")
    private static ArrayList<Airshow> airshows = null;
    @JsonProperty("Questions")
    private static ArrayList<Question> questions = null;
    @JsonProperty("Messages")
    private static ArrayList<Message> messages = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Airshows")
    public ArrayList<Airshow> getAirshows() {
        return airshows;
    }

    @JsonProperty("Airshows")
    public void setAirshows(ArrayList<Airshow> airshows) {
        this.airshows = airshows;
    }

    @JsonProperty("Questions")
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    @JsonProperty("Questions")
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonProperty("Messages")
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ArrayList<String> getAirshowNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Airshow airshow : getAirshows()) {
            if (airshow != null)
                names.add(airshow.getName());
        }
        return names;
    }

    public Airshow getAirshowInfo(String name) {
        ArrayList<Airshow> Airshows = getAirshows();

        for (Airshow air : Airshows) {
            if (air != null) {
                if (name.equals(air.getName().trim())) {
                    return air;
                }
            }
        }
        return null;
    }

    @Override
    public String toString()
    {
        ObjectMapper objMap = new ObjectMapper();
        try {
            String json = objMap.writeValueAsString(this);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public void StoreInfo(Airshow airshow) {
        AirshowInformationStorage Storage = new AirshowInformationStorage();
        Storage.setAirshowName(airshow.getName());
        Storage.setDate(airshow.getDate());
        Storage.setWebsiteLink(airshow.getWebsiteLink());
        Storage.setFacebookLink(airshow.getFacebookLink());
        Storage.setTwitterLink(airshow.getTwitterLink());
        Storage.setAbout(airshow.getDescription());
        Storage.setParking(airshow.getDirections());
        Storage.setPerformers(airshow.getPerformers());
        Storage.setStatics(airshow.getStatics());
        Storage.setFoods(airshow.getFoods());
        Storage.setSelected(airshow);
        Storage.setSponsors(airshow.getSponsors());
        Storage.setQnA(getQuestions());
        Storage.setIGLink(airshow.getIgLink());

    }

}
