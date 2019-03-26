package ru.tsc.lectures.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ru.tsc.lectures.model.Lecture;

import java.util.Collection;

public interface LectureRepository extends Repository<Lecture, Long> {

    Collection<Lecture> findByName(@Param("name") String name);

    Lecture findById(int id) throws DataAccessException;

    Lecture save(Lecture petType) throws DataAccessException;

    void delete(Lecture petType) throws DataAccessException;

}
