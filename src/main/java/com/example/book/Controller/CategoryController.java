package com.example.book.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.book.Model.Book;
import com.example.book.Model.Category;
import com.example.book.Services.CategoryService;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("title", "Category List");
        return "category/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("title", "Add Category");
        return "category/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("categories") Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("categories", category);
            model.addAttribute("title", "Add Category");
            return "category/add";
        }

        categoryService.addCategory(category);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Edit Category");
        model.addAttribute("categories", categoryService.getAllCategories());
        Category editCategory = categoryService.getCategoryById(id);
        if (editCategory != null) {
            model.addAttribute("book", editCategory);
            return "category/edit";

        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute("category") Category category) {
        categoryService.updateCategory(category);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
}
