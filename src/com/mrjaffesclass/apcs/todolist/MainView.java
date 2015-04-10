package com.mrjaffesclass.apcs.todolist;

import java.util.*;
import javax.swing.table.*;
import com.mrjaffesclass.apcs.messenger.*;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import org.jdesktop.swingx.table.DatePickerCellEditor;

/**
 * To do list main view
 *
 * @authors Rachel Schmeltzer, Rebekah Leslie, and Ann Tran
 * @version 1.0
 *
 */
public class MainView extends javax.swing.JFrame implements MessageHandler {

    // Instance constants
    private final int ID_FIELD = 0;
    private final int DONE_FIELD = 1;
    private final int DESCRIPTION_FIELD = 2;
    private final int DATE_FIELD = 3;

    private final int DONE_COLUMN = 0;
    private final int DATE_COLUMN = 2;

    private final int DONE_FIELD_WIDTH = 100;
    private final int DESCRIPTION_FIELD_WIDTH = 475;
    private final int DATE_FIELD_WIDTH = 150;
    private final int ROW_HEIGHT = 25;

    private final int X_POSITION = 100;
    private final int Y_POSITION = 100;

    // Messenger class gets saved here
    private final Messenger messenger;

    /**
     * Creates a new main view
     *
     * @param _messenger mvcMessaging object
     */
    public MainView(Messenger _messenger) {
        messenger = _messenger;   // Save the calling controller instance
        this.setLocation(X_POSITION, Y_POSITION); // Set main window location
        initComponents();           // Create and init the GUI components

        // Make adjustments to the column widths to suit our needs
        // Remove the ID column and set the row height
        jTable1.getColumnModel().getColumn(DONE_FIELD).setPreferredWidth(DONE_FIELD_WIDTH);  // Set width of checkbox column
        jTable1.getColumnModel().getColumn(DESCRIPTION_FIELD).setPreferredWidth(DESCRIPTION_FIELD_WIDTH);  // Set width of checkbox column
        jTable1.getColumnModel().getColumn(DATE_FIELD).setPreferredWidth(DATE_FIELD_WIDTH);
        jTable1.removeColumn(jTable1.getColumnModel().getColumn(ID_FIELD));  // Remove the ID column from the table
        jTable1.setRowHeight(ROW_HEIGHT);
    }

    /**
     * Do any required initialization code and subscribe to messages to which
     * the view needs to respond
     */
    public void init() {
        messenger.subscribe("ready", this);
        messenger.subscribe("items", this);

        //Date renderer code from the internet
        TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {

            SimpleDateFormat f = new SimpleDateFormat("EEE MM/dd");

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                if (value instanceof Date) {
                    value = f.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
            }
        };

        // set our jTable date column to use date renderer above to display dates
        jTable1.getColumnModel().getColumn(DATE_COLUMN).setCellRenderer(tableCellRenderer);

        TableColumn dateColumn = jTable1.getColumnModel().getColumn(DATE_COLUMN);
        dateColumn.setCellEditor(new DatePickerCellEditor());
    }

    // This method implements the messageHandler method defined in
    // the MessageHandler interface and will fire when a message 
    // that this module has subscribed to is sent
    @Override
    public void messageHandler(String messageName, Object messagePayload) {
        switch (messageName) {
            // The app is loaded and ready to go
            case "ready":
                // Ask for the to do list items
                messenger.notify("getItems", null, true);
                break;

            // The message contains the list of to do items in messagePayload
            // The tableModel has the data that's displayed in the table. 
            case "items":
                ArrayList list = (ArrayList) messagePayload;
                DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                loadTableModel(tableModel, list);
                break;
        }
    }

    /**
     * Loads the to do list into the tableModel to display in the table If we
     * put the to do list's data in the table model the view will paint the
     * table with the table model's data.
     *
     * @param tableModel TableModel for the jTable
     * @param list To do list of items
     */
    private void loadTableModel(DefaultTableModel tableModel, ArrayList list) {
        // Get the number of items in the list and
        // set the tableModel with this size
        int size = list.size();
        tableModel.setRowCount(size);

        // Look through the to do list and save the to do item fields
        // in the table model.  The table model will see the changes
        // and cause the table to repaint with the new data
        for (int i = 0; i < size; i++) {
            ToDoItem item = (ToDoItem) (list.get(i));
            tableModel.setValueAt(item.getId(), i, ID_FIELD);
            tableModel.setValueAt(item.isDone(), i, DONE_FIELD);
            tableModel.setValueAt(item.getDescription(), i, DESCRIPTION_FIELD);
            tableModel.setValueAt(item.getDate(), i, DATE_FIELD);
        }
    }

    /**
     * Light up the edit item dialog
     *
     * @param item Item to edit
     */
    private void editItem(ToDoItem item) {
        EditView dialog = new EditView(this, item, messenger);
        dialog.init();
        dialog.setVisible(true);
    }

    /**
     * User clicked the completed check box. Don't show edit dialog because the
     * done field is toggled by clicking the check box
     *
     * @param item Item to edit
     */
    private void toggleDone(ToDoItem item) {
        messenger.notify("saveItem", item, true);
    }

    /**
     * Creates a ToDoItem from the data in the table model
     */
    private ToDoItem createItemFromTableModelRow(int row) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();

        // Now create a ToDoItem that we can pass to the editing dialog
        // The done field is toggled when the user clicks the checkbox
        ToDoItem item = new ToDoItem(
                (int) tableModel.getValueAt(row, ID_FIELD),
                (String) tableModel.getValueAt(row, DESCRIPTION_FIELD),
                (boolean) tableModel.getValueAt(row, DONE_FIELD),
                (Date) tableModel.getValueAt(row, DATE_FIELD)
        );
        return item;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tableForList = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        newItemBtn = new javax.swing.JButton();
        aboutBtn = new javax.swing.JButton();
        removeCompleteItemsBtn = new javax.swing.JButton();
        sortUp = new javax.swing.JButton();
        sortDown = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 494, 481));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Complete", "Description", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        tableForList.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("id");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("Complete");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Description");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Date");
        }

        newItemBtn.setText("New");
        newItemBtn.setBorderPainted(false);
        newItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newItemBtnActionPerformed(evt);
            }
        });

        aboutBtn.setText("About...");
        aboutBtn.setBorderPainted(false);
        aboutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutBtnActionPerformed(evt);
            }
        });

        removeCompleteItemsBtn.setText("Remove Completed Items");
        removeCompleteItemsBtn.setBorderPainted(false);
        removeCompleteItemsBtn.setOpaque(false);
        removeCompleteItemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCompleteItemsBtnActionPerformed(evt);
            }
        });

        sortUp.setText("Sort ^");
        sortUp.setBorderPainted(false);
        sortUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sortUpMouseClicked(evt);
            }
        });
        sortUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSortUpActionPerformed(evt);
            }
        });

        sortDown.setText("Sort  v");
        sortDown.setBorderPainted(false);
        sortDown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sortDownMouseClicked(evt);
            }
        });
        sortDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortDownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tableForList)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(removeCompleteItemsBtn)
                        .addGap(18, 18, 18)
                        .addComponent(aboutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(sortUp, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortDown, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeCompleteItemsBtn)
                    .addComponent(newItemBtn)
                    .addComponent(aboutBtn)
                    .addComponent(sortUp)
                    .addComponent(sortDown))
                .addGap(18, 18, 18)
                .addComponent(tableForList, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * selects part of the table when clicked by the mouse
     * 
     * @param evt the event is the mouse clicking that occurs
     */
  private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      // Get the clicked item data
      // Get the row number that was clicked in the table
      // then find the item id of the clicked item
      int row = jTable1.getSelectedRow();
      int col = jTable1.getSelectedColumn();
      ToDoItem item = this.createItemFromTableModelRow(row);

      // If the checkbox column was clicked, then we can just toggle the item's
      // done field.  If any other column was clicked we should open the editItem
      // dialog so the item can be edited.
      if (col == DONE_COLUMN) {
          toggleDone(item);
      } else {
          editItem(item);
      }
  }//GEN-LAST:event_jTable1MouseClicked

  private void newItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newItemBtnActionPerformed
      ToDoItem item = new ToDoItem(-1, "New to do item", false);
      editItem(item);
  }//GEN-LAST:event_newItemBtnActionPerformed

  private void removeCompleteItemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCompleteItemsBtnActionPerformed
      messenger.notify("removeCompletedItems");
  }//GEN-LAST:event_removeCompleteItemsBtnActionPerformed

  private void aboutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutBtnActionPerformed
      AboutView dialog = new AboutView(this, true);
      dialog.setVisible(true);
  }//GEN-LAST:event_aboutBtnActionPerformed

    private void jButtonSortUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSortUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSortUpActionPerformed

    private void sortDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortDownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sortDownActionPerformed

    private void sortUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortUpMouseClicked
        messenger.notify("sortDatesUp");
    }//GEN-LAST:event_sortUpMouseClicked

    private void sortDownMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortDownMouseClicked
        messenger.notify("sortDatesDown");
    }//GEN-LAST:event_sortDownMouseClicked

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton newItemBtn;
    private javax.swing.JButton removeCompleteItemsBtn;
    private javax.swing.JButton sortDown;
    private javax.swing.JButton sortUp;
    private javax.swing.JScrollPane tableForList;
    // End of variables declaration//GEN-END:variables
}
