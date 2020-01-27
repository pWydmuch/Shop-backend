package wydmuch.patryk.psw2.model;

import wydmuch.patryk.psw2.entity.Product;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem() {
        this.quantity = 0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productInfo) {
        this.product = productInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return this.product.getPrice() * this.quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
