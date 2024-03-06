package com.food.delivery.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.food.delivery.domain.exception.BusinessException;
import com.food.delivery.domain.exception.EntityInUseException;
import com.food.delivery.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ex.getRootCause();

        ResponseEntity<Object> checkRootCause = checkRootCause(rootCause, headers, status, request);
        if (checkRootCause != null) {
            return checkRootCause;
        }

        String detail = "The request body is invalid. Check syntax error";
        Problem problem = createProblemBuilder(status, ProblemType.INCOMPREHENSIBLE_MESSAGE, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> checkRootCause(Throwable rootCause, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handleUnrecognizedPropertyExceptionOrIgnoredPropertyException((PropertyBindingException) rootCause, headers, status, request);
        }
        return null;
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());

        String detail = String.format("Property '%s' has been assigned the value '%s', " +
                "which is of an invalid type. Correct and enter a value compatible with type %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());
        Problem problem = createProblemBuilder(status, ProblemType.INCOMPREHENSIBLE_MESSAGE, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleUnrecognizedPropertyExceptionOrIgnoredPropertyException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());

        String detail = String.format("Property '%s' not exist. " +
                "Please correct or remove this property and try again.", path);
        Problem problem = createProblemBuilder(status, ProblemType.INCOMPREHENSIBLE_MESSAGE, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        Problem problem = createProblemBuilder(HttpStatus.NOT_FOUND, ProblemType.ENTITY_NOT_FOUND, ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityInUseException ex, WebRequest request) {
        Problem problem = createProblemBuilder(HttpStatus.CONFLICT, ProblemType.ENTITY_IN_USE, ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(),
                HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request) {
        Problem problem = createProblemBuilder(HttpStatus.BAD_REQUEST, ProblemType.BUSINESS_ERROR, ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problem.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private static String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }
}
