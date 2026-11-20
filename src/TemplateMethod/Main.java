package TemplateMethod;

public class Main {

    public static void main(String[] args) {

        Order order1 = new Order("ORD001", 500000);
        Order order2 = new Order("ORD002", 750000);
        Order order3 = new Order("ORD003", 0);

        OrderProcessor onlineProcessor =
                new OnlineOrderProcessor();

        System.out.println("+ Đơn số 1");
        onlineProcessor.processOrder(order1);

        OrderProcessor codProcessor =
                new CODOrderProcessor();

        System.out.println("\n+ Đơn số 2");
        codProcessor.processOrder(order2);
        
        System.out.println("\n+ Đơn số 3");
        onlineProcessor.processOrder(order3);
    }
}
