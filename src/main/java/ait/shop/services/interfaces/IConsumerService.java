package ait.shop.services.interfaces;

import ait.shop.model.dto.ConsumerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IConsumerService {

    //    ➢ Сохранить покупателя в базе данных (при сохранении покупатель автоматически считается
//            активным)
    ConsumerDTO save(ConsumerDTO consumer);

    //➢ Вернуть всех покупателей из базы данных (активных)
    List<ConsumerDTO> getAllActiveConsumers();

    //➢ Вернуть одного покупателя из базы данных по его идентификатору (если он активен)
    ConsumerDTO getById(Long id);

    //    ConsumerDTO getActiveConsumerById(Long id);
    //➢ Изменить одного покупателя в базе данных по его идентификатору
    ConsumerDTO update(Long id, ConsumerDTO consumer);

    //➢ Удалить покупателя из базы данных по его идентификатору
    ConsumerDTO deleteById(Long id);

    //➢ Удалить покупателя из базы данных по его имени
    ConsumerDTO deleteByName(String name);

    //➢ Восстановить удалённого покупателя в базе данных по его идентификатору

    ConsumerDTO restoreConsumer(Long id);

    //➢ Вернуть общее количество покупателей в базе данных (активных)
    long getCountActiveConsumers();

    //➢ Вернуть стоимость корзины покупателя по его идентификатору (если он активен)
    BigDecimal getTotalPriceOfProductsInCartByActiveConsumerId(Long customerId);

    //➢ Вернуть среднюю стоимость продукта в корзине покупателя по его идентификатору (если он
//активен)
    double getAveragePriceOfActiveProductsInCartByActiveConsumerId(Long customerId);

    //➢ Добавить товар в корзину покупателя по их идентификаторам (если оба активны)
    void addProductToConsumersCart(Long consumerId, Long productId);

    //➢ Удалить товар из корзины покупателя по их идентификаторам
    void removeProductFromConsumersCart(Long consumerId, Long productId);

    //➢ Полностью очистить корзину покупателя по его идентификатору (если он активен)
    void cleanCart(Long consumerId);

}
