package usaf.airshowapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

public class Database {
    private Airshow[] airshows;
    private Map<String, Message> messages;
    private Question[] questions;

    @JsonProperty("Airshows")
    public Airshow[] getAirshows() {
        return airshows;
    }

    @JsonProperty("Airshows")
    public void setAirshows(Airshow[] value) {
        this.airshows = value;
    }

    @JsonProperty("Messages")
    public Map<String, Message> getMessages() {
        return messages;
    }

    @JsonProperty("Messages")
    public void setMessages(Map<String, Message> value) {
        this.messages = value;
    }

    @JsonProperty("Questions")
    public Question[] getQuestions() {
        return questions;
    }

    @JsonProperty("Questions")
    public void setQuestions(Question[] value) {
        this.questions = value;
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
        Airshow[] Airshows = getAirshows();

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
    public String toString() {
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

