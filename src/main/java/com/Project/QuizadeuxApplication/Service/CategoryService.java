package com.Project.QuizadeuxApplication.Service;

import com.Project.QuizadeuxApplication.Entities.Category;

import java.util.List;

public interface CategoryService {

    Category NewCategory(Category category);

    List<Category> AllCategory();

    Category findCategoryById(String categoryId);
}
