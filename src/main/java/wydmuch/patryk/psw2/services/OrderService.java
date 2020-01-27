package wydmuch.patryk.psw2.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wydmuch.patryk.psw2.entity.Order;
import wydmuch.patryk.psw2.entity.OrderDetail;
import wydmuch.patryk.psw2.entity.Product;
import wydmuch.patryk.psw2.model.Cart;
import wydmuch.patryk.psw2.model.CartItem;
import wydmuch.patryk.psw2.repositories.OrderDetailRepository;
import wydmuch.patryk.psw2.repositories.OrderRepository;
import wydmuch.patryk.psw2.repositories.ProductRepository;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
    }


    public void saveOrder(Cart cart){
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setAmount(cart.getAmountTotal());
        order.setDelivery(cart.getDeliveryId());

        List<CartItem> items = cart.getCartItems();

        orderRepository.save(order);
        for (CartItem item : items) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setAmount(item.getAmount());
            detail.setPrice(item.getProduct().getPrice());
            detail.setQuanity(item.getQuantity());


            Long id = item.getProduct().getId();
            Product product = this.productRepository.findById(id).get();
            detail.setProduct(product);
            orderDetailRepository.save(detail);

        }




    }
}
