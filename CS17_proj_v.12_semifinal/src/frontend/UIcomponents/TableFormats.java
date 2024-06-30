package frontend.UIcomponents;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class TableFormats {
    private static JTable table = null;

    // for numeric columns
    private static void renderRight(int colIndex) {

        if (table == null)
            return;
        TableColumnModel columnModel = table.getColumnModel();

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        columnModel.getColumn(colIndex).setCellRenderer(rightRenderer);
    }

    private static void setTable(JTable table) {
        TableFormats.table = table;
    }

    public static void toActiveUsers(JTable table) {
        setTable(table);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toViewOrders(JTable table) {
        setTable(table);
        renderRight(2);
        renderRight(7);
        ColumnsAutoSizer.sizeColumnsToFit(table, 22);
    }

    public static void toEventSchedules(JTable table) {
        setTable(table);
        renderRight(7);
        ColumnsAutoSizer.sizeColumnsToFit(table, 15);
    }

    public static void toOutofStock(JTable table) {
        setTable(table);
        renderRight(2);
        renderRight(3);
        renderRight(4);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toEventTable(JTable table) {
        setTable(table);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toEventPackageTable(JTable table) {
        setTable(table);
        renderRight(4);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toManageUser(JTable table) {
        setTable(table);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toArchiveUserTable(JTable table) {
        setTable(table);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toUserTypeTable(JTable table) {
        setTable(table);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toInventoryTable(JTable table) {
        setTable(table);
        renderRight(2);
        renderRight(3);
        renderRight(4);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toStockInTable(JTable table) {
        setTable(table);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toStockOutTable(JTable table) {
        setTable(table);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toItemTable(JTable table) {
        setTable(table);
        renderRight(2);
        renderRight(3);
        renderRight(4);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toOrderTable(JTable table) {
        setTable(table);
        renderRight(1);
        renderRight(2);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toCustomerTable(JTable table) {
        setTable(table);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }

    public static void toIncludedItemsTable(JTable table) {
        setTable(table);
        renderRight(1);
        renderRight(2);
        ColumnsAutoSizer.sizeColumnsToFit(table);
    }
}
