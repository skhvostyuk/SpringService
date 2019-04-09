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
import ru.tsc.lectures.model.Visitor;
import ru.tsc.lectures.repository.LectureRepository;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "api/lecture", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LectureController extends BaseController<Lecture> {
    @Autowired
    private LectureRepository lectureRepository;

    @GetMapping("/{lectureId}")
    public ResponseEntity<Lecture> getById(@PathVariable("lectureId") int lectureId) {
        return nullGetReview(lectureRepository.findById(lectureId));
    }

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<Void> deleteById(@PathVariable("lectureId") int lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId);
        if (lecture == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        lectureRepository.delete(lecture);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity<Lecture> addLecture(@Valid @RequestBody Lecture lecture, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        if (bindingResult.hasErrors() || lecture == null || !lecture.isNew())
            return errorResponseEntity(bindingResult);
        lectureRepository.save(lecture);
        HttpHeaders headers = headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/lecture/{id}").buildAndExpand(lecture.getId()).toUri());
        return new ResponseEntity<>(lecture, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{lectureId}")
    public ResponseEntity<Lecture> updateLecture(@PathVariable("lectureId") int lectureId, @Valid @RequestBody Lecture lecture,
                                                 BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        if (bindingResult.hasErrors() || lecture == null)
            return errorResponseEntity(bindingResult);

        Lecture currentLecture = lectureRepository.findById(lectureId);
        if (currentLecture == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        currentLecture.update(lecture);
        return new ResponseEntity<>(currentLecture, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{lectureId}/visitors")
    public ResponseEntity<Collection<Visitor>> findVisitorsByLectureID(@PathVariable("lectureId") int lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId);
        if (lecture == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //if (lecture.getVisitors().size() == 0)
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // Пустой список тоже возвращается, поскольку, если есть лекция,
        // то и список есть, просто никто еще не записан. TODO: Как правильно?
        return new ResponseEntity<>(lecture.getVisitors(), HttpStatus.OK);
    }

    public ResponseEntity<Collection<Lecture>> findAll() {
        return emptyGetCollectionReview(lectureRepository.findAll());
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Collection<Lecture>> findByParams(@RequestParam(value = "name", defaultValue = "") String name,
                                                            @RequestParam(value = "pricefrom", defaultValue = "0") int minPrice,
                                                            @RequestParam(value = "priceto", defaultValue = "9999999") int maxPrice) {
        return emptyGetCollectionReview(lectureRepository.findByNameContainingAndPriceBetween(name, minPrice, maxPrice));
    }
}
