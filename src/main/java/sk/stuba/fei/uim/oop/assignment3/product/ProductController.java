package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }


    @GetMapping
    public List<ProductResponse> getAllProducts(){
        var result = new ArrayList<ProductResponse>();
        for(Product a:this.service.getAll()){
            result.add(new ProductResponse(a));
        }
        return result;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<ProductResponse>addProduct(@RequestBody ProductRequest request){
        return new ResponseEntity<>(new ProductResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return this.service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id")Long id){this.service.delete(id);}


    @GetMapping("/{id}/amount")
    public Map<String,Long> getAmountById(@PathVariable("id")Long id){
        return Collections.singletonMap("amount",this.service.getAmountById(id));
    }

    @PostMapping("/{id}/amount")
    public Map<String, Long> increaseAmountById(@PathVariable("id") Long id,@RequestBody ProductRequest request){
        return Collections.singletonMap("amount",this.service.increaseAmountById(id,request));
    }
    }



