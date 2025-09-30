package ait.shop.logging;

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
    public void getProductById() {
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

    @Pointcut("execution(* ait.shop.services.*.*(..))")
    public void ProductServiceMethod() {
    }

    @Before("ProductServiceMethod()")
    public void beforeProductServiceMethod(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            logger.info("Method {} of class {} was called with arguments {}", methodName, className, args[0]);
        } else {
            logger.info("Method {} of class {} was called", methodName, className);
        }
    }

    @After("ProductServiceMethod()")
    public void afterProductServiceMethod(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} of class {} finished its work", methodName, className);
    }

    @AfterThrowing(
            pointcut = "ProductServiceMethod()",
            throwing = "exception"
    )
    public void afterExceptionThrowing(Exception exception, JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.warn("Method {} of the class {} throw an exception: {}", methodName, className, exception);
    }

    @AfterReturning(
            pointcut = "ProductServiceMethod()",
            returning = "res"
    )
    public void afterReturningResult(Object res, JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} of the class {} successfully returned result: {}", methodName, className, res);
    }
}
