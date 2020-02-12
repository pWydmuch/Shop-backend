package wydmuch.patryk.psw2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import wydmuch.patryk.psw2.entity.Product;
import wydmuch.patryk.psw2.model.Cart;
import wydmuch.patryk.psw2.model.CartWrapper;
import wydmuch.patryk.psw2.repositories.ProductRepository;
import wydmuch.patryk.psw2.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

@RestController
public class CartController {

    final
    ProductRepository productRepository;

    final
    OrderService orderService;

    final
    CartWrapper cartWrapper;

    @Autowired
    public CartController(ProductRepository productRepository, OrderService orderService, CartWrapper cart) {
        this.productRepository = productRepository;
        this.orderService = orderService;
        this.cartWrapper = cart;
    }

    @GetMapping("cart")
    public Cart shoppingCartHandler() {
        return cartWrapper.getCart();
    }

    @GetMapping("/cart/{id}")
    public Cart listProductHandler(@PathVariable Long id) {
        Product product = null;
        Cart cart = cartWrapper.getCart();
        if (id != null) {
            product = productRepository.findById(id).get(); // moze trzeba findByCode
        }
        if (product != null) {
            cart.addProduct(product, 1);
        }
        return cart;
    }

    @DeleteMapping("cart/{id}")
    public Cart removeProductHandler(@PathVariable Long id) {
        Product product = null;
        Cart cart = cartWrapper.getCart();
        if (id != null) {
            product = productRepository.findById(id).get();
        }
        if (product != null) {

            cart.removeProduct(product);

        }

        return cart;
    }


    @PutMapping("cart")
    public String shoppingCartUpdateQty(@RequestBody Cart cartForm) {
        Cart cart = cartWrapper.getCart();
        cart.updateQuantity(cartForm);
        return "";
    }

    @PutMapping("cart/{id}")
    public Cart chooseDel(@PathVariable Long id) {
        Cart cart = cartWrapper.getCart();
        cart.setDeliveryId(id);
        return cart;
    }


    @GetMapping("cart/rem")
    public String shoppingCartConfirmationSave(HttpSession session) {

        Cart cart = cartWrapper.getCart();
        try {
            orderService.saveOrder(cart);
        } catch (Exception e) {

            e.printStackTrace();
            return "bad";
        }
        finally {
            System.out.println(cart);
            session.invalidate();
        }


        return "good";
    }
}
