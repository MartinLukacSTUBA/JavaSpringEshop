package sk.stuba.fei.uim.oop.assignment3.cartProducts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class CartProducts {

    @Id
    private Long productId;
    private Long amount;

}
