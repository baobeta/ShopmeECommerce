package com.shopme.admin.product;


import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TestProductRepository {

//    @Autowired
//    private ProductRepository repo;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Test
//    public void testCreateProduct()  {
//        Brand brand = entityManager.find(Brand.class, 37);
//        Category category = entityManager.find(Category.class,5);
//
//        Product product = new Product();
//        product.setName("Acer Asprice Desktop");
//        product.setAlias("acer_asprice_desktop");
//        product.setShortDescription("sort description for cer Asprice Desktop ");
//        product.setFullDescription("full description for cer Asprice Desktop");
//
//        product.setBrand(brand);
//        product.setCategory(category);
//
//        product.setPrice(678);
//        product.setCost(600);
//        product.setEnabled(true);
//        product.setInStock(true);
//
//        product.setCreatedTime(new Date());
//        product.setUpdatedTime(new Date());
//
//       Product saveProduct =  repo.save(product);
//       assertThat(saveProduct).isNotNull();
//       assertThat(saveProduct.getId()).isGreaterThan(0);
//
//
//    }
//
//    @Test
//    public void testListAllProduct() {
//        Iterable<Product> iterableProduct = repo.findAll();
//
//        iterableProduct.forEach(System.out::println);
//
//    }
//    @Test
//    public void testGetProduct() {
//        Integer id = 2;
//        Product productById = repo.findById(2).get();
//        System.out.println(productById);
//        assertThat(productById.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void testUpdateProduct() {
//        Integer id =1;
//        Product product = repo.findById(id).get();
//        product.setPrice(400);
//
//        repo.save(product);
//
//        Product productUpdate = entityManager.find(Product.class, id);
//        assertThat(productUpdate.getPrice()).isEqualTo(400);
//    }
//
//    @Test
//    public void testdeleteProduct() {
//        Integer id =4;
//        repo.deleteById(id);
//
//        Optional<Product> byId = repo.findById(id);
//        assertThat(!byId.isPresent());
//    }

}
