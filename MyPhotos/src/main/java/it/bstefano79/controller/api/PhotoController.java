package it.bstefano79.controller.api;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.bstefano79.model.Photo;

@RestController
@RequestMapping("/api")
public class PhotoController {
	private List<Photo> lista;
	
	public PhotoController() {
		lista = new ArrayList<Photo>();
		for(int i=0;i<5;i++)
		{
			String url = i+1<10? "./img/0"+(i+1)+".png":"./img/"+(i+1)+".png";
			lista.add(new Photo(i+1,url));
		}
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

}
 