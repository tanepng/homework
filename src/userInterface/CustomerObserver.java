package userInterface;

import java.util.Scanner;

// 客户观察者类
class CustomerObserver implements IObserver {

    @Override
    public void update(String message) {
        System.out.println(message);
    }

    public void getFeedback(Scanner scanner) {
        System.out.println("请对物流服务做出反馈: 1. good  2. bad  3. just so so");
        int feedback = scanner.nextInt();
        handleFeedback(feedback);

        // 在反馈后终止程序
        System.out.println("感谢您的反馈，程序即将结束！");
        System.exit(0);  // 结束程序
    }

    private void handleFeedback(int feedback) {
        switch (feedback) {
            case 1:
                System.out.println("您选择了 good，感谢您的支持！");
                break;
            case 2:
                System.out.println("您选择了 bad，抱歉给您带来不便！");
                break;
            case 3:
                System.out.println("您选择了 just so so，我们会继续改进！");
                break;
            default:
                System.out.println("无效的反馈。");
        }
    }
}

