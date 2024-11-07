package userInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 订单类 (被观察者)
public class Order {
    private List<IObserver> observers = new ArrayList<>();
    private OrderStatus status;
    private String orderNumber;
    private double price;

    public Order(double price) {
        this.orderNumber = generateOrderNumber();
        this.status = OrderStatus.PLACED; // 初始状态为"已下单"
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update("订单号: " + orderNumber + " 的状态变为: " + getStatusDescription() + "，价格: $" + price);
        }
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
        notifyObservers();
    }

    private String generateOrderNumber() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000; // 生成100000到999999之间的数字
        return String.valueOf(number);
    }

    private String getStatusDescription() {
        switch (status) {
            case PLACED:
                return "已下单";
            case SHIPPING:
                return "运输中";
            case DELIVERED:
                return "已到达，请取件";
            default:
                return "未知状态";
        }
    }
}