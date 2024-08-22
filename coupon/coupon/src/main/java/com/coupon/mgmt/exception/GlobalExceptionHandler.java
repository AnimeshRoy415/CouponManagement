package com.coupon.mgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(OrderNotfoundException.class)
	public ResponseEntity<MyErrorDetails> myExpHandler(OrderNotfoundException ie,WebRequest wr) {
	System.out.println("inside Order not found method...");
	MyErrorDetails err=new MyErrorDetails();
	err.setTimestamp(LocalDateTime.now());
	err.setMessage(ie.getMessage());
	err.setDetails(wr.getDescription(false));
	return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	
	}
	@ExceptionHandler(InvalidInput.class)
	public ResponseEntity<MyErrorDetails> myExpHandler(InvalidInput ie,WebRequest wr) {
		System.out.println("inside Invalid Input method...");
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ConditionNotMeet.class)
	public ResponseEntity<MyErrorDetails> myExpHandler(ConditionNotMeet ie,WebRequest wr) {
		System.out.println("inside Condition not meet method...");
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(CouponNotFound.class)
	public ResponseEntity<MyErrorDetails> myExpHandler(CouponNotFound ie,WebRequest wr) {
		System.out.println("inside Coupon not found method...");
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(CouponExpire.class)
	public ResponseEntity<MyErrorDetails> myExpHandler(CouponExpire ie,WebRequest wr) {
		System.out.println("inside Coupon Expire method...");
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExpHandler(Exception ie,WebRequest wr) {
	System.out.println("inside Exception method...");
	MyErrorDetails err=new MyErrorDetails();
	err.setTimestamp(LocalDateTime.now());
	err.setMessage(ie.getMessage());
	err.setDetails(wr.getDescription(false));
	return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		System.out.println("inside MethodArgumentNotValidException method...");
	MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage());
	return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> myExpHandler(NoHandlerFoundException ie,WebRequest wr) {
	System.out.println("inside NoHandlerFoundException method...");
	MyErrorDetails err=new MyErrorDetails();
	err.setTimestamp(LocalDateTime.now());
	err.setMessage(ie.getMessage());
	err.setDetails(wr.getDescription(false));
	
	
	return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
}
