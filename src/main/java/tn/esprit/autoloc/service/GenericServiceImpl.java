package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class GenericServiceImpl<T,ID> implements IGenericService<T,ID> {
    protected final CrudRepository<T,ID> repository;

    public abstract String getEntityName();
    public abstract ID getId(T entity);
    public abstract void setFields(T existingEntity, T entity);
    
    public T create(T entity) {
        if (getId(entity) != null) {
            throw new IllegalArgumentException("Une nouvelle " + getEntityName() + " ne doit pas avoir d'identifiant !!");
        }

        if (!check(entity, true)) {
            throw new IllegalArgumentException("Vérifier les données de " + getEntityName() + " !!");
        }

        return repository.save(entity);
    }
    public T findById(ID id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(getEntityName(), id.toString()));
    }
    public List<T> findAll(){
        var list = new ArrayList<T>();

        repository.findAll().forEach(list::add);
        return list;
    }
    public T update(ID id, T entity){
        var entityExistant = findById(id);

        if (!check(entity, false)) {
            throw new IllegalArgumentException("Vérifier les données de " + getEntityName() + " !!");
        }

        setFields(entityExistant, entity);
        return repository.save(entityExistant);
    }
    public void deleteById(ID id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("entity", id.toString());
        }

        repository.deleteById(id);
    }
}
