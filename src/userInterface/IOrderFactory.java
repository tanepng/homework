package userInterface;

// 抽象工厂接口
public interface IOrderFactory {
    Order createOrder(double distance);
}