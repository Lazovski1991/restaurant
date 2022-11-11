//package my.company.restaurant.exception;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//@ControllerAdvice
//@RequiredArgsConstructor
//public class ExceptionsHandler {
//    @ExceptionHandler(value = {AuthException.class})
//    protected ResponseEntity<Object> handleAuthExc(AuthException ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(value = {RestaurantException.class})
//    protected ResponseEntity<Object> handleRestExc(RestaurantException ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(value = {Exception.class})
//    protected ResponseEntity<Object> handleExc(Exception ex, WebRequest request) {
//        return new ResponseEntity<>("Ups...", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
