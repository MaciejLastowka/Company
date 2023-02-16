package pl.great.waw.company.repository;

import pl.great.waw.company.exceptions.IdNotFoundException;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;

import java.util.List;

public interface Crudable<E> {

    List<E> getAll();
    E create(E e) throws PeselAlreadyExistException;
    E read(String id) throws IdNotFoundException;
    E update(E e) throws IdNotFoundException;
    boolean delete(String id) throws IdNotFoundException;
    int size();

}
