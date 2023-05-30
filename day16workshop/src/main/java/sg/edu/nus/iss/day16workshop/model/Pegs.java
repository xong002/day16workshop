package sg.edu.nus.iss.day16workshop.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pegs implements Serializable {
    private int total_count;
    private List<Type> types;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public JsonObjectBuilder toJSON() {
        //for object that has an array within
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        //convert list of Objects to list of JsonObjects 
        List<JsonObjectBuilder> typeList = this.getTypes()
                .stream()
                .map(t -> t.toJSON())
                .collect(Collectors.toList());

        for (JsonObjectBuilder o : typeList){
            arrBuilder.add(o);
        }

        return Json.createObjectBuilder()
                .add("total_count", this.getTotal_count())
                .add("types", arrBuilder);
    }

    public static Pegs createJson(JsonObject o) {
        Pegs p = new Pegs();

        JsonNumber count = o.getJsonNumber("total_count");
        JsonArray types = o.getJsonArray("types");
                
        p.setTotal_count(count.intValue());
        
        //loop through JsonArray to convert each element from JSON to Type object, and finally return a list of <Type> so that it can be set in setTypes()
        List<Type> typesList = new LinkedList<>();
        for(int i=0; i<types.size(); i++){
            JsonObject jsonObj = types.getJsonObject(i);
            Type typeObj = Type.createJson(jsonObj);
            typesList.add(typeObj);
        }
        p.setTypes(typesList);

        return p;
    }
}
