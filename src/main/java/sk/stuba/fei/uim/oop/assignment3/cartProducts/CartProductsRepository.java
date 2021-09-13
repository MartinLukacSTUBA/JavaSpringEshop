package sk.stuba.fei.uim.oop.assignment3.cartProducts;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartProductsRepository extends CrudRepository<CartProducts,Long> {

    Optional<CartProducts> findByProductId(Long id);
}
