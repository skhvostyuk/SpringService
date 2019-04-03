package ru.tsc.lectures.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ru.tsc.lectures.model.Lecture;

import java.util.Collection;

public interface LectureRepository extends Repository<Lecture, Long> {

    Collection<Lecture> findByName(@Param("name") String name);

    Collection<Lecture> findByPriceLessThanEqual(@Param("price") int price);

    Collection<Lecture> findByPriceGreaterThanEqual(@Param("price") int price);

    Collection<Lecture> findByPriceBetween(int minPrice, int maxPrice);

    Collection<Lecture> findAll();

    Lecture findById(int id) throws DataAccessException;

    Lecture save(Lecture lecture) throws DataAccessException;

    void delete(Lecture lecture) throws DataAccessException;


}
