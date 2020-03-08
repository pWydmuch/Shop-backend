package wydmuch.patryk.psw2.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import wydmuch.patryk.psw2.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private int orderNum;



    private final List<CartItem> cartItems = new ArrayList<CartItem>();

    public Cart() {

    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }


    private Long deliveryId;

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }



    public List<CartItem> getCartItems() {
        return this.cartItems;
    }

    private CartItem findItemByCode(Long id) {
        for (CartItem item : this.cartItems) {
            if (item.getProduct().getId()==id ){
                return item;
            }
        }
        return null;
    }

    public void addProduct(Product product, int quantity) {
        CartItem item = this.findItemByCode(product.getId());

        if (item == null) {
            item = new CartItem();
            item.setQuantity(0);
            item.setProduct(product);
            this.cartItems.add(item);
        }
        int newQuantity = item.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartItems.remove(item);
        } else {
            item.setQuantity(newQuantity);
        }
    }

    public void validate() {

    }

    public void updateProduct(Long id, int quantity) {
        CartItem item = this.findItemByCode(id);

        if (item != null) {
            if (quantity <= 0) {
                this.cartItems.remove(item);
            } else {
                item.setQuantity(quantity); //quantity ++
            }
        }
    }

    public void removeProduct(Product product) {
        CartItem item = this.findItemByCode(product.getId());
        if (item != null) {
            this.cartItems.remove(item);
        }
    }

    public void removeAllProducts() {
       cartItems.clear();
    }

    public boolean isEmpty() {
        return this.cartItems.isEmpty();
    }



    public int getQuantityTotal() {
        int quantity = 0;
        for (CartItem item : this.cartItems) {
            quantity += item.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartItem item : this.cartItems) {
            total += item.getAmount();
        }
        return total;
    }

    public void updateQuantity(Cart cartForm) {
        if (cartForm != null) {
            List<CartItem> items = cartForm.getCartItems();
            for (CartItem item : items) {
                this.updateProduct(item.getProduct().getId(), item.getQuantity());
            }
        }

    }

    @Override
    public String toString() {
        return "Cart{" +
                "orderNum=" + orderNum +
                ", cartItems=" + cartItems +
                ", deliveryId=" + deliveryId +
                '}';
    }
}
