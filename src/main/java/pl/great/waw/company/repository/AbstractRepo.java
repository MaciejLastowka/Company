package pl.great.waw.company.repository;

import pl.great.waw.company.exceptions.IdNotFoundException;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepo<E extends Entity> implements Crudable<E> {

    protected List<E> list = new ArrayList<>();

    @Override
    public List<E> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public E create(E entity) throws PeselAlreadyExistException {
        if (list.contains(entity)) {
            throw new PeselAlreadyExistException(("This pesel already exist: " + entity.getId()));
        }
        list.add(entity);
        return entity;
    }

    @Override
    public E read(String id) throws IdNotFoundException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        throw new IdNotFoundException("This pesel not found: " + id);
    }


    @Override
    public E update(E e) throws IdNotFoundException {
        E entity = this.read(e.getId());
        int index = list.indexOf(entity);
        list.set(index, entity);
        return entity;
    }

    @Override
    public boolean delete(String id) throws IdNotFoundException {
        return list.remove(read(id));
    }

    @Override
    public int size() {
        return list.size();
    }
}
