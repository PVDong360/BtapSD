package TemplateMethod;

public class OnlineOrderProcessor extends OrderProcessor {

    @Override
    protected void processPayment(Order order) {
        System.out.println("4. Thanh toán trực tuyến");
        System.out.println("   Kết nối ngân hàng / ví điện tử...");
        System.out.println("   Thanh toán thành công!");
    }
}
