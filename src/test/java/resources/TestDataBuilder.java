package resources;

import pojo.AddPlace;
import pojo.location;

import java.util.LinkedList;
import java.util.List;

public class TestDataBuilder{

    public AddPlace addPlacePayLoad(String name, String language, String address){
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("+91 9087843322");
        addPlace.setAddress(address);
        addPlace.setWebsite("https://sagar.com");
        addPlace.setLanguage(language);
        location location = new location();
        location.setLat(-38);
        location.setLng(33);
        List<String> types = new LinkedList<>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setLocation(location);
        addPlace.setTypes(types);



        return  addPlace;
    }

}
