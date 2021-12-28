package com.shopme.admin.category;

import com.shopme.admin.user.UserNotFoundException;
import com.shopme.common.entity.Category;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Transactional
public class CategoryService {

    public static final int CATEGORYY_PER_PAGE =4;

    @Autowired
    private CategoryRepository categoryRepo;


    public List<Category> listAll() {
        List<Category> rootCategories = categoryRepo.findRootCategories();
        return listHierarchicalCategories(rootCategories);
    }

    private List<Category> listHierarchicalCategories(List<Category> rootCategories) {
        List<Category> hierarchicalCategories = new ArrayList<>();

        for (Category rootCategory : rootCategories) {
            hierarchicalCategories.add(Category.copyFull(rootCategory));

            Set<Category> children = rootCategory.getChildren();

            for (Category subCategory : children) {
                String name = "--" + subCategory.getName();
                hierarchicalCategories.add(Category.copyFull(subCategory, name));

                listSubHierarchicalCategories(hierarchicalCategories, subCategory, 1);
            }
        }

        return hierarchicalCategories;
    }
    private void listSubHierarchicalCategories(List<Category> hierarchicalCategories,
                                               Category parent, int subLevel) {
        Set<Category> children = parent.getChildren();
        int newSubLevel = subLevel + 1;

        for (Category subCategory : children) {
            String name = "";
            for (int i = 0; i < newSubLevel; i++) {
                name += "--";
            }
            name += subCategory.getName();

            hierarchicalCategories.add(Category.copyFull(subCategory, name));

            listSubHierarchicalCategories(hierarchicalCategories, subCategory, newSubLevel);
        }

    }

    public Page<Category> listByPage(int pageNum, String sortField, String sortDir, String keyword ) {
        Sort sort = Sort.by(sortField);

        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNum-1,CATEGORYY_PER_PAGE, sort);

        if(keyword!=null) {
            return categoryRepo.findAll(keyword, pageable);
        }
        return categoryRepo.findAll(pageable);

    }
    public void updateCategoryEnabledStaus(Integer id, boolean enabled) {
        categoryRepo.updateEnabledStatus(id,enabled);
    }
    public void delete(Integer id) throws UserNotFoundException {
        Long countById = categoryRepo.countById(id);
        if(countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any category with ID "+ id);
        }
        categoryRepo.deleteById(id);
    }

    public Category get(Integer id) throws UserNotFoundException {
        try {
            return categoryRepo.findById(id).get();
        } catch(NoSuchElementException ex) {
            throw new UserNotFoundException("Could not find any category with ID "+ id);
        }

    }


    public Category save(Category category) {
        return categoryRepo.save(category);

    }

    public List<Category> listCategoriesUsedInForm() {
        List<Category> categoriesUsedInForm = new ArrayList<>();

        Iterable<Category> categoriesInDB = categoryRepo.findAll();

        for (Category category : categoriesInDB) {
            if (category.getParent() == null) {
                categoriesUsedInForm.add(Category.copyIdAndName(category));

                Set<Category> children = category.getChildren();

                for (Category subCategory : children) {
                    String name = "--" + subCategory.getName();
                    categoriesUsedInForm.add(Category.copyIdAndName(subCategory.getId(), name));

                    listChildren(categoriesUsedInForm, subCategory, 1);
                }
            }
        }

        return categoriesUsedInForm;
    }


    public boolean isNameUnique(Integer id, String name) {
        Category category = categoryRepo.findCategoryByName(name);
        if(category == null) return true;
        boolean isCreateNew= (id==null);
        if(isCreateNew) {
            if(category != null) return false;
        } else {
            if(category.getId()!=id) {
                return false;
            }
        }
        return true;

    }

    private void listChildren(List<Category> categoriesUsedInForm, Category parent, int subLevel) {
        int newSubLevel = subLevel + 1;
        Set<Category> children = parent.getChildren();

        for (Category subCategory : children) {
            String name = "";
            for (int i = 0; i < newSubLevel; i++) {
                name += "--";
            }
            name += subCategory.getName();

            categoriesUsedInForm.add(Category.copyIdAndName(subCategory.getId(), name));

            listChildren(categoriesUsedInForm, subCategory, newSubLevel);
        }
    }
}
