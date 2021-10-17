package com.codegym.createcart.controller;

import com.codegym.createcart.model.Cart;
import com.codegym.createcart.model.Product;
import com.codegym.createcart.service.CartService;
import com.codegym.createcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public void deno(HttpSession session){
        System.out.println(session.getAttribute("carts"));
    }

    @GetMapping("/buy")
    public String getCart(@SessionAttribute("carts") HashMap<Long, Cart> hashMap, Model model){
        model.addAttribute("carts", hashMap);
        model.addAttribute("cartNum", hashMap.size());
        model.addAttribute("cartMoney", totalPrice(hashMap));
        return "cart";
    }

    @GetMapping("/buy/{id}")
    public String addProductToCart(@PathVariable Long id, @SessionAttribute("carts") HashMap<Long, Cart> hashMap, Model model) {

        Product product = productService.findById(id);
        if (product != null){

//            Trường hợp chưa có sản phẩm ở trong giỏ hàng.
            if (!hashMap.containsKey(id)){
                Cart item = new Cart();
                item.setProduct(product);
                item.setQuantity(1);
                hashMap.put(id, item);
            } else {
//                Sản phẩm đã có trong giỏ hàng trước.
                Cart item = hashMap.get(id);
                item.setProduct(product);
                item.setQuantity(item.getQuantity() + 1);
                hashMap.put(id, item);
            }
        }
        model.addAttribute("carts", hashMap);
        model.addAttribute("cartNum", hashMap.size());
        model.addAttribute("cartMoney", totalPrice(hashMap));
        return "redirect:/cart/buy";
    }


    public double totalPrice(HashMap<Long, Cart> cartHashMap) {
        int count = 0;
        for (Map.Entry<Long, Cart> list : cartHashMap.entrySet()) {
            count += Double.parseDouble(list.getValue().getProduct().getNewPrice()) * list.getValue().getQuantity();
        }
        return count;
    }
}
