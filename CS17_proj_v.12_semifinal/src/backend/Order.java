package backend;

import java.util.Date;


public class Order {
    
    private int id;
    private Date orderDate;
    private double total_amount;
    private Customer customer;
    private OrderedItem[] items;

    public Order(Date orderDate, double total_amount, Customer customer) {

        this.orderDate = orderDate;
        this.total_amount = total_amount;
        this.customer = customer;
    }

    public Order(int id) {
        this.id = id;
    }

    public Order(int id, double total_amount) {
        this.id = id;
        this.total_amount = total_amount;
    }

    public Order(int orderID, Date orderDate, double total, Customer customer) {
        this.id = orderID;
        this.orderDate = orderDate;
        this.total_amount = total;
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    
    public double getTotal_amount() {
        return total_amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderedItem[] getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public static class OrderedItem {

        private Item item;
        private Order order;
        private int quantity;
        private double orderprice;
        private double subtotal;

        public OrderedItem(Item item, Order order, int quantity, double orderprice, double subtotal) {
            this.item = item;
            this.order = order;
            this.quantity = quantity;
            this.orderprice = orderprice;
            this.subtotal = subtotal;
        }

        public OrderedItem(Item item, Order order, int quantity, double orderprice) {
            this.item = item;
            this.order = order;
            this.quantity = quantity;
            this.orderprice = orderprice;
        }

        public OrderedItem(Item item) {
            this.item = item;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getOrderPrice() {
            return orderprice;
        }
        
        public double getSubtotal() {
            return subtotal;
        }

        public Item getItem() {
            return item;
        }

        public Order getOrder() {
            return order;
        }
        
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    
        public void setOrderPrice(double quantity) {
            this.orderprice = quantity;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public void setOrder(Order order) {
            this.order = order;
        }
    }
    
    public static class Payment {
        private Date payment_date;
        private double payment_amount;
        private Order order;
    
        public Payment(Date payment_date, double payment_amount, Order order) {
            this.payment_date = payment_date;
            this.payment_amount = payment_amount;
            this.order = order;
        }
        
        public Date getPayment_date() {
            return payment_date;
        }
    
        public double getPayment_amount() {
            return payment_amount;
        }

        public Order getOrder() {
            return order;
        }
       
    }
}
