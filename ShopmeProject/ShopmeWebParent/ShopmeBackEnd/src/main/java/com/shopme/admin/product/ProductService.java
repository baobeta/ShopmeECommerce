package com.shopme.admin.product;

import com.shopme.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    
    
    public List<Product> listAll() {
        return (List<Product>) productRepository.findAll();
    }
    
    public Product save(Product product) {
        if(product.getId()==null) {
            product.setCreatedTime(new Date());
        }
        if(product.getAlias()==null || product.getAlias().isEmpty()) {
            String defaultAlias = product.getName().replace(" ","-");
            product.setAlias(defaultAlias);
        } else {
            product.setAlias(product.getAlias().replace(" ",""));
        }
        product.setCreatedTime(new Date());
        return productRepository.save(product);
    }

    public String checkUnique(Integer id, String name) {
        boolean isCreatingNew = (id == null || id == 0);
        Product productByName = productRepository.findByName(name);

        if (isCreatingNew) {
            if (productByName != null) return "Duplicate";
        } else {
            if (productByName != null && productByName.getId() != id) {
                return "Duplicate";
            }
        }

        return "OK";
    }
}
