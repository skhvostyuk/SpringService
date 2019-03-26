package ru.tsc.lectures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.tsc.lectures.model.Lecture;
import ru.tsc.lectures.repository.LectureRepository;

@RestController
@RequestMapping("api/lecture")
public class LectureController {
    @Autowired
    private LectureRepository lectureRepository;



    @RequestMapping(value = "/{lectureId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Lecture> greeting(@PathVariable("lectureId") int lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId);
        if (lecture == null)
            return new ResponseEntity<Lecture>(HttpStatus.NOT_FOUND);
        System.out.println(lecture.getLecturerName());
        return new ResponseEntity<Lecture>(lecture, HttpStatus.OK);
    }
}
