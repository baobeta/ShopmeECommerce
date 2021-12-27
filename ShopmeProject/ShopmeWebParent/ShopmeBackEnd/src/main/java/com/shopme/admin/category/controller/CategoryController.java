package com.shopme.admin.category.controller;

import com.shopme.admin.FileUploadUtil;
import com.shopme.admin.category.CategoryService;
import com.shopme.common.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    public CategoryService service;


    @GetMapping("/categories")
    public String listAll(Model model) {
        return listByPage(1,model,"name","asc",null);
    }

    @GetMapping("/categories/page/{pageNum}")
    public String listByPage(
            @PathVariable(name="pageNum") int pageNum, Model model,
            @Param("sortField") String sortField,
            @Param("sortDir") String sortDir,
            @Param("keyword") String keyword) {

        // pagination
        Page<Category> page = service.listByPage(pageNum, sortField, sortDir,keyword);
        List<Category> listCategories = page.getContent();


        // setup number in button
        long startCount = (pageNum-1) * CategoryService.CATEGORYY_PER_PAGE +1;
        long endCount= startCount + CategoryService.CATEGORYY_PER_PAGE -1;
        if(endCount>page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        // sort
        String reserveSortDir = sortDir.equals("asc") ? "desc" : "asc";

        model.addAttribute("currentPage",pageNum);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("startCount",startCount);
        model.addAttribute("endCount",endCount);
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reserveSortDir",reserveSortDir);
        model.addAttribute("keyword",keyword);
        model.addAttribute("listCategories",listCategories);

        return "category/categories";
    }


    @GetMapping("/categories/{id}/enabled/{status}")
    public String updateUserEnabledStatus(@PathVariable("id") Integer id,
                                          @PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
        service.updateCategoryEnabledStaus(id, enabled);
        String status = enabled ? "enabled" : "disabled";
        String message ="The user ID "+ id +" has been " +status;
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/categories";
    }

    @GetMapping("/categories/new")
    public String newUser(Model model) {
        List<Category> listCategories = service.listCategoriesUsedInForm();
        // create new user
        Category category = new Category();
        category.setEnabled(true);
        // set some attribute
        model.addAttribute("category",category);
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("pageTitle","Create New User");
        return "category/category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category,
                               @RequestParam("fileImage") MultipartFile multipartFile,
                               RedirectAttributes ra) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        category.setImage(fileName);

        Category savedCategory = service.save(category);
        String uploadDir = "category-images/" + savedCategory.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        ra.addFlashAttribute("message", "The category has been saved successfully.");
        return "redirect:/categories";
    }

}
