package com.invex.employees.infrastructure.adapter.in.rest.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.invex.employees.domain.exception.EmployeeNotFoundException;
import com.invex.employees.infrastructure.adapter.in.rest.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
 
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerNotFound(EmployeeNotFoundException ex,
			HttpServletRequest request){
		return buildError(
				HttpStatus.NOT_FOUND,
				ex.getMessage(),
				request,null);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handlerValidation(
			MethodArgumentNotValidException ex,
			HttpServletRequest request){
		Map<String,String> errors = new LinkedHashMap<>();
		
		ex.getBindingResult().getFieldErrors()
		.forEach(error -> errors.putIfAbsent(
				error.getField(),
				error.getDefaultMessage()
				));				
		
		return buildError(
				HttpStatus.BAD_REQUEST,
				"Validation failed",
				request,errors);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handlerBadRequest(IllegalArgumentException ex,
			HttpServletRequest request){
		return buildError(
				HttpStatus.BAD_REQUEST,
				ex.getMessage(),
				request,null);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handlerInvalidBody(HttpMessageNotReadableException ex,
			HttpServletRequest request){
		return buildError(
				HttpStatus.BAD_REQUEST,
				"Invalid Json",
				request,null);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handlerTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpServletRequest request){
		return buildError(
				HttpStatus.BAD_REQUEST,
				"Invalid parameter: " + ex.getName(),
				request,null);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handlerGeneral(
			Exception ex,
			HttpServletRequest request){
		log.error("unexpected API error",ex);
		return buildError(
				HttpStatus.BAD_REQUEST,
				ex.getMessage(),
				request,null);
	}
	
	
	private ResponseEntity<ErrorResponse> buildError(
			HttpStatus status,
			String message,
			HttpServletRequest request,
			Map<String,String> fieldErrors){
		ErrorResponse error= new ErrorResponse(
				LocalDateTime.now(),
				status.value(),
				status.getReasonPhrase(),
				message,
				request.getRequestURI(),
				fieldErrors);
		
		return ResponseEntity.status(status).body(error);
	}
}
