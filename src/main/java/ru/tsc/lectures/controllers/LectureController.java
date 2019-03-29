package ru.tsc.lectures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsc.lectures.model.Lecture;
import ru.tsc.lectures.repository.LectureRepository;

import java.util.Collection;

@RestController
@RequestMapping(value = "api/lecture", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LectureController {
    @Autowired
    private LectureRepository lectureRepository;



    @GetMapping("/{lectureId}")
    public ResponseEntity<Lecture> getById(@PathVariable("lectureId") int lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId);
        if (lecture == null)
            return new ResponseEntity<Lecture>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Lecture>(lecture, HttpStatus.OK);
    }

    @DeleteMapping("/{lectureId}/delete")
    public ResponseEntity<Void> deleteById(@PathVariable("lectureId") int lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId);
        if(lecture == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        lectureRepository.delete(lecture);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<Collection<Lecture>> findByName(@RequestParam("name") String name) {
        Collection<Lecture> lectures = lectureRepository.findByName(name);
        if (lectures.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }




}
