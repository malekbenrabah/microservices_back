package com.example.category.Service;

import com.example.category.entity.Category;
import com.example.category.entity.SCategory;
import java.util.*;
public interface ISCategoryService {
    SCategory addSubCategory(Long id, String category);
    List<Category> getCategories();
    List<SCategory> getSubCategories();
    Category addCategory(String name);
    List<SCategory> getSubCategoriesByCategory(Long id);
    Category updateCategory(Long id,String category);
    void deleteCategory(Long id);
    SCategory updateSubCategory(Long id,String category);
    void deleteSubCategory(Long id);

}
