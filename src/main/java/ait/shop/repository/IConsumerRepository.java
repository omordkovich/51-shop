package ait.shop.repository;

import ait.shop.model.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsumerRepository extends JpaRepository<Consumer, Long> {

}
