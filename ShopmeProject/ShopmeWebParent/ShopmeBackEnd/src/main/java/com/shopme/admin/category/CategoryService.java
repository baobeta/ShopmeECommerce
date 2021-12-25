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

import java.util.NoSuchElementException;

@Service
@Transactional
public class CategoryService {

    public static final int CATEGORYY_PER_PAGE =4;

    @Autowired
    private CategoryRepository categoryRepo;

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



}
