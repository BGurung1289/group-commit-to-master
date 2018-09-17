package qa.seanqagroup.learningApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s already exists %s : '%s'", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}


}
