package userInterface;

// 加急快递工厂
public class UrgentOrderFactory implements IOrderFactory {
    @Override
    public Order createOrder(double distance) {
        double price = distance * 3.0; // 加急快递价格是普通的三倍
        return new Order(price);
    }
}