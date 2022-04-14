package it.bstefano79.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.bstefano79.model.Photo;

@RestController
@RequestMapping("/admin/api")
public class AdminPhotoController {
	
	private List<Photo> lista;
	private int lastId;
	
	public AdminPhotoController() {
		lista = new ArrayList<Photo>();
		for(int i=0;i<5;i++)
		{
			String url = i+1<10? "./img/0"+(i+1)+".png":"./img/"+(i+1)+".png";
			lista.add(new Photo(i+1,url));
		}
		lastId = lista.size();
	}
	
	@RequestMapping(value = "/photos", method = RequestMethod.GET)
	public Iterable<Photo> getAll()
	{
		return lista;
	}
	
	@RequestMapping(value = "/photos/{id}", method = RequestMethod.GET)
	public Photo getPhoto(@PathVariable int id)
	{
		Optional<Photo> opt = lista.stream().filter(value -> value.getId() == id).findFirst();
		
		if(opt.isEmpty())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Foto non trovata");
		}
		return opt.get();
	}
	
	@RequestMapping(value = "/photos", method = RequestMethod.POST)
	public Photo createPhoto(@RequestBody Photo photo)
	{	
		photo.setId(++lastId);
		lista.add(photo);
		return photo;
	}
	
	@RequestMapping(value = "/photos/{id}", method = RequestMethod.PUT)
	public Photo updatePhoto(@PathVariable int id, @RequestBody Photo photo)
	{
		Optional<Photo> opt = lista.stream().filter(value -> value.getId() == id).findFirst();
		
		if(opt.isEmpty())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Foto non trovata");
		}
		
		opt.get().setUrl(photo.getUrl());
		return opt.get();
	}
	
	@RequestMapping(value = "/photos/{id}", method = RequestMethod.DELETE)
	public Photo deletePhoto(@PathVariable int id)
	{
		Optional<Photo> opt = lista.stream().filter(value -> value.getId() == id).findFirst();
		
		if(opt.isEmpty())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Foto non trovata");
		}
		
		lista.remove(opt.get());
		return opt.get();
	}


}
