package ru.tsc.lectures.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import ru.tsc.lectures.model.Visitor;

public interface VisitorRepository extends Repository<Visitor, Integer> {



    void save(Visitor petType) throws DataAccessException;

    void delete(Visitor petType) throws DataAccessException;

}
