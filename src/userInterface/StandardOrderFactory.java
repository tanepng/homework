package userInterface;

// 普通快递工厂
public class StandardOrderFactory implements IOrderFactory {
    @Override
    public Order createOrder(double distance) {
        double price = distance * 1.0; // 价格按距离计算，每公里1美元
        return new Order(price);
    }
}