package wydmuch.patryk.psw2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wydmuch.patryk.psw2.entity.Product;
import wydmuch.patryk.psw2.model.Cart;
import wydmuch.patryk.psw2.model.CartWrapper;
import wydmuch.patryk.psw2.repositories.ProductRepository;
import wydmuch.patryk.psw2.services.OrderService;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = {"http://localhost:4200", "http://487a086c.ngrok.io"}, allowCredentials = "true")

@RestController
public class CartController {

    private final
    ProductRepository productRepository;

    private final
    OrderService orderService;

    private final
    CartWrapper cartWrapper;

    @Autowired
    public CartController(ProductRepository productRepository, OrderService orderService, CartWrapper cart) {
        this.productRepository = productRepository;
        this.orderService = orderService;
        this.cartWrapper = cart;
    }

    @GetMapping("cart")
    public Cart shoppingCartHandler() {

        System.out.println(cartWrapper.getCart());
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

    @PutMapping("cart/delivery/{id}")
    public Cart chooseDelivery(@PathVariable Long id) {
        Cart cart = cartWrapper.getCart();
        cart.setDeliveryId(id);
        return cart;
    }
    @DeleteMapping("cart/delivery")
    public Cart deleteDelivery() {
        Cart cart = cartWrapper.getCart();
        cart.deleteDelivery();
        return cart;
    }


    @GetMapping("/order")
    public Cart shoppingCartConfirmationSave() {
        Cart cart = cartWrapper.getCart();
        try {
            orderService.saveOrder(cart);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cart.removeAllProducts();

        }
        return cart;
    }

    @DeleteMapping("cart")
    public Cart clearCart() {
        Cart cart = cartWrapper.getCart();
        cart.removeAllProducts();
        return cart;
    }

}

