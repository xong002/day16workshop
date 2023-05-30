package sg.edu.nus.iss.day16workshop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day16workshop.model.Mastermind;
import sg.edu.nus.iss.day16workshop.service.BoardGameService;

@RestController
@RequestMapping(path = "/api/boardgame", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardGameRestController {

    @Autowired
    BoardGameService service;

    @PostMapping
    public ResponseEntity<String> createBoardGame(@RequestBody Mastermind mm) {
        int returnVal = service.saveGame(mm);
        if (returnVal == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mm.getId());
    }

    @GetMapping("/{mmId}")
    public ResponseEntity<String> getBoardGame(@PathVariable String mmId) throws IOException {
        Mastermind mm = service.findById(mmId);
        if (mm == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(mm.toJSON().toString());
    }

    //not working - id not correct?
    @PutMapping("/{mmId}")
    public ResponseEntity<String> updateBoardGame(@RequestBody Mastermind mm, @PathVariable String mmId)
            throws IOException {
        Mastermind result = service.findById(mmId);
        if (result != null) {
            int returnVal = service.updateGame(mm, mmId);
            if (returnVal == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            result = service.findById(mmId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result.toJSON().toString());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
