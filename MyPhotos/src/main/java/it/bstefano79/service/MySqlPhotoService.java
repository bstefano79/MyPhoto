package it.bstefano79.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bstefano79.model.Photo;
import it.bstefano79.repository.InterfacePhotoRepository;

@Service("mioPhotoService")
public class MySqlPhotoService implements MainPhotoService{
	
	@Autowired
	InterfacePhotoRepository photoRepository;

	@Override
	public Iterable<Photo> getAll() {
		return photoRepository.findAll();
	}

	@Override
	public Photo getPhoto(int id) {
		Optional<Photo> opt = photoRepository.findById(id);
		if(opt.isEmpty())
		{
			return null;
		}
		return opt.get();
	}

	@Override
	public Photo createPhoto(Photo photo) {
		return photoRepository.save(photo);
	}

	@Override
	public Photo updatePhoto(int id, Photo photo) {
		Photo photoDb = this.getPhoto(id);
		if(photoDb==null)
		{
			return null;
		}
		photoDb.setUrl(photo.getUrl());
		return this.createPhoto(photoDb);
	}

	@Override
	public Photo deletePhoto(int id) {
		Photo photoDb = this.getPhoto(id);
		if(photoDb==null)
		{
			return null;
		}
		photoRepository.deleteById(id);
		return photoDb;
	}

}
