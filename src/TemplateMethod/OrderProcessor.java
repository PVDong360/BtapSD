package TemplateMethod;

public abstract class OrderProcessor {

    public final void processOrder(Order order) {

        System.out.println("- XỬ LÝ ĐƠN HÀNG:");

        receiveOrder(order);

        if (!validateOrder(order)) {
            System.out.println("- Đơn không hợp lệ!");
            return;
        }

        calculateTotal(order);

        processPayment(order);

        createInvoice(order);

        sendNotification(order);

        System.out.println("- Hoàn tất xử lý đơn!");
    }
    
    private void receiveOrder(Order order) {
        System.out.println("1. Tiếp nhận đơn: "
                + order.getOrderId());
    }

    private boolean validateOrder(Order order) {
        System.out.println("2. Kiểm tra thông tin đơn");
        return order.getTotalAmount() > 0;
    }

    private void calculateTotal(Order order) {
        System.out.println("3. Tổng tiền đơn: "
                + order.getTotalAmount() + " VND");
    }

    private void createInvoice(Order order) {
        System.out.println("5. Tạo hóa đơn cho đơn: "
                + order.getOrderId());
    }

    private void sendNotification(Order order) {
        System.out.println("6. Gửi thông báo xác nhận đơn");
    }

    protected abstract void processPayment(Order order);
}
