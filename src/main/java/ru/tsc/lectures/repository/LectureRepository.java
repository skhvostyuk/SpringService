package ru.tsc.lectures.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ru.tsc.lectures.model.Lecture;

import java.util.Collection;

public interface LectureRepository extends Repository<Lecture, Long> {

    Collection<Lecture> findByName(@Param("name") String name);

    @Cacheable(cacheNames = "lectures")
    Collection<Lecture> findByNameContainingAndPriceBetween(String name, int minPrice, int maxPrice);

    Collection<Lecture> findAll();

    Lecture findById(int id) throws DataAccessException;

    @CachePut(cacheNames = "lectures")
    Lecture save(Lecture lecture) throws DataAccessException;

    @CacheEvict(value = "lectures", allEntries = true)
    Lecture delete(Lecture lecture) throws DataAccessException;


}
