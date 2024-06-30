package backend;

import java.util.Date;

public class Event {

    private String desc;
    private int id;
    private EventPackage[] packages;

    public Event(String desc) {
        this.desc = desc;
    }

    public Event(int id) {
        this.id = id;
    }

    public Event(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Event() {
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

    public EventPackage[] getPackages() {
        return packages;
    }

    public EventPackage getPackage(int index) {
        return this.packages[index];
    }

    public void setPackages(EventPackage[] packages) {
        this.packages = packages;
    }

    public static class EventPackage {

        private int id;
        private String desc;
        private double price;
        private Event event;
        private IncludedItem[] includedItems;

        public EventPackage(int id) {
            this.id = id;
        }

        public EventPackage(int id, String desc, double price, Event event) {

            this.id = id;
            this.desc = desc;
            this.price = price;
            this.event = event;
        }

        EventPackage(String desc) {
            this.desc = desc;
        }

        public EventPackage() {
        }

        public int getId() {
            return id;
        }

        public String getDesc() {
            return desc;
        }

        public double getPrice() {
            return price;
        }

        public Event getEvent() {
            return event;
        }

        public void setEvent(Event event) {
            this.event = event;
        }

        public IncludedItem[] getIncludedItems() {
            return includedItems;
        }

        public void setIncludedItems(IncludedItem[] includedItems) {
            this.includedItems = includedItems;
        }

        public static class IncludedItem extends Item {

            private EventPackage eventPackage;
            private int quantity;

            public IncludedItem(EventPackage eventPackage, Item item, int quantity) {
                super(item.getId(), item.getDesc(), item.getStock(), item.getRetail_price());
                this.eventPackage = eventPackage;
                this.quantity = quantity;
            }

            public IncludedItem(int itemID, int quantity) {
                super(itemID);
            }

            public EventPackage getEventPackage() {
                return eventPackage;
            }

            public int getQuantity() {
                return quantity;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                IncludedItem other = (IncludedItem) obj;
                return super.getId() == other.getId() && eventPackage.getId() == other.getEventPackage().getId();
            }

        }
    }

    public static class EventSchedule {

        private Date date;
        private Event event;
        private Customer customer;
        private EventPackage eventpackage;
        private Order order;

        public EventSchedule(Date date, Event event, Customer customer, EventPackage eventpackage, Order order) {

            this.date = date;
            this.event = event;
            this.customer = customer;
            this.eventpackage = eventpackage;
            this.order = order;
        }

        public EventSchedule(Date date, Event event, Customer customer,
                EventPackage eventpackage) {
            this.date = date;
            this.event = event;
            this.customer = customer;
            this.eventpackage = eventpackage;
        }

        public EventSchedule(Customer new_cust) {
            this.customer = new_cust;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public Customer getCustomer() {
            return customer;
        }

        public EventPackage getEventpackage() {
            return eventpackage;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public void setEvent(Event event) {
            this.event = event;
        }

        public void setEventpackage(EventPackage eventpackage) {
            this.eventpackage = eventpackage;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public static class AdditionalItem extends Item {

            private double additional_price = 0;
            private double subtotal_price = 0;
            private int quantity = 0;
            private EventSchedule schedule = null;

            public AdditionalItem(int id, String desc, int stock, double retail_price) {
                super(id, desc, stock, retail_price);
                this.additional_price = retail_price;
            }

            public AdditionalItem(Item item, int quantity, double additional_price, double subtotal_price) {
                super(item.getId());

                this.additional_price = additional_price;
                this.subtotal_price = subtotal_price;
                this.quantity = quantity;
            }

            public AdditionalItem(Item item, String desc, int stock, int quantity, double additional_price) {
                super(quantity, desc, stock);
                this.additional_price = additional_price;
            }

            public int getQuantity() {
                return quantity;
            }

            public double getAdditional_price() {
                return additional_price;
            }

            public double getSubtotal_price() {
                return subtotal_price;
            }

            public EventSchedule getSchedule() {
                return schedule;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public void setAdditional_price(double additional_price) {
                this.additional_price = additional_price;
            }

            public void setSubtotal_price(double subtotal_price) {
                this.subtotal_price = subtotal_price;
            }

            public void setEventdate(EventSchedule schedule) {
                this.schedule = schedule;
            }

        }
    }

}
