package sk.stuba.fei.uim.oop.assignment3.cart;

import java.util.List;

public interface ICartService{
    List<Cart> getAllCart();
    Cart create();
    Cart getCartById(Long id);
    void deleteCart(Long id);
    Cart addToCart(Long cartID,Long productID, Long amount);
    String payForCart(Long id);
}