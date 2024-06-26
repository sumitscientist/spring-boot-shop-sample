package com.syqu.shop.controller;

import com.syqu.shop.domain.Product;
import com.syqu.shop.service.ProductService;
import com.syqu.shop.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//create controller class CartControllerCopy
@Controller
public class CartControllerCopy {
    private final Logger logger = LoggerFactory.getLogger(CartController.class);

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @Autowired
    public CartControllerCopy(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    //SpringBoot method cart() with return type String
    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());
        return "cart";
    }
    @GetMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            shoppingCartService.addProduct(product);
            logger.debug(String.format("Product with id: %s added to shopping cart.", id));
        }
        return "redirect:/home";
    }
    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            shoppingCartService.removeProduct(product);
            logger.debug(String.format("Product with id: %s removed from shopping cart.", id));
        }
        return "redirect:/cart";
    }
    @GetMapping("/cart/clear")
    public String clearProductsInCart(){
        shoppingCartService.clearProducts();

        return "redirect:/cart";
    }
    @GetMapping("/cart/checkout")
    public String cartCheckout(){
        shoppingCartService.cartCheckout();

        return "redirect:/cart";
    }
    @GetMapping("/cart/checkout/success")
    public String cartCheckoutSuccess(){
        shoppingCartService.clearProducts();
        return "cart-checkout-success";
    }
    @GetMapping("/cart/checkout/cancel")
    public String cartCheckoutCancel(){
        shoppingCartService.clearProducts();
        return "cart-checkout-cancel";
    }
    @GetMapping("/cart/checkout/error")
    public String cartCheckoutError(){
        shoppingCartService.clearProducts();
        return "cart-checkout-error";
    }
}
