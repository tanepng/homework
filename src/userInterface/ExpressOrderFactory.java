package userInterface;

// 快速快递工厂
public class ExpressOrderFactory implements IOrderFactory {
    @Override
    public Order createOrder(double distance) {
        double price = distance * 2.0; // 快速快递价格是普通的两倍
        return new Order(price);
    }
}