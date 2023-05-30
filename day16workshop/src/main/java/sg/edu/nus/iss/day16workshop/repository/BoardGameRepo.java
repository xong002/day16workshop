package sg.edu.nus.iss.day16workshop.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day16workshop.model.Mastermind;

@Repository
public class BoardGameRepo {
    
    @Autowired
    RedisTemplate<String, String> template;

    public int saveGame(final Mastermind mm){
        template.opsForValue().set(mm.getId(), mm.toJSON().toString());
        String result = template.opsForValue().get(mm.getId());
        if(result != null){
            return 1;
        }
        return 0;
    }

    public Mastermind findById(final String mmId) throws IOException{
        String jsonStringVal = (String) template.opsForValue().get(mmId); //returns String from database
        Mastermind mm = Mastermind.createJson(jsonStringVal); //convert String to JsonObject, then set it to Mastermind obj
        return mm;
    }

    public int updateGame(final Mastermind mm, String mmId){
        String jsonStringVal = (String) template.opsForValue().get(mmId);
        if(jsonStringVal != null){
            template.opsForValue().set(mmId, mm.toJSON().toString());
            return 1;
        }
        return 0;
    }


}
