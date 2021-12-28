package com.shopme.admin.category;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Category;



@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repo;

    @Autowired
    private CategoryService service;

    @Test
    public void testListRootCategory()  {
        List<Category> categories = repo.findRootCategories();
        categories.forEach(cat-> System.out.println(cat.getName()));


    }

//
//    @Test
//    public void testListAllUsers() {
//
//        Pageable pageable = PageRequest.of(1,4);
//        Page<Category> page = repo.findAll(pageable);
//        page.forEach(p -> System.out.println(p.getName()));
//    }
//
//    @Test
//    public void testCreateRootCategory() throws Exception {
//        Category category = new Category("Nokia");
//        Category saveCategory = repo.save(category);
//        assertThat(saveCategory.getId()).isGreaterThan(0);
//
//    }
//    @Test
//    public void testCreateSubCategory() {
//        Category parent = new Category(7);
//        Category subCategory = new Category("iPhone", parent);
//        Category saveCategory =repo.save(subCategory);
//
//        assertThat(saveCategory.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void testGetCategory() {
//        Category category = repo.findById(2).get();
//        System.out.println(category.getName());
//        Set<Category> children = category.getChildren();
//
//        for (Category child : children) {
//            System.out.println(child.getName());
//        }
//        assertThat(children.size()).isGreaterThan(0);
//    }
//
//    @Test
//    public void testEnableCategory() {
//        repo.updateEnabledStatus(6,true);
//        System.out.println("Test enable");
//
//    }
//
//    @Test
//    public void testCreateCategory() {
//        Category category = new Category("Keyboard");
//        Category save = service.save(category);
//        assertThat(save.getId()).isGreaterThan(0);
//
//    }
//
//    @Test
//    public void testGetCategoryByName() {
//        Category category = repo.findCategoryByName("iPhone");
//        assertThat(category.getId()).isGreaterThan(0);
//    }
//
//
//    @Test
//    public void testPrintHierarchicalCategory() {
//        Iterable<Category> categories = repo.findAll();
//
//        for(Category category : categories) {
//            if(category.getParent()==null) {
//                System.out.println(category.getName());
//
//                Set<Category> children = category.getChildren();
//                for(Category child : children) {
//                    System.out.println("--"+child.getName());
//                    printChildren(child,1);
//                }
//            }
//        }
//
//    }
//    private void printChildren(Category parent, int subLevel) {
//        int newSubLevel = subLevel + 1;
//        Set<Category> children =  parent.getChildren();
//        for(Category child :children) {
//            for(int i=0;i<newSubLevel;i++) {
//                System.out.print("--");
//            }
//            System.out.println(child.getName());
//        }
//    }
}
