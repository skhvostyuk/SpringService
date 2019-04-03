package ru.tsc.lectures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsc.lectures.model.Lecture;
import ru.tsc.lectures.model.Visitor;
import ru.tsc.lectures.repository.LectureRepository;

import java.util.Collection;
import java.util.Map;

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

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<Void> deleteById(@PathVariable("lectureId") int lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId);
        if (lecture == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        lectureRepository.delete(lecture);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{lectureId}/visitors")
    // TODO: Имеет ли смысл выделение в отдельный контролле? (И искать можно сразу по базе)
    public ResponseEntity<Collection<Visitor>> findVisitorsByLectureID(@PathVariable("lectureId") int lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId);
        if (lecture.getVisitors().size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);   //TODO: Это адекватно? Может, стоит возвращать пустой массив?
        return new ResponseEntity<>(lecture.getVisitors(), HttpStatus.OK);
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Collection<Lecture>> findByParams(@RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "pricefrom", defaultValue  = "0") int minPrice,
                                                            @RequestParam(value = "priceto", defaultValue  = "0") int maxPrice) {
        if (name != null) //TODO: Некрасиво, есть другие варианты обработки?
            return findByName(name);
        else if (minPrice > 0) {
            if (maxPrice > 0)
                return findByMinMaxPriceFiler(minPrice, maxPrice);
            else
                return findByMinPriceFiler(minPrice);
        }
        else if (maxPrice > 0)
            return findByMaxPriceFiler(maxPrice);

        return findAll();
    }

    public ResponseEntity<Collection<Lecture>> findAll() {
        Collection<Lecture> lectures = lectureRepository.findAll();
        if (lectures.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }

    public ResponseEntity<Collection<Lecture>> findByName(String name) {
        Collection<Lecture> lectures = lectureRepository.findByName(name);
        if (lectures.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }


    public ResponseEntity<Collection<Lecture>> findByMaxPriceFiler( int maxPrice) {
        Collection<Lecture> lectures = lectureRepository.findByPriceLessThanEqual(maxPrice);
        if (lectures.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }

    public ResponseEntity<Collection<Lecture>> findByMinPriceFiler(int minPrice) {
        Collection<Lecture> lectures = lectureRepository.findByPriceGreaterThanEqual(minPrice);
        if (lectures.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }


    public ResponseEntity<Collection<Lecture>> findByMinMaxPriceFiler(int minPrice, int maxPrice) {
        Collection<Lecture> lectures = lectureRepository.findByPriceBetween(minPrice, maxPrice);
        if (lectures.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }
}
