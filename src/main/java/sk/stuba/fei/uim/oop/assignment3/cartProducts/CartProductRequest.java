package sk.stuba.fei.uim.oop.assignment3.cartProducts;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartProductRequest {

    private Long amount;
    private Long productId;
}
