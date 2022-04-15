package it.bstefano79.service;

import it.bstefano79.model.Photo;

public interface MainPhotoService {
	public Iterable<Photo> getAll();
	public Photo getPhoto(int id);
	public Photo createPhoto(Photo photo);
	public Photo updatePhoto(int id, Photo photo);
	public Photo deletePhoto(int id);
}
