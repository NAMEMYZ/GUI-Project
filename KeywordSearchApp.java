import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class KeywordSearchApp {
    private JTextField keywordField;
    private JTable resultTable;
    private DefaultTableModel model;

    public KeywordSearchApp() {
        JFrame frame = new JFrame("Keyword Search App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        keywordField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        resultTable = new JTable();
        JButton insertButton = new JButton("Insert");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton showAllButton = new JButton("Show All");
       

        panel.add(keywordField);
        panel.add(searchButton);
        panel.add(insertButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(showAllButton);
        
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // เพิ่ม ListSelectionListener ให้กับ JTable
        resultTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = resultTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // ทำสิ่งที่ต้องการเมื่อเลือกแถวใน JTable
                        // นำข้อมูลไปใช้ในการอัพเดทหรือลบข้อมูล
                        int id = (int) model.getValueAt(selectedRow, 0);
                        String column_name = (String) model.getValueAt(selectedRow, 1);
                        String keyword = (String) model.getValueAt(selectedRow, 2);
                        String data_text = (String) model.getValueAt(selectedRow, 3);
                        keywordField.setText(keyword);
                        // สามารถนำข้อมูล id, column_name, data_text ไปใช้ในการอัพเดทหรือลบตามต้องการ
                    }
                }
            }
        });
        insertButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        // เรียกฟังก์ชั่นสำหรับการเพิ่มข้อมูล
        insertData();
        }
        });
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword = keywordField.getText();
                searchAndDisplayResults(keyword);
            }
        });
        updateButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        // เรียกฟังก์ชั่นสำหรับการอัพเดทข้อมูล
        updateData();
        }
        });
        deleteButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        // เรียกฟังก์ชั่นสำหรับการลบข้อมูล
        deleteData();
    }
});
        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call a function to fetch and display all data
                showAllData();
            }
        });
        frame.setVisible(true);
    }
private void insertData() {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdb?user=root&password=");
        String sql = "INSERT INTO data (id, column_name, keyword, data_text) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID"));
        String columnName = JOptionPane.showInputDialog("Enter First Name");
        String keyword = keywordField.getText();
        String dataText = JOptionPane.showInputDialog("Enter Major");

        pstmt.setInt(1, id);
        pstmt.setString(2, columnName);
        pstmt.setString(3, keyword);
        pstmt.setString(4, dataText);

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

        searchAndDisplayResults("");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

private void updateData() {
    int selectedRow = resultTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select a row to update.");
        return;
    }

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdb?user=root&password=");
        String sql = "UPDATE data SET id = ?, column_name = ?, keyword = ?, data_text = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        int newId = Integer.parseInt(JOptionPane.showInputDialog("Enter New ID"));
        String newColumnName = JOptionPane.showInputDialog("Enter New First Name");
        String newKeyword = keywordField.getText();
        String newDataText = JOptionPane.showInputDialog("Enter New Major");
        
        int currentId = (int) resultTable.getValueAt(selectedRow, 0);

        pstmt.setInt(1, newId);
        pstmt.setString(2, newColumnName);
        pstmt.setString(3, newKeyword);
        pstmt.setString(4, newDataText);
        pstmt.setInt(5, currentId);

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

        searchAndDisplayResults("");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

private void deleteData() {
    int selectedRow = resultTable.getSelectedRow();
    if (selectedRow == -1) {
        // ไม่มีแถวที่ถูกเลือก
        JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        return;
    }

    int idToDelete = (int) resultTable.getValueAt(selectedRow, 0); // ดึงค่า ID จากตาราง

    // Ask for confirmation before deleting
    int confirmResult = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to delete this data?",
        "Delete Confirmation",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmResult == JOptionPane.YES_OPTION) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdb?user=root&password=");
            String sql = "DELETE FROM data WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idToDelete);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

            // เรียกฟังก์ชั่น searchAndDisplayResults เพื่อแสดงข้อมูลทั้งหมดหลังจากการลบข้อมูล
            searchAndDisplayResults("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

private void showAllData() {
        // You can reuse the searchAndDisplayResults method with an empty keyword
        searchAndDisplayResults("");
    }
    private void searchAndDisplayResults(String keyword) {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Major");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdb?user=root&password=");
            String sql = "SELECT * FROM data WHERE id LIKE ? OR column_name LIKE ? OR keyword LIKE ? OR data_text LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");
            pstmt.setString(4, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("column_name"), rs.getString("keyword"), rs.getString("data_text")});
            }

            resultTable.setModel(model);

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new KeywordSearchApp();
            }
        });
    }
}
