package com.mindtree.springbootapi.exceptions;

import com.mindtree.springbootapi.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<Object> handleExceptions(ApiException apiException){
        HttpStatus httpStatus = apiException.getHttpStatus();
        return new ResponseEntity<>(new ErrorResponse(apiException.getMessage(),httpStatus, ZonedDateTime.now()),httpStatus);
    }

    /*@ExceptionHandler(value = {MismatchedInputException.class, JsonParseException.class, JsonMappingException.class, IdentifierGenerationException.class})
    public ResponseEntity<Object> handleExceptions(Exception exception){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorResponse("Invalid body sent",httpStatus, ZonedDateTime.now()),httpStatus);
    }

    @ExceptionHandler(value = {RollbackException.class})
    public ResponseEntity<Object> handleExceptions(Throwable exception){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        while (exception != null) {
            if((exception instanceof ConstraintViolationException)){
                String message = "%s cannot be null or empty";
                if(exception.getMessage().contains("Id"))
                    message = String.format(message,"id");
                else if(exception.getMessage().contains("Name"))
                    message = String.format(message,"name");
                else if(exception.getMessage().contains("Description"))
                    message = String.format(message,"description");

                return new ResponseEntity<>(new ErrorResponse(message,httpStatus, ZonedDateTime.now()),httpStatus);
            }
            exception = exception.getCause();
        }
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(),httpStatus, ZonedDateTime.now()),httpStatus);
    }*/

}
