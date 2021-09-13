package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
 public class ProductService implements  IProductService{

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository){
        this.repository=repository;

        Product firstProduct = new Product();
        firstProduct.setName("First Product Name");
        firstProduct.setDescription("First Product Description");
        firstProduct.setAmount(10);
        firstProduct.setUnit("First Product String");
        firstProduct.setPrice(10);
        this.repository.save(firstProduct);
        Product secondProduct = new Product();
        secondProduct.setName("Second name");
        secondProduct.setDescription("Second desc");
        secondProduct.setAmount(20);
        secondProduct.setUnit("Second unit");
        secondProduct.setPrice(20);
        this.repository.save(secondProduct);

    }


    @Override
    public List<Product> getAll(){
        return this.repository.findAll();
    }
    @Override
    public Product create(ProductRequest request){
        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setAmount(request.getAmount());
        newProduct.setUnit(request.getUnit());
        newProduct.setPrice(request.getPrice());
        return this.repository.save(newProduct);
    }

    @Override
    public Product getById(Long id) {
        return this.repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        Product product = this.repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.repository.delete(product);
    }

    @Override
    public Long getAmountById(Long id) {
        Product product = this.repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return product.getAmount();
    }

    @Override
    public Long increaseAmountById(Long id,ProductRequest request) {
        Product product = this.repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        product.setAmount(product.getAmount()+request.getAmount());
        this.repository.save(product);
        return product.getAmount();
    }

}
