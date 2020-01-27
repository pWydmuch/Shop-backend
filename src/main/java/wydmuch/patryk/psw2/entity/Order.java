package wydmuch.patryk.psw2.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Order  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Order_Date")
    private Date orderDate;

    @Column(name = "Order_Num")
    private int orderNum;

    @Column(name = "Amount")
    private double amount;


    private Long delivery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getDelivery() {
        return delivery;
    }

    public void setDelivery(Long delivery) {
        this.delivery = delivery;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
