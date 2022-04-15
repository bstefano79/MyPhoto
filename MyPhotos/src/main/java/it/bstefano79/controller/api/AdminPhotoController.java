package it.bstefano79.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.bstefano79.model.Photo;
import it.bstefano79.service.PhotoService;

@RestController
@RequestMapping("/admin/api")
public class AdminPhotoController {
	
	@Autowired
	PhotoService photoService;
	
	@RequestMapping(value = "/photos", method = RequestMethod.GET)
	public Iterable<Photo> getAll()
	{
		return photoService.getAll();
	}
	
	@RequestMapping(value = "/photos/{id}", method = RequestMethod.GET)
	public Photo getPhoto(@PathVariable int id)
	{
		Photo photoReturn = photoService.getPhoto(id);
		
		if(photoReturn==null)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Foto non trovata");
		}
		return photoReturn;
	}
	
	@RequestMapping(value = "/photos", method = RequestMethod.POST)
	public Photo createPhoto(@RequestBody Photo photo)
	{	
		Photo photoReturn = photoService.createPhoto(photo);
		return photoReturn;
	}
	
	@RequestMapping(value = "/photos/{id}", method = RequestMethod.PUT)
	public Photo updatePhoto(@PathVariable int id, @RequestBody Photo photo)
	{
		Photo photoReturn = photoService.updatePhoto(id, photo);
		
		if(photoReturn==null)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Foto non trovata");
		}
		return photoReturn;
	}
	
	@RequestMapping(value = "/photos/{id}", method = RequestMethod.DELETE)
	public Photo deletePhoto(@PathVariable int id)
	{
		Photo photoReturn = photoService.deletePhoto(id);
		
		if(photoReturn==null)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Foto non trovata");
		}
		return photoReturn;
	}


}
