package frontend.UIcomponents;

import java.awt.Component;
import java.awt.FontMetrics;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


public class ColumnsAutoSizer {
    
    public static void sizeColumnsToFit(JTable table) {
        sizeColumnsToFit(table, 24);
    }

    public static void sizeColumnsToFit(JTable table, int margin) {
        JTableHeader tableHeader = table.getTableHeader();

        if(tableHeader == null)
            return;
        
        FontMetrics headerFontMetrics = tableHeader.getFontMetrics(tableHeader.getFont());

        int[] minWidths = new int[table.getColumnCount()];
        int[] maxWidths = new int[table.getColumnCount()];

        for (int colIndex = 0; colIndex < table.getColumnCount(); colIndex++) {
            int headerWidth = headerFontMetrics.stringWidth(table.getColumnName(colIndex));

            minWidths[colIndex] = headerWidth + margin;

            int maxWidth = getMaximalRequiredColumnWidth(table, colIndex, headerWidth);
            
            maxWidths[colIndex] = Math.max(maxWidth, minWidths[colIndex]) + margin;
        }

        adjustMaximumWidths(table, minWidths, maxWidths);

        for (int i = 0; i < minWidths.length; i++) {
            if (minWidths[i] > 0) {
                table.getColumnModel().getColumn(i).setMinWidth(minWidths[i]);
            }

            if (maxWidths[i] > 0) {

                table.getColumnModel().getColumn(i).setWidth(maxWidths[i]);
            }
        }
    }

    private static void adjustMaximumWidths(JTable table, int[] minWidths, int[] maxWidths) {
        if (table.getWidth() > 0) {
            // tot prevent infinite loops  in exceptional situations
            int breaker = 0;

            while (sum(maxWidths) > table.getWidth() && breaker < 10000) {
                int highestWidthIndex = findLargestIndex(maxWidths);

                maxWidths[highestWidthIndex] -= 1;

                maxWidths[highestWidthIndex] = Math.max(maxWidths[highestWidthIndex], minWidths[highestWidthIndex]);

                breaker++;
            }
        }
    }

    private static int getMaximalRequiredColumnWidth(JTable table, int colIndex, int headerWidth) {
        int maxWidth = headerWidth;

        TableColumn col = table.getColumnModel().getColumn(colIndex);

        TableCellRenderer cellRenderer = col.getCellRenderer();

        if(cellRenderer == null)
            cellRenderer = new DefaultTableCellRenderer();
        
        for (int row = 0; row < table.getModel().getRowCount(); row++) {
            Component rendererComponent = cellRenderer.getTableCellRendererComponent(table,
            table.getModel().getValueAt(row, colIndex),
            false,
            false,
            row,
            colIndex);

            double valueWidth = rendererComponent.getPreferredSize().getWidth();

            maxWidth = (int) Math.max(maxWidth, valueWidth);
        }
        return maxWidth;
    }

    private static int findLargestIndex(int[] widths) {
        int largestIndex = 0;
        int largestValue = 0;


        for (int i = 0; i < widths.length; i++) {
            if(widths[i] > largestValue) {
                largestIndex = i;
                largestValue = widths[i];
            }
        }

        return largestIndex;
    }

    private static int sum(int[] widths) {
        int sum = 0;
        
        for (int width : widths) {
            sum += width;
        }
        return sum;
    }
}
