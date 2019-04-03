package ru.tsc.lectures.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class Controller {
    public HttpHeaders errorHeaders(BindingResult bindingResult) {
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        String errors = "";
        try {
            errors = mapper.writeValueAsString(bindingResult.getFieldErrors());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        headers.add("errors", errors);
        return headers;
    }
}
