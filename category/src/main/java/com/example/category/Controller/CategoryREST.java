package com.example.category.Controller;

import com.example.category.Service.ISCategoryService;
import com.example.category.entity.Category;
import com.example.category.entity.SCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/category")
public class CategoryREST {
    @Autowired
    ISCategoryService categoryService;
    @GetMapping("/getallcat")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getCategories()
    {
        return categoryService.getCategories();
    }
    @GetMapping("/getallsubcat")
    @ResponseStatus(HttpStatus.OK)
    public List<SCategory> getSubCategories()
    {
    return categoryService.getSubCategories();
    }

    @PostMapping("/addcat/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@PathVariable(value = "name") String name){
        return categoryService.addCategory(name);
    }
    @PostMapping("/addsubcat/{id}/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public SCategory addSubCategory(@PathVariable Long id,@PathVariable String name){
        return categoryService.addSubCategory(id,name);
    }
    @GetMapping("/getscat/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<SCategory> findSubCategorybyCategory(@PathVariable Long id){
        return categoryService.getSubCategoriesByCategory(id);
    }
@PutMapping("/updatecat/{id}/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Category updatecat(@PathVariable Long id , @PathVariable String name)
{
  return  categoryService.updateCategory(id,name);
}
    @PutMapping("/updatesubcat/{id}/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SCategory updatesubcat(@PathVariable Long id , @PathVariable String name)
    {
        return  categoryService.updateSubCategory(id,name);
    }
    @DeleteMapping("/deletecat/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletecat(@PathVariable Long id ){
        categoryService.deleteCategory(id);
    }
    @DeleteMapping("/deletesubcat/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletesubcat(@PathVariable Long id ){
        categoryService.deleteSubCategory(id);
    }


}
