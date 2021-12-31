package com.shopme.admin.category;


import com.shopme.common.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repo;

    @Test
    public void testListAllUsers() {

        Pageable pageable = PageRequest.of(1,4);
        Page<Category> page = repo.findAll(pageable);
        page.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testRootCategory()  {
        List<Category> listCategories = repo.findRootCategories(Sort.by("name"));
        listCategories.forEach(c -> System.out.println(c.getName()));
    }

    @Test
    public void testFindByName() {
        String name = "Computers";
        Category category = repo.findByName(name);

        assertThat(category).isNotNull();
        assertThat(category.getName()).isEqualTo(name);
    }



    @Test
    public void testFindByAlias() {
        String alias = "Electronics";
        Category category = repo.findByAlias(alias);

        assertThat(category).isNotNull();
        assertThat(category.getAlias()).isEqualTo(alias);
    }

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
