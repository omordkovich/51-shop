package ait.shop.repository;

import ait.shop.model.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IConsumerRepository extends JpaRepository<Consumer, Long> {

    Optional<Consumer> findByName(String name);
}
