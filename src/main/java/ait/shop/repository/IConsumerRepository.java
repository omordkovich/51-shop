package ait.shop.repository;

import ait.shop.model.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IConsumerRepository extends JpaRepository<Consumer, Long> {

    List<Consumer> findByName(String name);
}
