/**
 * 
 */
package com.star.sud.exception;

import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.star.sud.exception.dto.ExceptionResponse;

/**
 * @author Sudarshan
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserExistsException.class)
	public final ResponseEntity<Object> handleUserAlreadyExists(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse response = new ExceptionResponse(Calendar.getInstance().getTime(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserRecordNotFound(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse response = new ExceptionResponse(Calendar.getInstance().getTime(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse response = new ExceptionResponse(Calendar.getInstance().getTime(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		StringBuilder sb = new StringBuilder();
		BindingResult bindingResult = ex.getBindingResult();
		for (FieldError error : bindingResult.getFieldErrors())
			sb.append(error.getDefaultMessage() + ", ");

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Filed", sb.toString());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
