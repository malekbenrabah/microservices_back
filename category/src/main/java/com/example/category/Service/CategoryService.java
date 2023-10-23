package com.example.category.Service;

import com.example.category.entity.Category;
import com.example.category.entity.CategoryRepository;
import com.example.category.entity.SCategory;
import com.example.category.entity.SCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ISCategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SCategoryRepository sCategoryRepository;
    @Override
    public SCategory addSubCategory(Long id, String scategory) {
        Category cat=categoryRepository.findById(id).get();
        SCategory sCategory = new SCategory();
        sCategory.setName(scategory);
        sCategory.setCategory(cat);
        sCategoryRepository.save(sCategory);
        return sCategory;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<SCategory> getSubCategories() {
        return sCategoryRepository.findAll();
    }

    @Override
    public Category addCategory(String name) {
        Category cat=new Category();
        cat.setName(name);
        return categoryRepository.save(cat);
    }

    @Override
    public List<SCategory> getSubCategoriesByCategory(Long id) {
        return sCategoryRepository.findByCategory_Id(id);
    }

    @Override
    public Category updateCategory(Long id,String category) {
      Category cat=categoryRepository.findById(id).get();
      cat.setName(category);
      categoryRepository.save(cat);
        return cat;
    }

    @Override
    public void deleteCategory(Long id) {
        List<SCategory> subcat = getSubCategoriesByCategory(id);


        subcat.forEach(sCat -> deleteSubCategory(sCat.getId()));


        sCategoryRepository.deleteById(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public SCategory updateSubCategory(Long id,String category) {
        SCategory cat= sCategoryRepository.findById(id).get();
        cat.setName(category);
        sCategoryRepository.save(cat);
        return cat;
    }

    @Override
    public void deleteSubCategory(Long id) {
      sCategoryRepository.deleteById(id);
    }


}
