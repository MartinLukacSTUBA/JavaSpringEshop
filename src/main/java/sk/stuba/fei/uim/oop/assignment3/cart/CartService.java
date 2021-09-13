package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.cartProducts.CartProducts;
import sk.stuba.fei.uim.oop.assignment3.cartProducts.CartProductsRepository;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{
    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final CartProductsRepository cartProductsRepository;



    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository,CartProductsRepository cartProductsRepository){
        this.cartRepository =cartRepository;
        this.productRepository=productRepository;
        this.cartProductsRepository=cartProductsRepository;

        Cart cart1 = new Cart();
        cart1.setPayed(false);
        cartRepository.save(cart1);
        Cart cart2 = new Cart();
        cart2.setPayed(false);
        this.cartRepository.save(cart2);
    }


    @Override
    public List<Cart> getAllCart(){return this.cartRepository.findAll();}

    @Override
    public Cart create() {
        Cart newCart = new Cart();
        newCart.setPayed(false);
        return this.cartRepository.save(newCart);
    }

    @Override
    public Cart getCartById(Long id) {
        return this.cartRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @Override
    public void deleteCart(Long id) {
        Cart cart = this.cartRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.cartRepository.delete(cart);
    }

    @Override
    public String payForCart(Long cartID){
        Cart cart =this.cartRepository.findById(cartID).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        double sum=0;
        if(cart.isPayed()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            for(CartProducts products:cart.getShoppingList()){
                Long amountOfProductsInList = products.getAmount();
                sum = sum + this.productRepository.findById(products.getProductId()).get().getPrice()*amountOfProductsInList;

            }
            cart.setPayed(true);
            this.cartRepository.save(cart);
        }
        return String.valueOf(sum);
    }

    @Override
    public Cart addToCart(Long cartID,Long productID, Long amount){
        Cart cart=this.cartRepository.findById(cartID).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Product product=this.productRepository.findById(productID).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
       Optional<CartProducts> cartProducts = this.cartProductsRepository.findByProductId(productID);
       if(amount>product.getAmount()||cart.isPayed()){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }else{
           product.setAmount(product.getAmount() - amount);
           this.productRepository.save(product);
           if(cartProducts.isPresent()){
               cartProducts.get().setAmount(cartProducts.get().getAmount()+amount);
               this.cartProductsRepository.save(cartProducts.get());
           }else{
               CartProducts cartProducts1 = new CartProducts();
               cartProducts1.setProductId(productID);
               cartProducts1.setAmount(amount);
               cart.getShoppingList().add(cartProducts1);
               this.cartProductsRepository.save(cartProducts1);
           }
           return this.cartRepository.save(cart);
       }
    }
}