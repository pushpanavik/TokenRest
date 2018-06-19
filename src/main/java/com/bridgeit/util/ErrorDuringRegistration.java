package com.bridgeit.util;

import java.util.List;

import org.springframework.validation.FieldError;

public class ErrorDuringRegistration  extends Response{
List<FieldError> errors;

public List<FieldError> getErrors() {
	return errors;
}

public void setErrors(List<FieldError> errors) {
	this.errors = errors;
}

}
