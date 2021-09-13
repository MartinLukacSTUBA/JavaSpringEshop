package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingListDTO {

    private Long productId;
    private Long amount;

    public ShoppingListDTO(Long productId,Long amount){
        this.productId=productId;
        this.amount= amount;
    }
}
