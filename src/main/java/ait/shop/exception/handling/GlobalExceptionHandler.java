package ait.shop.exception.handling;

import ait.shop.exception.handling.exceptions.consumer.ConsumerNotFoundException;
import ait.shop.exception.handling.exceptions.prodict.ProductNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @author Oleg Mordkovich
 * {@code @date} 07.10.2025
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //    Способ 3
//    ПЛЮС -  мы создаём глобальный обработчик ошибок, который умеет ловить
//    ошибки, возникающие в любом классе и методе нашего проекта
//    ПЛЮС -  логика обработки ошибок выносится в отдельный класс, а основная
//    бизнес-логика проекта не нагружена дополнительной вспомогательной логикой
//    МИНУС - такой способ не подходит, если нам нужна разная обработка одних и
//    тех же ошибок для разных контроллеров. В таком случае можно
//    воспользоваться способом 1

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Response> handleException(ProductNotFoundException e) {
        String message = e.getMessage();
        logger.warn(message);
        Response response = new Response(message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConsumerNotFoundException.class)
    public  ResponseEntity<Response> handleException(ConsumerNotFoundException e){
        String message = e.getMessage();
        logger.warn(message);
        Response response = new Response(message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleException(ConstraintViolationException e) {
        return new ResponseEntity<>(e
                .getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList(),
                HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<String> handleException(ProductNotFoundException e) {
//        String message = e.getMessage();
//        logger.warn(message);
//        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
//    }
}
