package jp.co.linkstaff.iis.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {
	  public ResourceAlreadyExistsException(String exception) {
		    super(exception);
		  }
	}