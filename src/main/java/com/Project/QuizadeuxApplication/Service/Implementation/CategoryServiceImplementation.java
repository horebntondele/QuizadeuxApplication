package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.CategoryDao;
import com.Project.QuizadeuxApplication.Entities.Category;
import com.Project.QuizadeuxApplication.Service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImplementation implements CategoryService {
    private CategoryDao categoryDao;

    public CategoryServiceImplementation(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category NewCategory(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public List<Category> AllCategory() {
        return categoryDao.findAll();
    }

    @Override
    public Category findCategoryById(String categoryId) {
        return categoryDao.findbyCategoryId(categoryId);
    }
}
