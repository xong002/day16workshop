package sg.edu.nus.iss.day16workshop.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.SecureRandom;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class Mastermind implements Serializable {

    private String name;
    private Pieces pieces;
    private String id;
    private int insert_count;
    private int update_count;
    private boolean upsert;

    public Mastermind() {
        this.id = generateId(8);
    }

    public synchronized String generateId(int maxChars) {
        SecureRandom sr = new SecureRandom();
        StringBuilder strbuilder = new StringBuilder();
        while (strbuilder.length() < maxChars) {
            strbuilder.append(Integer.toHexString(sr.nextInt()));
        }

        return strbuilder.toString().substring(0, maxChars);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getInsert_count() {
        return insert_count;
    }

    public void setInsert_count(int insert_count) {
        this.insert_count = insert_count;
    }

    public int getUpdate_count() {
        return update_count;
    }

    public void setUpdate_count(int update_count) {
        this.update_count = update_count;
    }

    public boolean isUpsert() {
        return upsert;
    }

    public void setUpsert(boolean upsert) {
        this.upsert = upsert;
    }

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

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("name", this.getName())
                .add("pieces", this.getPieces().toJSON())
                .add("id", this.getId())
                .add("insert_count", this.getInsert_count())
                .add("update_count", this.getUpdate_count())
                .build();
    }

    // JSON format to be returned
    public JsonObject toJSONInsert() {
        return Json.createObjectBuilder()
                .add("insert_count", this.getInsert_count())
                .add("id", this.getId())
                .build();
    }

    // JSON format to be returned
    public JsonObject toJSONUpdate() {
        return Json.createObjectBuilder()
                .add("update_count", this.getUpdate_count())
                .add("id", this.getId())
                .build();
    }

    public static Mastermind create(String json) throws IOException {
        Mastermind m = new Mastermind();
        // create Object from String of JSON
        if (json != null) {
            try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
                JsonReader r = Json.createReader(is);
                JsonObject o = r.readObject();
                m.setName(o.getString("name"));
                m.setPieces(Pieces.createJson(o.getJsonObject("pieces")));
                m.setId(o.getString("id"));
                m.setInsert_count(o.getInt("insert_count"));
                m.setUpdate_count(o.getInt("update_count"));
            }
        }
        return m;
    }

}
