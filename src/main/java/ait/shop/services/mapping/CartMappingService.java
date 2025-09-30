package ait.shop.services.mapping;

import ait.shop.model.dto.CartDTO;
import ait.shop.model.entity.Cart;
import org.mapstruct.Mapper;

/**
 * @author Oleg Mordkovich
 * {@code @date} 30.09.2025
 */

@Mapper(componentModel = "spring", uses = ProductMappingService.class)
public interface CartMappingService {

    Cart mapDtoToEntity(CartDTO cartDto);

    CartDTO mapEntityToDto(Cart cart);
}
