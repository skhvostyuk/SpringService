package ru.tsc.lectures.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.tsc.lectures.model.Lecture;
import ru.tsc.lectures.model.LectureHall;
import ru.tsc.lectures.repository.LectureHallRepository;
import ru.tsc.lectures.repository.LectureRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/hall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LectureHallController extends Controller {

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
        if (hall == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        hallRepository.delete(hall);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity<LectureHall> addHall(@Valid LectureHall hall, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers;
        if (bindingResult.hasErrors()) {
            headers = errorHeaders(bindingResult);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        hallRepository.save(hall);
        headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/hall/{id}").buildAndExpand(hall.getId()).toUri());
        return new ResponseEntity<>(hall, headers, HttpStatus.CREATED);
    }
}
