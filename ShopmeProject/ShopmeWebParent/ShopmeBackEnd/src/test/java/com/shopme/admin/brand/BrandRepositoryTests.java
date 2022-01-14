package com.shopme.admin.brand;


import com.shopme.common.entity.Brand;
import com.shopme.common.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BrandRepositoryTests {
//    @Autowired
//    BrandRepository repo;
//
//    @Test
//    public void  testAddBrand() {
//        Brand brand1 =new Brand();
//        Set<Category> cat1 = new HashSet<Category>();
//        cat1.add(new Category(6));
//
//        Set<Category> cat2 = new HashSet<Category>();
//        cat2.add(new Category(4));
//        cat2.add(new Category(7));
//
//        Set<Category> cat3 = new HashSet<Category>();
//        cat3.add(new Category(24));
//        cat3.add(new Category(29));
//
//
//        brand1.setName("Acer");
//        brand1.setCategories(cat1);
//        brand1.setLogo("brand-logo.png");
//        Brand brand2 =new Brand();
//
//        brand2.setName("Apple");
//        brand2.setLogo("brand-logo.png");
//        brand2.setCategories(cat2);
//
//        Brand brand3 =new Brand();
//        brand3.setName("Samsung");
//        brand3.setLogo("brand-logo.png");
//        brand3.setCategories(cat3);
////        Brand result = repo.save(brand1);
//        Iterable<Brand> categories = repo.saveAll(List.of(brand2, brand3));
//
//    }
//
//    @Test
//    public void findAllTest() {
//        List<Brand> brand = (List<Brand>) repo.findAll();
//        brand.forEach(b->System.out.println(b.getId()+" "+b.getName()+" "+b.getCategories().toString()));
//        assertThat(brand).isNotEmpty();
//    }
//
//    @Test
//    public void getBrandById() {
//        Brand brand = repo.findById(1).get();
//        assertThat(brand.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void updateBrand() {
//        Brand brand = repo.findById(3).get();
//        brand.setName("Samsung Electronics");
//        assertThat(brand.getId()).isGreaterThan(0);
//
//    }
//
//    @Test
//    public void DeleteBrand() {
//        Brand brand = repo.findById(3).get();
//        repo.delete(brand);
//    }
}
