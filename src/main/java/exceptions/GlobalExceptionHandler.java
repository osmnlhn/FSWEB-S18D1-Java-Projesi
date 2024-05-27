package exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleBurgerException(BurgerException exception){
        log.error("Burgerexception occured",exception);
        BurgerErrorResponse burgerErrorResponse=new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<>(burgerErrorResponse,exception.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleException(Exception exception){
        log.error("Burgerexception occured",exception);
        BurgerErrorResponse burgerErrorResponse=new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<>(burgerErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
