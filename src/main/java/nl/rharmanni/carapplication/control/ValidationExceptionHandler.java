package nl.rharmanni.carapplication.control;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import nl.rharmanni.carapplication.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 409
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidationException(ConstraintViolationException ex) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            ErrorResponse response = new ErrorResponse(
                    constraintViolation.getMessage(),
                    constraintViolation.getPropertyPath().toString());
            errorResponses.add(response);
        }
        return new ResponseEntity<>(errorResponses, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 409
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidationException(InvalidFormatException ex) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        ErrorResponse response = new ErrorResponse("Invalid format", ex.getPath().get(0).getFieldName());
        errorResponses.add(response);
        return new ResponseEntity<>(errorResponses, HttpStatus.BAD_REQUEST);
    }

}
