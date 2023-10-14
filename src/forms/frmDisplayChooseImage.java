/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;


import facial_recognition.FaceReconigtion;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import models.Account;
import models.ButtonColumn;

/**
 *
 * @author dell
 */

public class frmDisplayChooseImage extends javax.swing.JFrame {
    private static List<byte[]> listChooseImage;
    private ButtonColumn buttonColumn;
    private FaceReconigtion face;
    private static Account account;
     /**
     * Creates new form frmDisplayChooseImage
     * @param listChooseImage
     * @param account
     */
    public frmDisplayChooseImage(List<byte[]> listChooseImage, Account account) {
        initComponents();
        frmDisplayChooseImage.listChooseImage = listChooseImage;
        frmDisplayChooseImage.account = account;
        face = new FaceReconigtion();
        LoadImage();
        renderButtonDelete();
        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.cyan)); 
    }
    
    private void LoadImage() {
        DefaultTableModel model = (DefaultTableModel) tbImage.getModel();
        model.setRowCount(0); 
        for (byte[] image : listChooseImage) {                       
            ImageIcon imageIcon = new ImageIcon(image);
            Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
            model.addRow(new Object[]{model.getRowCount() + 1, scaledImageIcon});         
        }
        // Set the custom cell renderer for the image column
        TableColumn imageColumn = tbImage.getColumnModel().getColumn(1);
        imageColumn.setCellRenderer(new frmDisplayChooseImage.ImageRenderer());        
        tbImage.setRowHeight(100); 
    }
    
    class ImageRenderer extends JLabel implements TableCellRenderer {
        public ImageRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setIcon((Icon) value);
            return this;
        }
    }
    
    private void clickLastColumnCell(){
        
        tbImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbImage.rowAtPoint(e.getPoint());
                int column = tbImage.columnAtPoint(e.getPoint());
                if (column == tbImage.getColumnCount() - 1) {
                    
                    int selectedRow = tbImage.convertRowIndexToModel(row);
                    int indexImage = Integer.parseInt(tbImage.getModel().getValueAt(selectedRow, 0).toString());
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc muốn xoá ảnh này này? ",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION)
                    {
                        listChooseImage.remove(indexImage - 1);
                        JOptionPane.showMessageDialog(null, "Đã xoá ảnh");
                        LoadImage();
                        
                    }
                }

            }
        });
    }

    private void renderButtonDelete(){       
        clickLastColumnCell();
        Icon deleteIcon = new  ImageIcon("src\\icons\\delete.png");
        buttonColumn = new ButtonColumn(tbImage, tbImage.getColumnCount() - 1,deleteIcon);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbImage = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbImage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Số thứ tự", "Ảnh", "Xoá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbImage);

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(btnSave)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addGap(12, 12, 12))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        try{
            for(byte[] image : listChooseImage){
                face.saveFaceChoose(image, account);
            }
            JOptionPane.showMessageDialog(null, "Lưu ảnh thành công");
            this.dispose();
        }
        catch(HeadlessException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmDisplayChooseImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDisplayChooseImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDisplayChooseImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDisplayChooseImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDisplayChooseImage(listChooseImage, account).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbImage;
    // End of variables declaration//GEN-END:variables
}
