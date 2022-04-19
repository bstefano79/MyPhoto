package it.bstefano79.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import it.bstefano79.model.Photo;

@Service
public class PhotoService implements MainPhotoService {
	private List<Photo> lista;
	private int lastId;
	
	public PhotoService() {
		lista = new ArrayList<Photo>();
		for(int i=0;i<5;i++)
		{
			String url = i+1<10? "./img/0"+(i+1)+".png":"./img/"+(i+1)+".png";
			lista.add(new Photo(i+1,url));
		}
		lastId = lista.size();
	}
	
	@Override
	public Iterable<Photo> getAll()
	{
		return lista;
	}
	
	@Override
	public Photo getPhoto(int id)
	{
		Optional<Photo> opt = lista.stream().filter(value -> value.getId() == id).findFirst();
		
		if(opt.isEmpty())
		{
			return null;
		}
		return opt.get();
	}
	
	@Override
	public Photo createPhoto(Photo photo)
	{	
		photo.setId(++lastId);
		lista.add(photo);
		return photo;
	}
	
	@Override
	public Photo updatePhoto(int id, Photo photo)
	{
		Optional<Photo> opt = lista.stream().filter(value -> value.getId() == id).findFirst();
		
		if(opt.isEmpty())
		{
			return null;
		}
		
		opt.get().setUrl(photo.getUrl());
		return opt.get();
	}
	
	@Override
	public Photo deletePhoto(int id)
	{
		Optional<Photo> opt = lista.stream().filter(value -> value.getId() == id).findFirst();
		
		if(opt.isEmpty())
		{
			return null;
		}
		
		lista.remove(opt.get());
		return opt.get();
	}
}
