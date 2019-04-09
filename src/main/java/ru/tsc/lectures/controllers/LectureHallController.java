package ru.tsc.lectures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.tsc.lectures.model.LectureHall;
import ru.tsc.lectures.service.LectureHallService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "api/hall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LectureHallController extends BaseController<LectureHall> {

    @Autowired
    private LectureHallService hallService;

    @GetMapping("/{lectureHallId}")
    public ResponseEntity<LectureHall> getById(@PathVariable("lectureHallId") int lectureHallId) {
        return nullGetReview(hallService.findById(lectureHallId));
    }

    @DeleteMapping("/{lectureHallId}")
    public ResponseEntity<Void> deleteById(@PathVariable("lectureHallId") int lectureHallId) {
        LectureHall hall = hallService.findById(lectureHallId);
        if (hall == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        hallService.delete(hall);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity<LectureHall> addHall(@Valid @RequestBody LectureHall hall, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        if (bindingResult.hasErrors())
            return errorResponseEntity(bindingResult);
        hallService.save(hall);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/hall/{id}").buildAndExpand(hall.getId()).toUri());
        return new ResponseEntity<>(hall, headers, HttpStatus.CREATED);
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Collection<LectureHall>> findByParams(@RequestParam(value = "name", defaultValue = "") String name,
                                                                @RequestParam(value = "pricefrom", defaultValue = "0") int minPrice,
                                                                @RequestParam(value = "priceto", defaultValue = "9999999") int maxPrice,
                                                                @RequestParam(value = "projector", defaultValue = "0") int projector) {


        return emptyGetCollectionReview(hallService.findByFilter(name, minPrice, maxPrice, projector));
    }
}
