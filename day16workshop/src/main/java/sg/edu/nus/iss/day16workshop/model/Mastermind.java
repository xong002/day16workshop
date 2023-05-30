package sg.edu.nus.iss.day16workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Mastermind implements Serializable {
    private String name;
    private Pieces pieces;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pieces getPieces() {
        return pieces;
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }

    public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder()
                .add("name", this.getName())
                .add("pegs", this.getPieces().toJSON());
    }

    public static Mastermind createJson(JsonObject o) {
        Mastermind m = new Mastermind();
        String name = o.getString("name");
        JsonObject pieces = o.getJsonObject("pieces");

        m.setName(name);
        m.setPieces(Pieces.createJson(pieces));
        return m;
    }

}
