package it.bstefano79.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.bstefano79.model.Photo;
import it.bstefano79.service.MainPhotoService;

@RestController
@RequestMapping("/api")
public class PhotoController {

	
	@Autowired
	@Qualifier("mioPhotoService")
	//o si nomina l'istanza mainPhotoService o si usa @Qualifier("mainPhotoService")
	MainPhotoService photoService;
	
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

}
 