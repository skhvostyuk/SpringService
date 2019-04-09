package ru.tsc.lectures.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import ru.tsc.lectures.model.Lecture;
import ru.tsc.lectures.model.LectureHall;

import java.util.Collection;

public interface LectureHallRepository extends Repository<LectureHall, Integer> {

    LectureHall findById(int id) throws DataAccessException;

    Collection<LectureHall> findAll() throws DataAccessException;

    @Cacheable(cacheNames = "halls")
    Collection<LectureHall> findByNameContainingAndPriceBetweenAndProjectorGreaterThanEqual(String name, int minPrice, int maxPrice, int projector) throws DataAccessException;

    @CachePut(cacheNames = "halls")
    void save(LectureHall petType) throws DataAccessException;

    @CacheEvict(value = "lectures", allEntries = true)
    void delete(LectureHall petType) throws DataAccessException;
}
