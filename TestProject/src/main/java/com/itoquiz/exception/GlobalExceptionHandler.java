package com.itoquiz.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;

@RestControllerAdvice
public class GlobalExceptionHandler extends Throwable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception){
		
		String message = exception.getMessage();
		
		ApiResponse apiResponse = new ApiResponse(message);
		
		return new ResponseEntity<ApiResponse> (apiResponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, String> httpMessageNotReadableException(HttpMessageNotReadableException exception){
		
		Map<String, String> errorMap = new HashMap<String , String>();
		
		Throwable cause=exception.getCause();
		
		if(cause instanceof MismatchedInputException) {
			MismatchedInputException missMatchException= (MismatchedInputException) cause;
			
			errorMap.put("Should be Integer", missMatchException.getPath().get(0).getFieldName());
		}
		
		return errorMap;
		
	}	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> methodArgumentNotValidException(MethodArgumentNotValidException exception){
		Map<String, String> errorMap = new HashMap<String , String>();
		
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			System.out.println("errors "+error.getField());
			errorMap.put(error.getField(),error.getDefaultMessage());
		});
		
		return errorMap;
	}
	

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ApiResponse> NumberformatMisMatch(NumberFormatException exception){
		
		String message = "Please check your input";
	
		ApiResponse apiResponse = new ApiResponse(message);
		return new ResponseEntity<ApiResponse> (apiResponse,HttpStatus.NOT_FOUND);
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> illegalArgumentException(IllegalArgumentException exception){
		
		String message = exception.getMessage();
		
		ApiResponse apiResponse = new ApiResponse(message);
		
		return new ResponseEntity<ApiResponse> (apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ApiResponse> illegalArgumentException(SQLIntegrityConstraintViolationException exception){
		
		String message = "Email Id already exists, Please enter a new email id to register.";
		
		ApiResponse apiResponse = new ApiResponse(message);
		
		return new ResponseEntity<ApiResponse> (apiResponse,HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<ApiResponse> missingPathVariableException(MissingPathVariableException exception){
		
		String message = exception.getMessage();
		
		ApiResponse apiResponse = new ApiResponse(message);
		
		return new ResponseEntity<ApiResponse> (apiResponse,HttpStatus.NOT_FOUND);
	}
}
