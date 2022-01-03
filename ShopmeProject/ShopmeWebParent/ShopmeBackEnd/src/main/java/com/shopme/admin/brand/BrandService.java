package com.shopme.admin.brand;

import com.shopme.common.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BrandService {
    @Autowired
    public BrandRepository repo;

    public List<Brand> findAll() {
        return (List<Brand>) repo.findAll();
    }
}
