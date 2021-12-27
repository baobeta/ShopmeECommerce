package com.shopme.admin.category.controller;

import com.shopme.admin.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRestController {



    @Autowired
    private CategoryService service;


    @PostMapping("/categories/check_name")
    public String checkDuplicateEmail(@Param("id") Integer id,@Param("name") String name) {
        return service.isNameUnique(id, name) ? "OK" : "Duplicated";
    }
}
