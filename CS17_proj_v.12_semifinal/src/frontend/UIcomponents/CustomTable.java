package frontend.UIcomponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTable extends JTable {

    private int headerAlignment;
    private int fontSize;

    public CustomTable(int headerAlignment, int fontSize) {
        this.setHeaderAlignment(headerAlignment);
        this.setFontSize(fontSize);

        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(35);
        getTableHeader().setReorderingAllowed(false);

        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                Design_TableHeader header = new Design_TableHeader(o + "");
                if (i1 == headerAlignment) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setBackground(Color.WHITE);
                setBorder(noFocusBorder);
                if (isSelected) {
                    component.setForeground(new Color(0, 0, 0));
                    component.setBackground(new Color(227, 244, 244));
                    component.setFont(new Font("sansserif", 1, fontSize));
                } else {
                    component.setForeground(new Color(102, 102, 102));
                }
                return component;
            }
        });
    }

	public int getHeaderAlignment() {
		return headerAlignment;
	}

	public void setHeaderAlignment(int headerAlignment) {
		this.headerAlignment = headerAlignment;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
}