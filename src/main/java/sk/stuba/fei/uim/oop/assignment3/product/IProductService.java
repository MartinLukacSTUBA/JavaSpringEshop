package sk.stuba.fei.uim.oop.assignment3.product;


import java.util.List;

public interface IProductService {

     List<Product> getAll();
     Product create(ProductRequest request);
     Product getById(Long id);
     void delete(Long id);
     Long getAmountById(Long id);
     Long increaseAmountById(Long id, ProductRequest request);

}
