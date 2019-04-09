package ru.tsc.lectures.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.lectures.model.LectureHall;
import ru.tsc.lectures.repository.LectureHallRepository;

import java.util.Collection;

@Service
public class LectureHallService {
    @Autowired
    LectureHallRepository repository;

    public LectureHall findById(int lectureHallId) {
        return repository.findById(lectureHallId);
    }

    public void delete(LectureHall hall) {
        repository.delete(hall);
    }

    public void save(LectureHall hall) {
        repository.save(hall);
    }

    public Collection<LectureHall> findByFilter(String name, int minPrice, int maxPrice, int projector) {
        return repository.findByNameContainingAndPriceBetweenAndProjectorGreaterThanEqual(name, minPrice, maxPrice, projector);
    }
}
