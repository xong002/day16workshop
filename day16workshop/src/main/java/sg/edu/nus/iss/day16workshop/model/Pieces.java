package sg.edu.nus.iss.day16workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pieces implements Serializable {
    
    private DecodingBoard decodingBoard;
    private Pegs pegs;
    private Rulebook rulebook;

    public DecodingBoard getDecodingBoard() {
        return decodingBoard;
    }

    public void setDecodingBoard(DecodingBoard decodingBoard) {
        this.decodingBoard = decodingBoard;
    }

    public Pegs getPegs() {
        return pegs;
    }

    public void setPegs(Pegs pegs) {
        this.pegs = pegs;
    }

    public Rulebook getRulebook() {
        return rulebook;
    }

    public void setRulebook(Rulebook rulebook) {
        this.rulebook = rulebook;
    }

    public JsonObjectBuilder toJSON() {
        
        //value parameter can be JSONObject, JSONArray, JSONNumber, JSONString
        return Json.createObjectBuilder()
                .add("decoding_board", this.decodingBoard.toJSON())
                .add("pegs", this.pegs.toJSON())
                .add("rulebook", this.rulebook.toJSON());
    }

    public static Pieces createJson(JsonObject o) {
        Pieces pcs = new Pieces();

        JsonObject decodingBoard = o.getJsonObject("decoding_board");
        JsonObject pegs = o.getJsonObject("pegs");
        JsonObject rulebook = o.getJsonObject("rulebook");

        pcs.setDecodingBoard(DecodingBoard.createJson(decodingBoard));
        pcs.setPegs(Pegs.createJson(pegs));
        pcs.setRulebook(Rulebook.createJson(rulebook));

        return pcs;
    }

}
