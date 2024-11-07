package userInterface;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import com.tp.jdbc.*;

public class LogisticsSystem {
    public boolean registBasicUser(String username,String password){//正常注册
        BasicUser basicUser = new BasicUser(username,password);
        basicUser.register();
        return basicUser.getflage();
    }
    public boolean registVerifyUserDecorator(String username,String password){//有验证的注册
        BasicUser basicUser = new BasicUser(username,password);
        VerifyUserDecorator verifyUserDecorator = new VerifyUserDecorator(basicUser);
        verifyUserDecorator.register();
        return verifyUserDecorator.getFlage();
    }
    public boolean login(String username,String password){//登录
        BasicUser basicUser = new BasicUser(username,password);
        basicUser.login();
        return basicUser.getflage();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LogisticsSystem l = new LogisticsSystem();
        System.out.println("欢迎使用物流管理系统");
        System.out.println("请您选择注册新的账户还是直接登录:1.注册 2.登录");
        int flag = scanner.nextInt();
        boolean judege1 = true;
        if (flag == 1){
            while(judege1){
                System.out.println("请输入您的用户名:");
                String username = scanner.next();
                System.out.println("请输入您的密码");
                String password = scanner.next();
                judege1 = !l.registBasicUser(username,password);
            }
        }else{
            while(judege1){
                System.out.println("请输入您的用户名:");
                String username = scanner.next();
                System.out.println("请输入您的密码");
                String password = scanner.next();
                judege1 = !l.login(username,password);
            }

        }
        System.out.println("请选择: 1. 确认下单  2. 取消下单");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 吃掉换行符，避免Scanner问题

        if (choice == 1) {
            // 选择快递型号
            System.out.println("请选择快递型号: 1. 普通  2. 快速  3. 加急");
            int type = scanner.nextInt();
            scanner.nextLine(); // 吃掉换行符，避免Scanner问题

            // 输入距离并判断是否合法
            double distance = -1;  // 初始化为无效值
            while (distance < 0 || distance > 3000) {
                System.out.println("请输入运输距离 (公里) (0到3000公里之间): ");
                distance = scanner.nextDouble();
                scanner.nextLine(); // 吃掉换行符，避免Scanner问题

                if (distance < 0 || distance > 3000) {
                    System.out.println("无效的距离，请输入0到3000公里之间的有效值！");
                }
            }

            // 根据选择的快递类型使用相应的工厂生成订单
            IOrderFactory factory;
            if (type == 1) {
                factory = new StandardOrderFactory();
            } else if (type == 2) {
                factory = new ExpressOrderFactory();
            } else {
                factory = new UrgentOrderFactory();
            }

            Order order = factory.createOrder(distance);

            // 创建观察者
            CustomerObserver customer = new CustomerObserver();
            order.addObserver(customer);

            // 初始状态：已下单
            System.out.println("您的订单已创建，订单号为: " + order.getOrderNumber());
            order.notifyObservers();

            // 使用定时器模拟订单状态更新
            Timer timer = new Timer();

            // 运输中状态
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    order.updateStatus(OrderStatus.SHIPPING);
                }
            }, 3000); // 3秒后更新为运输中

            // 已到达状态
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    order.updateStatus(OrderStatus.DELIVERED);
                }
            }, 6000); // 6秒后更新为已到达

            // 主线程等待运输完成，然后获取用户反馈
            try {
                Thread.sleep(6000); // 等待6秒，让定时器任务执行完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 获取用户反馈
            customer.getFeedback(scanner); // 传递Scanner到客户反馈

        } else if (choice == 2) {
            System.out.println("订单已取消，程序退出。");
            System.exit(0);
        } else {
            System.out.println("无效选择，程序退出。");
            System.exit(0);
        }

        scanner.close();


    }


}

