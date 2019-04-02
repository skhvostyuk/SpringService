package ru.tsc.lectures.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import ru.tsc.lectures.model.Lecture;
import ru.tsc.lectures.model.LectureHall;

public interface LectureHallRepository extends Repository<LectureHall, Integer> {

    LectureHall findById(int id) throws DataAccessException;

    void save(LectureHall petType) throws DataAccessException;

    void delete(LectureHall petType) throws DataAccessException;
}
