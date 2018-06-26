package com.giflib.giflib.controller;

import com.giflib.giflib.data.CategoryRepository;
import com.giflib.giflib.data.GifRepository;
import com.giflib.giflib.model.Category;
import com.giflib.giflib.model.Gif;
import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GifRepository gifRepository;

    @RequestMapping("/categories")
    public String listCategories(ModelMap modelmap) {
        List<Category> categories = categoryRepository.getAllCategories();
        modelmap.put("categories",categories);
        return "categories";
    }

    @RequestMapping("/category/{id}")
    public String category(@PathVariable int id, ModelMap modelmap) {
        Category category = categoryRepository.findById(id);
        modelmap.put("category",category);

        List<Gif> gifs = gifRepository.findByCategoryId(id);
        modelmap.put("gifs", gifs);

        return "category";
    }
}
