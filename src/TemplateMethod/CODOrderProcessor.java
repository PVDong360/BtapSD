package TemplateMethod;

public class CODOrderProcessor extends OrderProcessor {

    @Override
    protected void processPayment(Order order) {
        System.out.println("4. Thanh toán khi nhận hàng (COD)");
        System.out.println("   Đơn hàng được chuyển cho đơn vị vận chuyển.");
        System.out.println("   Khách hàng thanh toán khi nhận hàng.");
    }
}
