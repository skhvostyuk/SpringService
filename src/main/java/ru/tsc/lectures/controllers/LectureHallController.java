package ru.tsc.lectures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsc.lectures.model.Lecture;
import ru.tsc.lectures.model.LectureHall;
import ru.tsc.lectures.repository.LectureHallRepository;
import ru.tsc.lectures.repository.LectureRepository;

@RestController
@RequestMapping(value = "api/hall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LectureHallController {

    @Autowired
    private LectureHallRepository hallRepository;

    @GetMapping("/{lectureHallId}")
    public ResponseEntity<LectureHall> getById(@PathVariable("lectureHallId") int lectureHallId) {
        LectureHall hall = hallRepository.findById(lectureHallId);
        if (hall == null)
            return new ResponseEntity<LectureHall>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<LectureHall>(hall, HttpStatus.OK);
    }

    @DeleteMapping("/{lectureHallId}")
    public ResponseEntity<Void> deleteById(@PathVariable("lectureHallId") int lectureHallId) {
        LectureHall hall = hallRepository.findById(lectureHallId);
        if(hall == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        hallRepository.delete(hall);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
