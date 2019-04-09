package ru.tsc.lectures.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.lectures.model.Visitor;
import ru.tsc.lectures.repository.VisitorRepository;

@Service
public class VisitorService {
    @Autowired
    private VisitorRepository repository;


    public Visitor findById(int visitorId) {
        return repository.findById(visitorId);
    }

    public void delete(Visitor visitor) {
        repository.delete(visitor);
    }

    public void save(Visitor visitor) {
        repository.save(visitor);
    }
}
