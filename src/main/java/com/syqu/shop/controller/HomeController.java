package com.syqu.shop.controller;

import com.syqu.shop.domain.Product;
import com.syqu.shop.service.CategoryService;
import com.syqu.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
// write HomeController class

@Controller
public class HomeController {
    private final ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    //write home method to get list of products from ProductService class
    //and add number of products to the model
    //and find all categories from CategoryService class and add them to the model
    //and return home.html
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("products", getAllProducts());
        model.addAttribute("productsCount", productsCount());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    //write getAllProducts method to get list of products from ProductService class
    private List<Product> getAllProducts(){
        return productService.findAllByOrderByIdAsc();
    }

    //write productsCount method to get number of products from ProductService class
    private long productsCount(){
        return productService.count();
    }
}
