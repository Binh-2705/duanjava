package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CTKiemKeView extends JDialog {
    public JTable tblChiTiet;
    public DefaultTableModel tableModel;

    public CTKiemKeView(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Chi tiết chênh lệch kiểm kê");
        setSize(700, 400);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
            new String[]{"Tên Sản Phẩm", "Số lượng Hệ thống", "Số lượng Thực tế", "Chênh lệch"}, 0
        );
        tblChiTiet = new JTable(tableModel);
        add(new JScrollPane(tblChiTiet), BorderLayout.CENTER);
        
        setLocationRelativeTo(parent);
    }
}