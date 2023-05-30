package sg.edu.nus.iss.day16workshop.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day16workshop.model.Mastermind;
import sg.edu.nus.iss.day16workshop.repository.BoardGameRepo;

@Service
public class BoardGameService {
    
    @Autowired
    BoardGameRepo repo;

    public int saveGame(final Mastermind mm){
        return repo.saveGame(mm);
    }

    public Mastermind findById(final String mmId) throws IOException{
        return repo.findById(mmId);
    }

    public int updateGame(final Mastermind mm){
        return repo.updateGame(mm);
    }
}
