package it.bstefano79.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.bstefano79.model.Photo;

@Repository
public interface InterfacePhotoRepository extends CrudRepository<Photo, Integer>{

}
