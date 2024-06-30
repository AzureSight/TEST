package backend;

import java.sql.Date;

import backend.User.Manager;

public class Item {

    private int id;
    private String desc;
    private int stock;
    private double wholesale_price;
    private double retail_price;
    private StockIn[] instocks;
    private StockOut[] outstocks;

    public Item(String desc, int stock, double wholesale_price, double retail_price) {

        this.desc = desc;
        this.stock = stock;
        this.wholesale_price = wholesale_price;
        this.retail_price = retail_price;
    }

    public Item(int id, String desc, int stock, double wholesale_price, double retail_price) {
        this.id = id;
        this.desc = desc;
        this.stock = stock;
        this.wholesale_price = wholesale_price;
        this.retail_price = retail_price;
    }

    public Item(int id, String desc, double wholesale_price, double retail_price) {
        this.id = id;
        this.desc = desc;
        this.wholesale_price = wholesale_price;
        this.retail_price = retail_price;
    }

    public Item(int id, String desc, int stock, double retail_price) {
        this.id = id;
        this.desc = desc;
        this.stock = stock;
        this.retail_price = retail_price;
    }

    public Item(int id) {
        this.id = id;
    }

    public Item(String desc, double w_price, double r_price) {
        this.desc = desc;
        this.wholesale_price = w_price;
        this.retail_price = r_price;
    }

    public Item(int id, String desc, int stock) {
        this.id = id;
        this.desc = desc;
        this.stock = stock;
    }

    public Item(int id, String desc, double retail_price) {
        this.id = id;
        this.desc = desc;
        this.retail_price = retail_price;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStock(int quantity) {
        this.stock = quantity;
    }

    public void setWholesale_price(double price) {
        this.wholesale_price = price;
    }

    public void setRetail_price(double retail_price) {
        this.retail_price = retail_price;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public int getStock() {
        return stock;
    }

    public double getWholesale_price() {
        return wholesale_price;
    }

    public double getRetail_price() {
        return retail_price;
    }

    public StockIn[] getInstocks() {
        return instocks;
    }

    public StockOut[] getOutstocks() {
        return outstocks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return this.id == other.id;
    }

    public class StockIn {

        private int id;
        private Date stockInDate;
        private int quantity;
        private Item item;
        private Manager manager;

        public StockIn(Date stockInDate, int quantity, Item item, Manager manager) {

            this.stockInDate = stockInDate;
            this.quantity = quantity;
            this.item = item;
            this.manager = manager;
        }

        public StockIn(int quantity, Item item) {
            this.quantity = quantity;
            this.item = item;
        }

        public StockIn(int id) {
            this.id = id;
        }

        public Date getStockInDate() {
            return stockInDate;
        }

        public int getQuantity() {
            return quantity;
        }

        public Item getItem() {
            return item;
        }

        public Manager getManager() {
            return manager;
        }

        public int getId() {
            return id;
        }
    }

    public class StockOut {

        private int id;
        private Date stockOutDate;
        private int quantity;
        private Item item;
        private Order order;
        private Cause cause;
        private User user;

        public StockOut(Date stockInDate, int quantity, Item item, Order order) {

            this.stockOutDate = stockInDate;
            this.quantity = quantity;
        }

        public StockOut(Item item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        public StockOut(int id) {
            this.id = id;
        }

        public Date getStockInDate() {
            return stockOutDate;
        }

        public int getQuantity() {
            return quantity;
        }

        public Item getItem() {
            return item;
        }

        public Order getOrder() {
            return order;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Cause getCause() {
            return cause;
        }

        public void setCause(Cause cause) {
            this.cause = cause;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }

    public class Supplier {
        private int id = -1;
        private String name = "";

        public Supplier(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Supplier(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

    }

    public class Cause {
        private int id = -1;
        private String causedesc = "";

        public Cause(int id, String causedesc) {
            this.id = id;
            this.causedesc = causedesc;
        }

        public Cause(String causedesc) {
            this.causedesc = causedesc;
        }

        public String getCausedesc() {
            return causedesc;
        }

        public int getId() {
            return id;
        }
    }
}
