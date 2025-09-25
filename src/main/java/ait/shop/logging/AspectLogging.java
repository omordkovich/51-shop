package ait.shop.logging;

import ait.shop.model.dto.ProductDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Oleg Mordkovich
 * {@code @date} 25.09.2025
 */

@Aspect
@Component
public class AspectLogging {
    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);

//    public double calculate(int a, int b) {}
//    public double calculate(double[]array){}
//    @Pointcut(....calculate(..)) -> to both methods
//    @Pointcut(....calculate(int, double)) -> to first method

    @Pointcut("execution(* ait.shop.services.ProductService.saveProduct(..))")
    public void saveProduct() {
    }

    @Before("saveProduct()")
    public void beforeSavingProduct(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info("Method saveProduct of the class ProductService called with argument {}", args[0]);
    }

    @After("saveProduct()")
    public void afterSavingProduct() {
        logger.info("Method saveProduct of the class ProductService finished its work");
    }

    @Pointcut("execution(* ait.shop.services.ProductService.getById(..))")
    public void getProductById(JoinPoint joinPoint) {
    }

    @AfterReturning(
            pointcut = "getProductById()",
            returning = "result"
    )
    public void afterReturningProductById(Object result) {
        logger.info("Method getProductById of the class ProductService successfully returned result: {}", result);
    }

    @AfterThrowing(
            pointcut = "getProductById()",
            throwing = "e"
    )
    public void exceptionWhileGettingProductById(Exception e) {
        logger.warn("Method getProductById of the class ProductService throw an exception: {}", e);
    }
}
