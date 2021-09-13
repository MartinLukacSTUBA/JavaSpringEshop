package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.cartProducts.CartProductRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {


    private final ICartService service;

    public CartController(ICartService service) {
        this.service = service;
    }


    @GetMapping
    public List<CartResponse> getAllCart(){
        ArrayList<CartResponse> result = new ArrayList<>();
        for(Cart a:this.service.getAllCart()){
            result.add(new CartResponse(a));
        }
        return result;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public CartResponse addCart(){
        return new CartResponse(this.service.create());
    }


    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable("id") Long id){
        return this.service.getCartById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable("id")Long id){this.service.deleteCart(id);}


    @PostMapping("/{id}/add")
    public Cart addProductToCart(@PathVariable("id") Long id,@RequestBody CartProductRequest request){
        return this.service.addToCart(id,request.getProductId(),request.getAmount());
    }

    @GetMapping("/{id}/pay")
    public String payForCartController(@PathVariable("id") Long id){
        return this.service.payForCart(id);
    }

}