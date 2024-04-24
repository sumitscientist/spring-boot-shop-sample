package com.syqu.shop.controller;

//import all required Junit classes
import com.syqu.shop.service.ProductService;
import com.syqu.shop.service.ShoppingCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootTest
@RunWith(SpringRunner.class)

public class CartControllerMvcTestsCopy {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ShoppingCartService shoppingCartService;

    @MockBean
    ProductService productService;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new CartController(shoppingCartService, productService)).setViewResolvers(viewResolver).build();
    }
    @Test
    public void testCart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cart"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("cart"));
    }

    @Test
    public void testCheckout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/checkout"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("checkout"));
    }

    @Test
    public void testCheckoutConfirm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/checkoutConfirm"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("checkoutConfirm"));
    }

    @Test
    public void testCheckoutComplete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/checkoutComplete"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("checkoutComplete"));
    }

    @Test
    public void testCheckoutCancel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/checkoutCancel"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("checkoutCancel"));
    }


}
