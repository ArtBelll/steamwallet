package ru.steamwallet.swserver.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.steamwallet.swcommon.exceptions.AlreadyRegistered;
import ru.steamwallet.swcommon.exceptions.BadRequest;
import ru.steamwallet.swcommon.exceptions.ResourceNotFoundException;
import ru.steamwallet.swcommon.exceptions.UnAuthorized;

/**
 * Created by Sergey Ignatov on 24/02/16.
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Error {
        @Getter @Setter
        private int code;
        @Getter @Setter
        private String message;

        public Error(int code, final String message) {
            this.code = code;
            this.message = message;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class ErrorResponse {
        @Getter @Setter
        private Error error;
        @Getter @Setter
        private String status;

        public ErrorResponse(final Error error, final String status) {
            this.error = error;
            this.status = status;
        }
    }

    private static HttpHeaders jsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundResource(RuntimeException ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        ResourceNotFoundException rex = (ResourceNotFoundException) ex;

        ErrorResponse error = new ErrorResponse(
                new Error(404, rex.getDescription() +
                        " [" + rex.getName() + "=" + (rex.getUuid() != null ? rex.getUuid().toString() : rex.getId()) + "]"),
                "ERR");

        return handleExceptionInternal(ex, error, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UnAuthorized.class})
    protected ResponseEntity<Object> handleUnauthorizedRequest(UnAuthorized ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        ErrorResponse error = new ErrorResponse(
                new Error(401, "Unauthorized request" + (!"".equals(ex.getMessage()) ? ": " + ex.getMessage() : "")),
                "ERR");

        return handleExceptionInternal(ex, error, headers, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleItunesExceptions(Exception ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        ErrorResponse error = new ErrorResponse(
                new Error(500, "Unhandled error"),
                "ERR"
        );

        ex.printStackTrace();
        return handleExceptionInternal(ex, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({AlreadyRegistered.class})
    protected ResponseEntity<Object> handleAreadyRegisteredEmail(AlreadyRegistered ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        ErrorResponse error = new ErrorResponse(
                new Error(400, ex.getMessage()),
                "ERR"
        );

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({BadRequest.class})
    protected ResponseEntity<Object> handleBadRequest(BadRequest ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        final String message = ex.getMessage();
        ErrorResponse error = new ErrorResponse(
                new Error(400, StringUtils.isEmpty(message) ? "Bad request" : "Bad request: " + message),
                "ERR"
        );

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }
}
