package wydmuch.patryk.psw2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wydmuch.patryk.psw2.entity.Product;
import wydmuch.patryk.psw2.model.Cart;
import wydmuch.patryk.psw2.repositories.ProductRepository;
import wydmuch.patryk.psw2.services.OrderService;
import wydmuch.patryk.psw2.utils.Utils;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
public class CartController {

    final
    ProductRepository productRepository;
    final
    OrderService orderService;

    @Autowired
    public CartController(ProductRepository productRepository, OrderService orderService) {
        this.productRepository = productRepository;
        this.orderService = orderService;
    }


    @GetMapping("cart")
    public Cart shoppingCartHandler(HttpServletRequest request) {
        Cart myCart = Utils.getCartInSession(request);
        return myCart;
    }

    @GetMapping("/cart/{id}")
    public Cart listProductHandler(HttpServletRequest request, //
                                   @PathVariable Long id) {
        Product product = null;
        Cart cart = Utils.getCartInSession(request);
        if (id != null) {
            product = productRepository.findById(id).get(); // moze trzeba findByCode
        }
        if (product != null) {
//            Cart cart = Utils.getCartInSession(request);
            cart.addProduct(product, 1);
        }
        return cart;
    }

    @DeleteMapping("cart/{id}")
    public Cart removeProductHandler(HttpServletRequest request, //
                                       @PathVariable Long id) {
        Product product = null;
        Cart cart = Utils.getCartInSession(request);
        if (id != null ) {
            product = productRepository.findById(id).get();
        }
        if (product != null) {

            cart.removeProduct(product);

        }

        return cart;
    }


    @PutMapping("cart")
    public String shoppingCartUpdateQty(HttpServletRequest request, //
                                        @RequestBody Cart cartForm) {
        Cart cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);
        return "";
    }
    @PutMapping("cart/{id}")
    public Cart chooseDel(HttpServletRequest request, //
                                        @PathVariable Long id) {
        Cart cartInfo = Utils.getCartInSession(request);
        cartInfo.setDeliveryId(id);
        return cartInfo;
    }



    // POST: Submit Cart (Save)
    @GetMapping("cart/rem")
    public String shoppingCartConfirmationSave(HttpServletRequest request) {
        Cart cart = Utils.getCartInSession(request);

//        if (cart.isEmpty()) {
//
//            return "redirect:/shoppingCart";
//        } else if (!cart.isValidCustomer()) {
//
//            return "redirect:/shoppingCartCustomer";
//        }
        try {
            orderService.saveOrder(cart);
        } catch (Exception e) {

            e.printStackTrace();
            return "bad";
        }
finally {
            System.out.println(cart);
            Utils.removeCartInSession(request);

            // Store last cart.
            Utils.storeLastOrderedCartInSession(request, cart);
        }
        // Remove Cart from Session

        return "good";
    }
}
