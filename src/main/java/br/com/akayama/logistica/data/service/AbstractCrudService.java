package br.com.akayama.logistica.data.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public abstract class AbstractCrudService<T, ID> {

    public AbstractCrudService() {
    }

    protected abstract JpaRepository<T, ID> getRepository();

    public Optional<T> findById(ID id) {
        return this.getRepository().findById(id);
    }

    public Page<T> findAll(Integer page, Integer size) {
        return this.getRepository().findAll(PageRequest.of(page, size));
    }

    public List<T> findAll() {
        return this.getRepository().findAll();
    }

    public T save(T entity) {
        return this.getRepository().save(entity);
    }

    public void deleteById(ID id) {
        this.getRepository().deleteById(id);
    }

    public void delete(T entity) {
        this.getRepository().delete(entity);
    }
}