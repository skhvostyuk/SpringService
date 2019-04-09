package ru.tsc.lectures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.tsc.lectures.model.Visitor;
import ru.tsc.lectures.repository.VisitorRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/visitor", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VisitorController extends BaseController<Visitor> {
    @Autowired
    private VisitorRepository repository;

    @GetMapping("/{visitorId}")
    public ResponseEntity<Visitor> getById(@PathVariable("visitorId") int visitorId) {
        Visitor visitor = repository.findById(visitorId);
        if (visitor == null)
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Visitor>(visitor, HttpStatus.OK);
    }

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<Void> deleteById(@PathVariable("lectureId") int lectureId) {
        Visitor visitor = repository.findById(lectureId);
        if (visitor == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        repository.delete(visitor);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity<Visitor> addVisitor(@Valid Visitor visitor, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        if (bindingResult.hasErrors())
            return errorResponseEntity(bindingResult);
        repository.save(visitor);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/visitor/{id}").buildAndExpand(visitor.getId()).toUri());
        return new ResponseEntity<>(visitor, headers, HttpStatus.CREATED);
    }
}