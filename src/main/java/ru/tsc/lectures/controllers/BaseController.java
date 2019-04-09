package ru.tsc.lectures.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Collection;

public class BaseController<T> {
    public ResponseEntity<T> errorResponseEntity(BindingResult bindingResult) {
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        String errors = "";
        try {
            errors = mapper.writeValueAsString(bindingResult.getFieldErrors());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        headers.add("errors", errors);
        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Collection<T>> emptyGetCollectionReview(Collection<T> collection) {
        if (collection.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    public ResponseEntity<T> nullGetReview(T t) {
        if (t == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }
}
