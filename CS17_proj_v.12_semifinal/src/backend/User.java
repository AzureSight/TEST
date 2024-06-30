package backend;

import java.awt.HeadlessException;
import java.text.DecimalFormat;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import backend.Event.EventPackage;
import backend.Event.EventSchedule;
import backend.Event.EventSchedule.AdditionalItem;
import backend.Event.EventPackage.IncludedItem;
import backend.Item.Cause;
import backend.Item.StockIn;
import backend.Item.StockOut;
import backend.Item.Supplier;
import backend.Order.OrderedItem;
import backend.Order.Payment;

public class User {

    private Connection conn = null;
    private PreparedStatement pst = null;
    private CallableStatement cst = null;
    private ResultSet rs = null;

    private int id;
    private String fname, lname, username, password;
    private UserType type;
    private Date date;

    public User() {

    }

    // deleteUser constructor
    public User(int id) {
        this.id = id;
    }

    // loginUser constructor
    public User(String username, String password, Date date) {
        this.username = username;
        this.password = password;
        this.date = date;
        this.type = new UserType();
        conn = db.java_db();
    }

    public User(int id, String fname, String lname, UserType type) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.type = type;
        conn = db.java_db();
    }

    public User(String fname, String lname, String username, String password, UserType type) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    // updateUser constructor
    public User(int id, String fname, String lname, String username, String password, UserType type) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(Manager manager) {
        this.id = manager.getId();
        this.fname = manager.getFname();
        this.lname = manager.getLname();
        this.username = manager.getUsername();
    }

    public User(Employee employee) {
        this.id = employee.getId();
        this.fname = employee.getFname();
        this.lname = employee.getLname();
        this.username = employee.getUsername();
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getType() {
        return type;
    }

    public boolean login() {
        if (conn == null)
            return false;
        else
            try {
                String checkpassword = "", sql = "SELECT * FROM user WHERE username = '" + username + "'";
                boolean access = false;
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    checkpassword = rs.getString("password");
                    if (checkpassword.equals(password)) {
                        access = true;
                        this.type.setId(rs.getInt("usertypeID"));
                        this.fname = rs.getString("fname");
                        this.lname = rs.getString("lname");
                        this.username = rs.getString("username");
                        this.id = rs.getInt("userID");
                    }
                }

                boolean isArchived = false;

                if (rs.next())
                    isArchived = rs.getBoolean("isArchived");

                if (isArchived) {
                    access = false;
                    JOptionPane.showMessageDialog(null, "This user has been deactivated!", "Notice", 1);
                } else {
                    if (access == true) {
                        if (date != null) {
                            sql = "SELECT type_desc FROM usertype WHERE usertypeID = '" + this.type.getId() + "' ";
                            pst = conn.prepareStatement(sql);
                            rs = pst.executeQuery();
                            if (rs.next())
                                this.type.setDesc(rs.getString("type_desc"));
                            JOptionPane.showMessageDialog(null, "Welcome " + username, "Login Successful!", 1);
                            sql = "UPDATE user SET last_active = '" + date + "' WHERE userID = '" + id + "' ";
                            pst = conn.prepareStatement(sql);
                            pst.executeUpdate();
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Incorrect username or password", "Login Failed!", 2);
                }
                return access;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error occured!", 0);
                return false;
            } finally {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                }
            }

    }

    public void insertPayment(Payment payment, int causeID)
            throws SQLException {
        try {
            conn.setAutoCommit(false);
            String sql = "{CALL `orderpayment_transaction`(?,?,?,?,?)}";
            cst = conn.prepareCall(sql);
            cst.setDouble(1, payment.getPayment_amount());
            cst.setInt(2, payment.getOrder().getId());
            cst.setInt(3, causeID);
            cst.setDouble(4, payment.getOrder().getTotal_amount());
            cst.setInt(5, this.id);
            cst.execute();
            JOptionPane.showMessageDialog(null, "Order Added: Payment Transaction Successful!", "Transaction", 1);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Transaction Failed!\n" + e.getMessage(), "Payment Processing", 0);
            conn.rollback();
        } finally {
            try {
                cst.close();
            } catch (Exception e) {
            }
        }
    }

    public void insertPayment(Payment payment, int causeID, int eventPackageID, java.util.Date eventDate)
            throws SQLException {
        try {
            conn.setAutoCommit(false);
            String sql = "{CALL `includedpayment_transaction`(?,?,?,?,?,?)}";
            cst = conn.prepareCall(sql);
            cst.setDouble(1, payment.getPayment_amount());
            cst.setInt(2, causeID);
            cst.setDouble(3, payment.getOrder().getTotal_amount());
            cst.setInt(4, eventPackageID);
            cst.setDate(5, new Date(eventDate.getTime()));
            cst.setInt(6, this.id);
            cst.execute();
            JOptionPane.showMessageDialog(null, "Included Items Added: Payment Transaction Successful!", "Transaction",
                    1);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Transaction Failed!\n" + e.getMessage(), "Payment Processing", 0);
            conn.rollback();
        } finally {
            try {
                cst.close();
            } catch (Exception e) {
            }
        }
    }

    public void insertPayment(Payment payment, int causeID, java.util.Date eventdate)
            throws SQLException {
        try {
            conn.setAutoCommit(false);
            String sql = "{CALL `additionalpayment_transaction`(?,?,?,?,?)}";
            cst = conn.prepareCall(sql);
            cst.setDouble(1, payment.getPayment_amount());
            cst.setInt(2, causeID);
            cst.setDouble(3, payment.getOrder().getTotal_amount());
            cst.setDate(4, new Date(eventdate.getTime()));
            cst.setInt(5, this.id);
            cst.execute();
            JOptionPane.showMessageDialog(null, "Additional Items Added: Payment Transaction Successful!",
                    "Transaction", 1);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Transaction Failed!\n" + e.getMessage(), "Payment Processing", 0);
            conn.rollback();
        } finally {
            try {
                cst.close();
            } catch (Exception e) {
            }
        }
    }

    public void takeAdditionalItems(AdditionalItem additionalitem) throws SQLException {
        try {
            conn.setAutoCommit(false);
            String sql = "{CALL `take_additionalItems`(?,?,?,?)}";
            cst = conn.prepareCall(sql);
            cst.setDate(1, new Date(additionalitem.getSchedule().getDate().getTime()));
            cst.setInt(2, additionalitem.getId());
            cst.setInt(3, additionalitem.getQuantity());
            cst.setDouble(4, additionalitem.getAdditional_price());
            cst.execute();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e.getMessage(),
                    "Additional Order Processing", 0);
            conn.rollback();
        } finally {
            try {
                cst.close();
            } catch (Exception e) {
            }
            ;
        }
    }

    public int newCustomerEvent(Customer customer, EventSchedule schedule) throws SQLException {
        int orderID = -1;
        try {
            conn.setAutoCommit(false);
            String sql = "{CALL `newcust_schedule_transaction`(?,?,?,?,?,?,?,?,?,?)}";
            cst = conn.prepareCall(sql);
            cst.setString(1, customer.getFname());
            cst.setString(2, customer.getLname());
            cst.setString(3, customer.getEmail());
            cst.setString(4, customer.getContactnum());
            cst.setInt(5, customer.getEventOrganizer().getId());
            cst.setInt(6, schedule.getEvent().getId());
            cst.setInt(7, schedule.getEventpackage().getId());
            cst.setDate(8, new Date(schedule.getDate().getTime()));
            cst.setDouble(9, schedule.getOrder().getTotal_amount());
            cst.registerOutParameter(10, Types.INTEGER);
            cst.execute();
            orderID = cst.getInt(10);
            JOptionPane.showMessageDialog(null, "Event Schedule Added Successfully!", "New Event Schedule", 1);
            conn.setAutoCommit(true);
            return orderID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Transaction Failed!\n" + e.getMessage(), "New Event Schedule", 0);
            conn.rollback();
            return -1;
        } finally {
            try {
                cst.close();
            } catch (Exception e) {
            }
        }
    }

    public int regCustomerEvent(Customer customer, EventSchedule schedule) throws SQLException {
        int orderID = -1;
        try {
            conn.setAutoCommit(false);
            String sql = "{CALL `regcust_schedule_transaction`(?,?,?,?,?,?)}";
            cst = conn.prepareCall(sql);
            cst.setInt(1, customer.getId());
            cst.setInt(2, schedule.getEvent().getId());
            cst.setInt(3, schedule.getEventpackage().getId());
            cst.setDate(4, new Date(schedule.getDate().getTime()));
            cst.setDouble(5, schedule.getOrder().getTotal_amount());
            cst.registerOutParameter(6, Types.INTEGER);
            cst.execute();
            orderID = cst.getInt(6);
            JOptionPane.showMessageDialog(null, "Event Schedule Added Successfully!", "New Event Schedule", 1);
            conn.setAutoCommit(true);
            return orderID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Transaction Failed!\n" + e.getMessage(), "New Event Schedule", 0);
            conn.rollback();
            return -1;
        } finally {
            try {
                cst.close();
            } catch (Exception e) {
            }
        }
    }

    public void rescheduleEvent(EventSchedule schedule, java.util.Date old_date) throws SQLException {
        try {
            conn.setAutoCommit(false);
            String sql = "{CALL `reschedule_transaction`(?,?,?,?)}";
            cst = conn.prepareCall(sql);
            cst.setInt(1, schedule.getEvent().getId());
            cst.setInt(2, schedule.getCustomer().getId());
            cst.setDate(3, new Date(old_date.getTime()));
            cst.setDate(4, new Date(schedule.getDate().getTime()));
            cst.execute();
            JOptionPane.showMessageDialog(null, "Reschedule Successful!", "Reschedule Event", 1);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Reschedule Failed!\n" + e.getMessage(), "Reschedule Event", 0);
            conn.rollback();
        } finally {
            try {
                cst.close();
            } catch (Exception e) {
            }
        }
    }

    public void updateEventSchedule(EventSchedule schedule) {

        try {
            String sql = "UPDATE customer SET fname = '" + schedule.getCustomer().getFname() + "', lname = '"
                    + schedule.getCustomer().getLname() + "', email = '" + schedule.getCustomer().getEmail()
                    + "', contactnum = '" + schedule.getCustomer().getContactnum() + "' WHERE cusID = '"
                    + schedule.getCustomer().getId() + "' ";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Updated Successfully!", "Update Event Schedule", 1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Update Failed!\n" + e.getMessage(), "Update Event Schedule", 1);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            String sql = "UPDATE customer SET fname = '" + customer.getFname() + "', lname = '"
                    + customer.getLname() + "', email = '" + customer.getEmail() + "', contactnum = '"
                    + customer.getContactnum() + "', userID = '" + customer.getEventOrganizer() + "' ";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated successfully!", "Update Customer", 1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Update Failed!\n" + e.getMessage(), "Update User", 0);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public void deleteCustomer(Customer customer) {
        try {
            String sql = "DELETE FROM customer WHERE cusID = '" + customer.getId() + "' ";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Removed successfully!", "Remove Customer", 1);
            sql = "ALTER TABLE customer AUTO_INCREMENT = 1";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Remove Failed!\n" + e.getMessage(), "Remove Customer", 0);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public Customer searchRegCustomer(String search) {
        Customer searchCustomer = null;
        try {
            String sql = "SELECT * FROM viewcustomers WHERE Email LIKE '" + search + "' OR `Contact No.` LIKE '"
                    + search + "' LIMIT 1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                searchCustomer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5));
            }
            return searchCustomer;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e.getMessage(), "Search Customer", 0);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel searchCustomer(String search) {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT * FROM viewcustomers WHERE `Customer ID` = '" + search
                    + "' OR `First Name` LIKE '%" + search + "%' OR `Last Name` LIKE '%" + search
                    + "%' OR Email LIKE '%" + search + "%' OR `Contact No.` LIKE '%" + search
                    + "%' OR `Accomodated by` LIKE '%" + search + "%' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colNames = new String[cols];
            for (int i = 0; i < colNames.length; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 1);
            }
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Search Failed!\n" + e.getMessage(), "Search Customer", 0);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public Cause searchCause(String desc) {
        Cause cause = null;
        try {
            String sql = "SELECT * FROM viewcauses WHERE `Description` = '" + desc + "' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next())
                cause = new Item(-1).new Cause(rs.getInt(1), rs.getString(2));
            return cause;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cause not found!\n" + e.getMessage());
            return null;
        }
    }

    public DefaultTableModel viewAllCustomer() {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT * FROM viewcustomers";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colNames = new String[cols];
            for (int i = 0; i < colNames.length; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 1);
            }
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "View Customers", 0);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel viewAllEventSchedules() {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT `Date`, `Event`, `Package`, `Customer's Name`, `Email`, `Contact No.`, `Accomodated by`, `Contract Price`, `Status` FROM `vieweventschedules`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colNames = new String[cols];
            for (int i = 0; i < colNames.length; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 1);
            }
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getDate(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        new DecimalFormat("0.00").format(rs.getDouble(8)),
                        rs.getString(9)
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Event SChedule Table", 1);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }

    }

    public EventSchedule viewIfScheduleBooked(int month, int year, int day) {
        EventSchedule schedule = null;
        try {
            String sql = "SELECT * FROM vieweventschedules WHERE YEAR(`DATE`) = '" + year + "' AND MONTH(`Date`) = '"
                    + month + "' AND DAY(`Date`) = '" + day + "' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                java.util.Date date = new java.util.Date(rs.getDate(1).getTime());
                Event event = new Event(rs.getInt(2), rs.getString(3));
                EventPackage eventpackage = new EventPackage(rs.getString(4));
                Customer customer = new Customer(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        new Employee(rs.getString(9)));
                Order order = new Order(rs.getInt(10), rs.getInt(11));

                schedule = new EventSchedule(date, event, customer, eventpackage, order);
            }
            return schedule;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e.getMessage(), "", 0);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel searchItem(String search) {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT * FROM viewinventory WHERE `Item ID` = '" + search + "' OR Description LIKE '%"
                    + search + "%' OR Stock LIKE '%" + search + "%' OR `Wholesale Price` LIKE '%" + search
                    + "%' OR `Retail Price` LIKE '%" + search + "%' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colNames = new String[cols];
            for (int i = 0; i < colNames.length; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 1);
            }
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        new DecimalFormat("0.00").format(rs.getDouble(4)),
                        new DecimalFormat("0.00").format(rs.getDouble(5))
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Search Failed!\n" + e.getMessage(), "Search Item", 1);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel viewIncludedItems(int eventPackageID) {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT * FROM viewincludeditems WHERE `Package ID` = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, eventPackageID);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colNames = new String[cols - 2];
            for (int i = 0; i < colNames.length; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 3);
            }
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getString(3),
                        rs.getInt(4),
                        new DecimalFormat("0.00").format(rs.getDouble(5))
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "View Included Items", 0);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel searchIncludedItem(int itemID, int eventPackageID) {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT * FROM viewincludeditems WHERE `Item ID` = ? AND `Package ID` = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, itemID);
            pst.setInt(2, eventPackageID);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colNames = new String[cols];
            for (int i = 0; i < colNames.length; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 1);
            }
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        new DecimalFormat("0.00").format(rs.getDouble(5))
                });
            }
            return m;
        } catch (Exception e1) {
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel searchAdditionalItem(AdditionalItem additionalItem) {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT * FROM viewincludeditems WHERE `Item ID` = ? AND `Event Date` = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, additionalItem.getId());
            pst.setDate(2, new Date(additionalItem.getSchedule().getDate().getTime()));
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colNames = new String[cols];
            for (int i = 0; i < colNames.length; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 1);
            }
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getDate(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        new DecimalFormat("0.00").format(rs.getDouble(4)),
                        new DecimalFormat("0.00").format(rs.getDouble(5)),
                        rs.getInt(6)
                });
            }
            return m;
        } catch (Exception e1) {
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    private ResultSetMetaData viewInventory() {
        try {
            String sql = "SELECT * FROM viewinventory";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return rs.getMetaData();
        } catch (Exception e) {
            return null;
        }
    }

    public DefaultTableModel viewFullInventory() {
        DefaultTableModel m = new DefaultTableModel();
        try {
            ResultSetMetaData rsmd = viewInventory();
            int cols = rsmd.getColumnCount();
            String[] colNames = new String[cols];
            for (int i = 0; i < colNames.length; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 1);
            }
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        new DecimalFormat("0.00").format(rs.getDouble(4)),
                        new DecimalFormat("0.00").format(rs.getDouble(5))
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Table", 1);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel viewHalfInventory() {
        DefaultTableModel m = new DefaultTableModel();
        try {
            ResultSetMetaData rsmd = viewInventory();
            String[] colNames = new String[3];
            colNames[0] = rsmd.getColumnLabel(2);
            colNames[1] = rsmd.getColumnLabel(3);
            colNames[2] = rsmd.getColumnLabel(5);
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getString(2),
                        rs.getInt(3),
                        new DecimalFormat("0.00").format(rs.getDouble(5))
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Table", 1);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel viewQuarterInventory() {
        DefaultTableModel m = new DefaultTableModel();
        try {
            ResultSetMetaData rsmd = viewInventory();
            String[] colNames = new String[2];
            colNames[0] = rsmd.getColumnLabel(2);
            colNames[1] = rsmd.getColumnLabel(5);
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getString(2),
                        new DecimalFormat("0.00").format(rs.getDouble(5))
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Table", 1);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel viewStockInventory() {
        DefaultTableModel m = new DefaultTableModel();
        try {
            ResultSetMetaData rsmd = viewInventory();
            String[] colNames = new String[3];
            colNames[0] = rsmd.getColumnLabel(1);
            colNames[1] = rsmd.getColumnLabel(2);
            colNames[2] = rsmd.getColumnLabel(3);
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Table", 1);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel searchHalfItem(String search) {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT * FROM viewinventory WHERE `Item ID` = '" + search + "' OR Description LIKE '%"
                    + search + "%' OR Stock LIKE '%" + search + "%' OR `Retail Price` LIKE '%" + search + "%' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] colNames = new String[3];
            colNames[0] = rsmd.getColumnLabel(2);
            colNames[1] = rsmd.getColumnLabel(3);
            colNames[2] = rsmd.getColumnLabel(5);
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getString(2),
                        rs.getInt(3),
                        new DecimalFormat("0.00").format(rs.getDouble(5))
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Search Failed!" + e.getMessage(), "Search Item", 1);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel viewOutOfStockItems() {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT * FROM viewinventory WHERE Stock = 0";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colNames = new String[cols];
            for (int i = 0; i < colNames.length; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 1);
            }
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        new DecimalFormat("0.00").format(rs.getDouble(4)),
                        new DecimalFormat("0.00").format(rs.getDouble(5)),
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Table", 1);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public DefaultTableModel viewAllOrders() {
        DefaultTableModel m = new DefaultTableModel();
        try {
            String sql = "SELECT * FROM vieworders ORDER BY `Order ID` ASC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colNames = new String[cols];
            for (int i = 0; i < colNames.length; i++) {
                colNames[i] = rsmd.getColumnLabel(i + 1);
            }
            m.setColumnIdentifiers(colNames);
            while (rs.next()) {
                m.addRow(new Object[] {
                        rs.getInt(1),
                        rs.getDate(2),
                        new DecimalFormat("0.00").format(rs.getDouble(3)),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        new DecimalFormat("0.00").format(rs.getDouble(8))
                });
            }
            return m;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Order Table", 1);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    // DROP-DOWN MENUS

    public Event[] chooseEvent() {
        Event[] events = {};
        int i = 0;
        try {
            String sql = "SELECT COUNT(`Event`) FROM viewevents";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next())
                events = new Event[rs.getInt(1)];
            sql = "SELECT * FROM viewevents";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                events[i] = new Event(rs.getInt(1), rs.getString(2));
                i++;
            }
            return events;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Dropdown Error", 0);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public void chooseEventPackages(Event event) {
        EventPackage[] packages = {};
        int i = 0;
        try {
            String sql = "SELECT COUNT(`Description`) FROM vieweventpackages WHERE `Event ID` = '" + event.getId()
                    + "' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next())
                packages = new EventPackage[rs.getInt(1)];
            sql = "SELECT `Package ID`, `Description`, Price FROM vieweventpackages WHERE `Event ID` = '"
                    + event.getId() + "' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                packages[i] = new EventPackage(rs.getInt(1), rs.getString(2), rs.getDouble(3), event);
                i++;
            }
            event.setPackages(packages);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Dropdown Error", 0);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public Cause[] chooseCauses() {
        Cause[] causes = {};
        int i = 0;
        try {
            String sql = "SELECT COUNT(`Description`) FROM viewcauses WHERE `Cause ID` != 1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next())
                causes = new Cause[rs.getInt(1)];
            sql = "SELECT * FROM viewcauses WHERE `Cause ID` != 1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                causes[i] = new Item(-1).new Cause(rs.getInt(1), rs.getString(2));
                i++;
            }
            return causes;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Dropdown Error", 0);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    public class Employee extends User {

        public Employee(int id, String fname, String lname, UserType type) {
            super(id, fname, lname, type);
        }

        public Employee(String name) {
            String[] splitname = name.split(" ");
            super.fname = splitname[0];
            super.lname = splitname[1];
        }

        public int newCustomerOrder(Customer customer, Order order) throws HeadlessException, SQLException {
            int orderID = 0;
            try {
                conn.setAutoCommit(false);
                String sql = "{CALL `newcust_order_transaction`(?,?,?,?,?,?,?,?)}";
                cst = conn.prepareCall(sql);
                cst.setString(1, customer.getFname());
                cst.setString(2, customer.getLname());
                cst.setString(3, customer.getEmail());
                cst.setString(4, customer.getContactnum());
                cst.setInt(5, customer.getEventOrganizer().getId());
                cst.setDate(6, new Date(order.getOrderDate().getTime()));
                cst.setDouble(7, order.getTotal_amount());
                cst.registerOutParameter(8, Types.INTEGER);
                cst.execute();
                orderID = cst.getInt(8);
                // JOptionPane.showMessageDialog(null, "Order Added Successfully!", "New
                // Customer Order", 1);
                conn.setAutoCommit(true);
                return orderID;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Transaction Failed!\n" + e.getMessage(), "New Customer Order", 0);
                conn.rollback();
                return 0;
            } finally {
                try {
                    cst.close();
                } catch (Exception e) {
                }
            }
        }

        public int regCustomerOrder(Customer customer, Order order) throws SQLException {
            int orderID = 0;
            try {
                conn.setAutoCommit(false);
                String sql = "{CALL `regcust_order_transaction`(?,?,?,?)}";
                cst = conn.prepareCall(sql);
                cst.setInt(1, customer.getId());
                cst.setDate(2, new Date(order.getOrderDate().getTime()));
                cst.setDouble(3, order.getTotal_amount());
                cst.execute();
                cst.registerOutParameter(4, Types.INTEGER);
                orderID = cst.getInt(4);
                // JOptionPane.showMessageDialog(null, "Order Added Successfully!", "New
                // Customer Order", 1);
                conn.setAutoCommit(true);
                return orderID;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Transaction Failed!\n" + e.getMessage(), "New Customer Order", 0);
                conn.rollback();
                return 0;
            } finally {
                try {
                    cst.close();
                } catch (Exception e) {
                }
            }
        }

        public void cancelOrder(Order order) throws SQLException {
            try {
                conn.setAutoCommit(false);
                String sql = "{CALL `cancel_order_transaction`(?)}";
                cst = conn.prepareCall(sql);
                cst.setInt(1, order.getId());
                JOptionPane.showMessageDialog(null, "Order Canceled", "Cancel Order", 1);
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Cancel Order Failed!\n" + e.getMessage(), "", 0);
                conn.rollback();
            } finally {
                try {
                    cst.close();
                } catch (Exception e) {
                }
            }
        }

        public void takeOrderedItems(OrderedItem ordereditem) throws SQLException {
            try {
                conn.setAutoCommit(false);
                String sql = "{CALL `take_orderedItems`(?,?,?,?)}";
                cst = conn.prepareCall(sql);
                cst.setInt(1, ordereditem.getOrder().getId());
                cst.setInt(2, ordereditem.getItem().getId());
                cst.setInt(3, ordereditem.getQuantity());
                cst.setDouble(4, ordereditem.getOrderPrice());
                cst.execute();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e.getMessage(), "Order Processing", 0);
                conn.rollback();
            } finally {
                try {
                    cst.close();
                } catch (Exception e) {
                }
                ;
            }
        }

        public DefaultTableModel searchProductName(String search) {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM viewinventory WHERE Description LIKE '" + search + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            new DecimalFormat("0.00").format(rs.getDouble(4)),
                            new DecimalFormat("0.00").format(rs.getDouble(5))
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Search Failed!" + e.getMessage(), "Search Item", 1);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public Item searchItem(int id) {
            Item item = null;
            try {
                String sql = "SELECT * FROM viewinventory WHERE `Item ID` = '" + id + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next())
                    item = new Item(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5));
                return item;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Item not found!\n" + e.getMessage(), "", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel viewAllActiveUsers() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String view = "SELECT `User ID`, `First Name`, `Last Name`, `User Type`, `Last Activity` FROM viewusers WHERE `Last Activity` COLLATE utf8mb4_unicode_ci = 'Recently Active';";
                pst = conn.prepareStatement(view);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "View Users", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        // public DefaultTableModel viewAllOrderedItems() {
        // DefaultTableModel m = new DefaultTableModel();
        // try {
        // return m;
        // } catch (Exception e) {
        // JOptionPane.showMessageDialog(null, e.getMessage(), "Ordered Items Table",
        // 1);
        // return null;
        // } finally {
        // try {
        // rs.close(); pst.close();
        // } catch (Exception e) {}
        // }
        // }

        // public void deleteEventSchedule(EventSchedule schedule) {
        // try {

        // } catch (Exception e) {

        // } finally {
        // try {
        // pst.close();
        // } catch (Exception e) {}
        // }
        // }
    }

    public class Manager extends User {

        public Manager(int id, String fname, String lname, UserType type) {
            super(id, fname, lname, type);
        }

        public Manager(String fname, String lname, String username, String password) {
            super.fname = fname;
            super.lname = lname;
            super.username = username;
            super.password = password;
            super.type = new UserType();
            super.conn = db.java_db();
        }

        public void createUser(User user) {
            try {
                String sql = "INSERT INTO user (fname, lname, username, password, usertypeID) VALUES (?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, user.fname);
                pst.setString(2, user.lname);
                pst.setString(3, user.username);
                pst.setString(4, user.password);
                pst.setInt(5, user.type.getId());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Added successfully!", "Create User", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Add Failed!\n" + e.getMessage(), "Create User", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void updateUser(User user) {
            try {
                String sql = "UPDATE user SET fname = '" + user.fname + "', lname = '" + user.lname + "', username = '"
                        + user.username + "', password = '" + user.password + "', usertypeID = '" + user.type.getId()
                        + "' WHERE userID = " + user.id + " ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated successfully!", "Update User", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Update Failed!\n" + e.getMessage(), "Update User", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel viewAllUsers() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String view = "SELECT * FROM viewusers";
                pst = conn.prepareStatement(view);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "View Users", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel viewAllActiveUsers() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String view = "SELECT * FROM viewusers WHERE `Last Activity` COLLATE utf8mb4_unicode_ci = 'Recently Active'";
                pst = conn.prepareStatement(view);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "View Users", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel searchUsers(String search) {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM viewusers WHERE `User ID` = '" + search + "' OR `First Name` LIKE '%"
                        + search
                        + "%' OR `Last Name` LIKE '%" + search + "%' OR Username LIKE '%" + search
                        + "%' OR `User Type` LIKE '%"
                        + search + "%' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Search Failed!\n" + e.getMessage(), "Search User", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel viewArchivedUsers() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String view = "SELECT * FROM viewuserarchive";
                pst = conn.prepareStatement(view);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "View User Archive", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void createUserType(UserType usertype) {
            try {
                String sql = "INSERT INTO usertype (type_desc) VALUES(?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, usertype.getDesc());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Added Successfully!", "Create User Type", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Add Failed!\n" + e.getMessage(), "Create User Type", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void updateUserType(UserType usertype) {
            try {
                String sql = "UPDATE usertype SET type_desc = '" + usertype.getDesc() + "' WHERE usertypeID = '"
                        + usertype.getId() + "'";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Successfully!", "Update User Type", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Update Failed!\n" + e.getMessage(), "Update User Type", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void deleteUserType(UserType usertype) {
            try {
                String sql = "DELETE FROM usertype WHERE usertypeID = '" + usertype.getId() + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Removed Successfully!", "Remove User Type", id);
                sql = "ALTER TABLE usertype AUTO_INCREMENT = 1";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Remove Failed!\n" + e.getMessage(), "Remove User Type", id);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel viewUserTypes() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String view = "SELECT * FROM viewusertypes";
                pst = conn.prepareStatement(view);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "View User Archive", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel searchArchivedUsers(String search) {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM viewarchive WHERE `User ID` = '" + search + "' OR `First Name` LIKE '%"
                        + search
                        + "%' OR `Last Name` LIKE '%" + search + "%' OR Username LIKE '%" + search
                        + "%' OR `User Type` LIKE '%"
                        + search + "%' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Search Failed!\n" + e.getMessage(), "Search User", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void archiveUser(User user) {
            try {
                String sql = "UPDATE user SET isArchived = true WHERE userID = '" + user.getId() + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,
                        "Account Deactivation Complete: " + user.getUsername() + " is not inactive", "Archive User", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed to archive user.", "Archive User", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void unarchiveUser(User user) {
            try {
                String sql = "UPDATE user SET isArchived = false WHERE userID = '" + user.getId() + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Unarchiving Complete: " + user.getUsername() + " is active again",
                        "Archive User", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Unarchive Failed!\n" + e.getMessage(), "Unarchive User", 0);
            }
        }

        public void createEvent(Event event) {
            try {
                String sql = "INSERT INTO event (`desc`) VALUES (?) ";
                pst = conn.prepareStatement(sql);
                pst.setString(1, event.getDesc());
                pst.execute();
                JOptionPane.showMessageDialog(null, "New event added successfully!", "Add Event", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Add Failed!\n" + e.getMessage(), "Add Event", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void updateEvent(Event event) {
            try {
                String sql = "UPDATE event SET `desc` = '" + event.getDesc() + "' WHERE eventID = '" + event.getId()
                        + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated successfully!", "Update Event", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Update Failed!\n" + e.getMessage(), "Update Event", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void deleteEvent(Event event) {
            try {
                String sql = "DELETE FROM event WHERE eventID = '" + event.getId() + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Removed Successfully!", "Remove Event", 1);
                sql = "ALTER TABLE event AUTO_INCREMENT = 1 ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Remove Failed!\n" + e.getMessage(), "Remove Event", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void createEventPackages(String desc, double price, Event eventID) {
            try {
                String sql = "INSERT INTO eventpackage (packagedesc, price, eventID) VALUES(?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, desc);
                pst.setDouble(2, price);
                pst.setInt(3, eventID.getId());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Added successfully!", "Add Event Package", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Add Failed!\n" + e.getMessage(), "Add Event Package", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void createIncludedItems(IncludedItem[] includedItems) {
            try {
                for (int i = 0; i < includedItems.length; i++) {
                    String sql = "INSERT INTO includeditems (eventpackageID, itemID, quantity) VALUES(?,?,?)";
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, includedItems[i].getEventPackage().getId());
                    pst.setInt(2, includedItems[i].getId());
                    pst.setInt(3, includedItems[i].getQuantity());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Included items added successfully!", "Add Included Items", 1);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed to include items!\n" + e.getMessage(), "Add Included Items",
                        0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void deleteIncludedItem(IncludedItem includedItem) {
            try {
                String sql = "DELETE FROM includeditems WHERE eventpackageID = ? AND itemID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, includedItem.getEventPackage().getId());
                pst.setInt(2, includedItem.getId());
                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Remove Failed!\n" + e.getMessage(), "Included Item", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void updateEventPackage(int id, String desc, double price, Event eventID) {
            try {
                String sql = "UPDATE eventpackage SET packagedesc = '" + desc + "', price = '" + price
                        + "', eventID = '"
                        + eventID.getId() + "' WHERE eventpackageID = '" + id + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated successfully!", "Update Event Package", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Update Failed!", "Update Event Package", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void deleteEventPackage(EventPackage eventpackage) {
            try {
                String sql = "DELETE FROM eventpackage WHERE eventpackageID = '" + eventpackage.getId() + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Removed successfully!", "Remove Event Package", 1);
                sql = "ALTER TABLE eventpackage AUTO_INCREMENT = 1";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Remove Failed!\n" + e.getMessage(), "", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel viewAllEvents() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM viewevents";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "View Events", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel searchEvents(String search) {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM viewevents WHERE `Event ID` = '" + search + "' OR `Event` LIKE '%"
                        + search
                        + "%' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                    });
                }
                return m;
            } catch (Exception e) {
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel viewAllEventPackages() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM vieweventpackages";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getString(4),
                            new DecimalFormat("0.00").format(rs.getDouble(5))
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "View Events", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel searchEventPackages(int searchEventID) {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM vieweventpackages WHERE `Event ID` = '" + searchEventID + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getString(4),
                            new DecimalFormat("0.00").format(rs.getDouble(5))
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Search Event Package", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void createItem(Item item) {
            try {
                String sql = "INSERT INTO item (`desc`, wholesale_price, retail_price) VALUES (?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, item.getDesc());
                pst.setDouble(2, item.getWholesale_price());
                pst.setDouble(3, item.getRetail_price());
                pst.execute();
                JOptionPane.showMessageDialog(null, "New item added successfully!", "Add Item", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Add Failed!\n" + e.getMessage(), "Add Item", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void updateItem(Item item) {
            try {
                String sql = "UPDATE item SET `desc` = '" + item.getDesc() + "', wholesale_price = '"
                        + item.getWholesale_price() + "', retail_price = '" + item.getRetail_price()
                        + "' WHERE itemID = " + item.getId() + " ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Successfully", "Update Item", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Update Failed!\n" + e.getMessage(), "Update Item", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void deleteItem(Item item) {
            try {
                String sql = "DELETE FROM item WHERE itemID = " + item.getId() + " ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Removed successfully!", "Remove Item", 1);
                sql = "ALTER TABLE event AUTO_INCREMENT = 1 ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Remove Failed!\n" + e.getMessage(), "Remove Item", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel viewStockins() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM viewstockins";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getDate(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Stock Out Table", 1);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel searchStockins(String search) {
            DefaultTableModel m = new DefaultTableModel();

            try {
                String sql = "SELECT * FROM viewstockins WHERE `StockIn ID` = '" + search
                        + "' OR `StockIn Date` LIKE '%" + search + "%' OR Quantity LIKE '%" + search
                        + "%' OR `Item ID` LIKE '%" + search + "%' OR Description LIKE '%" + search
                        + "%' OR `User` LIKE '%" + search + "%' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getDate(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Search Failed!" + e.getMessage(), "Search Item", 1);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel searchStockouts(String search) {
            DefaultTableModel m = new DefaultTableModel();

            try {
                String sql = "SELECT * FROM viewstockouts WHERE `StockOut ID` = '" + search
                        + "' OR `StockOut Date` LIKE '%" + search + "%' OR `Quantity` LIKE '%" + search
                        + "%' OR `Item ID` LIKE '%" + search + "%' OR `Description` LIKE '%" + search
                        + "%' OR `Cause` LIKE '%" + search + "%' OR `User` LIKE '%" + search + "%' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getDate(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Search Failed!" + e.getMessage(), "Search Item", 1);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void removeStocks(java.util.Date stockoutDate, StockOut stockout_items[], Cause cause) {
            try {
                Date sqldate = new Date(stockoutDate.getTime());
                for (int i = 0; i < stockout_items.length; i++) {

                    String sql = "INSERT INTO stockout (stockoutdate, quantity, itemID, causeID, userID) VALUES (?,?,?,?,?)";
                    pst = conn.prepareStatement(sql);
                    pst.setDate(1, sqldate);
                    pst.setInt(2, stockout_items[i].getQuantity());
                    pst.setInt(3, stockout_items[i].getItem().getId());
                    pst.setInt(4, cause.getId());
                    pst.setInt(5, super.id);

                    pst.execute();

                }

                JOptionPane.showMessageDialog(null, "Remove stocks completed!", "Remove Stocks", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Remove Stock Failed!\n" + e.getMessage(), "Remove Stock", 0);
            }
        }

        public DefaultTableModel viewStockouts() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM viewstockouts";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getDate(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Stock Out Table", 1);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel searchQuarterItem(String search) {
            DefaultTableModel m = new DefaultTableModel();

            try {
                String sql = "SELECT * FROM viewinventory WHERE Description LIKE '%"
                        + search + "%' OR `Retail Price` LIKE '%" + search + "%' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                ResultSetMetaData rsmd = rs.getMetaData();
                String[] colNames = new String[2];
                colNames[0] = rsmd.getColumnLabel(2);
                colNames[1] = rsmd.getColumnLabel(5);
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getString(2),
                            new DecimalFormat("0.00").format(rs.getDouble(5))
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Search Failed! " + e.getMessage(), "Search Item", 1);
                e.printStackTrace();
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel searchStockItem(String search) {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM viewinventory WHERE `Item ID` = '" + search + "' OR Description LIKE '%"
                        + search + "%' OR Stock LIKE '%" + search + "%' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String[] colNames = new String[3];
                colNames[0] = rsmd.getColumnLabel(1);
                colNames[1] = rsmd.getColumnLabel(2);
                colNames[2] = rsmd.getColumnLabel(3);
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Search Failed!" + e.getMessage(), "Search Item", 1);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void createSupplier(String name) {
            try {
                String sql = "INSERT INTO supplier (suppname) VALUES (?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, name);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Added successfully!", "Create Supplier", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Add Failed!\n" + e.getMessage(), "Create Supplier", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void updateSupplier(Supplier supplier) {
            try {
                String sql = "UPDATE supplier SET suppname = '" + supplier.getName() + "' WHERE supplierID = '"
                        + supplier.getId() + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated successfully!", " Supplier", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Update Failed!\n" + e.getMessage(), " Supplier", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void deleteSupplier(int supplierID) {
            try {
                String sql = "DELETE FROM supplier WHERE supplierID = '" + supplierID + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Removed successfully!", " Supplier", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Removed Failed!\n" + e.getMessage(), " Supplier", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel viewAllSuppliers() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String view = "SELECT * FROM viewsuppliers";
                pst = conn.prepareStatement(view);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "View Suppliers", 0);
                e.printStackTrace();
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel searchSuppliers(String search) {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM viewsuppliers WHERE `Supplier ID` = '" + search
                        + "' OR `Supplier Name` LIKE '%" + search + "%' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                    });
                }
                return m;
            } catch (Exception e) {
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        // DROP-DOWN MENUS
        public UserType[] chooseUserType() {
            UserType[] usertypes = {};
            int i = 0;
            try {
                String sql = "SELECT COUNT(usertypeID) FROM usertype";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next())
                    usertypes = new UserType[rs.getInt(1)];
                sql = "SELECT usertypeID, type_desc FROM usertype";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    usertypes[i] = new UserType(rs.getString(2), rs.getInt(1));
                    i++;
                }
                return usertypes;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "Dropdown Error", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public Supplier[] chooseSuppliers() {
            Supplier[] suppliers = {};
            int i = 0;
            try {
                String sql = "SELECT COUNT(supplierID) FROM supplier";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next())
                    suppliers = new Supplier[rs.getInt(1)];
                sql = "SELECT * FROM supplier";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    suppliers[i] = new Item(-1).new Supplier(rs.getInt(1), rs.getString(2));
                    i++;
                }
                return suppliers;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "Dropdown Error", 0);
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void addStocks(java.util.Date stockinDate, StockIn stockin_items[], Supplier supplier) {
            try {
                Date sqldate = new Date(stockinDate.getTime());
                for (int i = 0; i < stockin_items.length; i++) {

                    String sql = "INSERT INTO stockin (stockindate, quantity, itemID, userID, supplierID) VALUES (?,?,?,?,?)";
                    pst = conn.prepareStatement(sql);
                    pst.setDate(1, sqldate);
                    pst.setInt(2, stockin_items[i].getQuantity());
                    pst.setInt(3, stockin_items[i].getItem().getId());
                    pst.setInt(4, super.id);
                    pst.setInt(5, supplier.getId());

                    pst.execute();

                }

                JOptionPane.showMessageDialog(null, "Adding stocks completed!", "Add Stocks", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Adding Stock Failed!\n" + e.getMessage(), "Add Stock", 0);
                e.printStackTrace();
            }
        }

        public void createCause(Cause cause) {
            try {
                String sql = "INSERT INTO cause (causedesc) VALUES (?) ";
                pst = conn.prepareStatement(sql);
                pst.setString(1, cause.getCausedesc());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Added successfully!", "Add Cause", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Add Failed", "Add Cause", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void updateCause(Cause cause) {
            try {
                String sql = "UPDATE cause SET causedesc = '" + cause.getCausedesc() + "' WHERE causeID = '"
                        + cause.getId() + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Updated successfully!", "Update Cause", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Update Failed", "Update Cause", 0);
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public void deleteCause(Cause cause) {
            try {
                String sql = "DELETE FROM cause WHERE causeID = '" + cause.getId() + "' ";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Removed successfully!", "Remove Cause", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Remove Failed!", "Remove Cause", 1);
                e.printStackTrace();
            } finally {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel viewAllCauses() {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String view = "SELECT * FROM viewcauses";
                pst = conn.prepareStatement(view);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2)
                    });
                }
                return m;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "View Causes", 0);
                e.printStackTrace();
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        public DefaultTableModel searchCauses(String search) {
            DefaultTableModel m = new DefaultTableModel();
            try {
                String sql = "SELECT * FROM viewcauses WHERE `Cause ID` = '" + search
                        + "' OR `Description` LIKE '%" + search + "%' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String[] colNames = new String[cols];
                for (int i = 0; i < colNames.length; i++) {
                    colNames[i] = rsmd.getColumnLabel(i + 1);
                }
                m.setColumnIdentifiers(colNames);
                while (rs.next()) {
                    m.addRow(new Object[] {
                            rs.getInt(1),
                            rs.getString(2),
                    });
                }
                return m;
            } catch (Exception e) {
                return null;
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }

        // public void removeStocks(java.util.Date date, Item items[], )

        // public void undoAddStock(java.util.Date date, Item item) throws SQLException
        // {

        // try {
        // conn.setAutoCommit(false);
        // String sql = "{CALL undo_add_stock(?,?,?,?)}";
        // cst = conn.prepareCall(sql);
        // cst.setDate(1, new Date(date.getTime()));
        // cst.setInt(2, item.getStock());
        // cst.setInt(3, item.getId());
        // cst.setInt(4, super.id);
        // cst.execute();
        // JOptionPane.showMessageDialog(null,
        // "Undo Successful: Stock for " + item.getDesc() + " has been adjusted", "Add
        // Stock", 1);
        // conn.setAutoCommit(true);
        // } catch (SQLException e) {
        // JOptionPane.showMessageDialog(null,
        // "An error occured while attempting to undo stock addition\n" +
        // e.getMessage(), "Add Stock", 0);
        // conn.rollback();
        // } finally {
        // try {
        // cst.close();
        // } catch (Exception e) {
        // }
        // }
        // }
    }

    public static class UserType {
        private String desc;
        private int id;

        public UserType(String desc, int id) {
            this.desc = desc;
            this.id = id;
        }

        public UserType(String desc) {
            this.desc = desc;
        }

        public UserType(int id) {
            this.id = id;
        }

        public UserType() {
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public int getId() {
            return id;
        }
    }
}