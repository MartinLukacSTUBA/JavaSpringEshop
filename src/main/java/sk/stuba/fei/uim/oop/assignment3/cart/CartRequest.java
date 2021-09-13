package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cartProducts.CartProducts;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CartRequest {
    private List<CartProducts> shoppingList = new ArrayList<>();
    private boolean payed ;
}