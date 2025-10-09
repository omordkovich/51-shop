package ait.shop.exception.handling.exceptions.prodict;

// 2 способ
// ПЛЮС -  быстро и удобно без лишнего кода создаём
//         глобальный обработчик ошибок
// МИНУС - пользователь не видит сообщения об ошибке,
//         поэтому может не понять причин её возникновения
//@ResponseStatus(HttpStatus.NOT_FOUND)

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("Product with id: %d was not found", id));
    }
}
