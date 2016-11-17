/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Permission;

import Users.*;
import ExceptionLogging.MyLogger;
import MyLibraries.JtableTools.JTableSQLTool;
import MyLibraries.SQL.SQLTools;
import Properties.Properties_Bundel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polina
 */
public class Permission extends javax.swing.JFrame {

    /**
     * Creates new form Users
     */
    private final int Add_mode = 0;   // les variable des mode ajout modification suppression pour une fonctionalité futur
    // je veux qu il genere automatiquement la requete d insertion ajout et meme supression
    private final int Edit_mode = 1;
    private int mode;

    public Permission() throws SQLException {
        initComponents();
    }
    private ResultSet J_Table_UsersRS;

     private final String[] HideColumns = new String[]{
        
        Properties_Bundel.getString(Properties_Bundel.HeadsCaption_users_ID,Properties_Bundel.HeadsCaption),
        Properties_Bundel.getString(Properties_Bundel.HeadsCaption_users_PASSWORD,Properties_Bundel.HeadsCaption),
        Properties_Bundel.getString(Properties_Bundel.HeadsCaption_users_PROFILE,Properties_Bundel.HeadsCaption),
        Properties_Bundel.getString(Properties_Bundel.HeadsCaption_users_STATE,Properties_Bundel.HeadsCaption),
        Properties_Bundel.getString(Properties_Bundel.HeadsCaption_profiles_ID,Properties_Bundel.HeadsCaption),
        Properties_Bundel.getString(Properties_Bundel.HeadsCaption_usersstate_ID,Properties_Bundel.HeadsCaption),
    };

    private final Object[] getBindingFields() {
        Object[] BindingFields = new Object[]{J_users_UserName, J_Users_PassConfirm,J_Users_Pass, J_Users_Profile, J_Users_State};
        return BindingFields;
    }
    
    private String query = "SELECT *\n"//"SELECT u.ID,u.USERNAME,u.PASSWORD,us.ID, us.TITRE AS STATE,p.TITRE AS PROFILE\n"
            + "FROM users u\n"
            + "LEFT JOIN profiles p ON u.PROFILE=p.ID\n"
            + "LEFT JOIN usersstate us ON us.ID=u.STATE;";

    public final void InitFrame() throws SQLException {

        J_Panel_Edit.setVisible(false);
        try {

            J_Table_UsersRS = JTableSQLTool.FillTableDataFromQuery(query, J_Table_Users);
            JTableSQLTool.BindTableToFields(J_Table_Users, getBindingFields());
            JTableSQLTool.HideColumns(J_Table_Users, HideColumns);
        } catch (Exception e) {
            MyLogger.Log_to_local(e);
        }
        pack();
    }

    private void ShowEditPanel() {
        J_Panel_Edit.setVisible(true);
         JL_Add.setEnabled(false);
        JL_Edit.setEnabled(false);
        JL_Delete.setEnabled(false);
        pack();
    }

    private void HideEditPanel() {
        J_Panel_Edit.setVisible(false);
         JL_Add.setEnabled(true);
        JL_Edit.setEnabled(true);
        JL_Delete.setEnabled(true);
        
        J_Users_Pass.setText("");
        J_Users_PassConfirm.setText("");
        J_users_UserName.setText("");
        J_Users_Profile.setSelectedIndex(0);
        J_Users_State.setSelected(false);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        J_Panel_Edit = new javax.swing.JPanel();
        J_User_Name = new javax.swing.JLabel();
        J_users_UserName = new javax.swing.JTextField();
        JL_EditMode_Title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        J_User_Name2 = new javax.swing.JLabel();
        J_Users_Pass = new javax.swing.JPasswordField();
        J_User_Name3 = new javax.swing.JLabel();
        J_Users_PassConfirm = new javax.swing.JPasswordField();
        JL_Commit = new javax.swing.JLabel();
        J_Users_Profile = new javax.swing.JComboBox<>();
        J_User_Name4 = new javax.swing.JLabel();
        J_User_Name5 = new javax.swing.JLabel();
        J_Users_State = new javax.swing.JCheckBox();
        JL_Commit1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        J_Table_Users = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        J_User_Search_Value = new javax.swing.JTextField();
        J_Users_Filter = new javax.swing.JComboBox<>();
        JL_Delete = new javax.swing.JLabel();
        JL_Edit = new javax.swing.JLabel();
        JL_Add = new javax.swing.JLabel();
        JL_Exit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(85, 51, 125));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        J_User_Name.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        J_User_Name.setText("Utilisateur :");

        J_users_UserName.setText("users_USERNAME");

        JL_EditMode_Title.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        JL_EditMode_Title.setText("  Mode ");

        J_User_Name2.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        J_User_Name2.setText("Mot De Passe :");

        J_Users_Pass.setText("users_PASSWORD");

        J_User_Name3.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        J_User_Name3.setText("Confirmer Mot DE Passe :");

        J_Users_PassConfirm.setText("users_PASSWORD");

        JL_Commit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Icons/check.png"))); // NOI18N
        JL_Commit.setText("Valider");
        JL_Commit.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        JL_Commit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JL_CommitMouseClicked(evt);
            }
        });

        J_Users_Profile.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        J_Users_Profile.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Super Administrateur", "Médecin" }));
        J_Users_Profile.setName("profiles_ID"); // NOI18N

        J_User_Name4.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        J_User_Name4.setText("Profile :");

        J_User_Name5.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        J_User_Name5.setText("Active :");

        J_Users_State.setName("users_STATE"); // NOI18N

        JL_Commit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Icons/back.png"))); // NOI18N
        JL_Commit1.setText("Annuler");
        JL_Commit1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        JL_Commit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JL_Commit1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout J_Panel_EditLayout = new javax.swing.GroupLayout(J_Panel_Edit);
        J_Panel_Edit.setLayout(J_Panel_EditLayout);
        J_Panel_EditLayout.setHorizontalGroup(
            J_Panel_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(J_Panel_EditLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(JL_EditMode_Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(J_Panel_EditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(J_Panel_EditLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(J_User_Name5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(J_Panel_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(J_users_UserName)
                    .addComponent(J_Users_Pass)
                    .addComponent(J_Users_PassConfirm)
                    .addComponent(J_Users_Profile, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(J_Panel_EditLayout.createSequentialGroup()
                        .addComponent(J_Users_State)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, J_Panel_EditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_Commit1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JL_Commit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, J_Panel_EditLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(J_Panel_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(J_User_Name4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(J_User_Name3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(J_User_Name2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(J_User_Name, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(248, 248, 248))
        );
        J_Panel_EditLayout.setVerticalGroup(
            J_Panel_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(J_Panel_EditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_EditMode_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(J_Panel_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(J_User_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J_users_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(J_Panel_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(J_User_Name2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J_Users_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(J_Panel_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(J_User_Name3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J_Users_PassConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(J_Panel_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(J_Users_Profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J_User_Name4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(J_Panel_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(J_User_Name5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J_Users_State))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(J_Panel_EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Commit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_Commit1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 255));

        J_Table_Users.setAutoCreateRowSorter(true);
        J_Table_Users.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        J_Table_Users.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        J_Table_Users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Talcorp", "Active"},
                {"Test", "Inactive"}
            },
            new String [] {
                "Utilisateur", "Etat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        J_Table_Users.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        J_Table_Users.setGridColor(new java.awt.Color(255, 255, 255));
        J_Table_Users.setRowHeight(24);
        J_Table_Users.setRowMargin(3);
        J_Table_Users.setSelectionBackground(new java.awt.Color(204, 204, 255));
        J_Table_Users.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(J_Table_Users);

        jPanel4.setName("SimpleSearchPanel"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Rechercher :");

        J_User_Search_Value.setText("jTextField2");

        J_Users_Filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(J_User_Search_Value)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(J_Users_Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(J_User_Search_Value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J_Users_Filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        JL_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Icons/garbage.png"))); // NOI18N
        JL_Delete.setText("Suprimmer");
        JL_Delete.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)));

        JL_Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Icons/text.png"))); // NOI18N
        JL_Edit.setText("Modifier");
        JL_Edit.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        JL_Edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JL_EditMouseClicked(evt);
            }
        });

        JL_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Icons/plus.png"))); // NOI18N
        JL_Add.setText("Ajouter");
        JL_Add.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        JL_Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JL_AddMouseClicked(evt);
            }
        });

        JL_Exit.setBackground(new java.awt.Color(227, 217, 237));
        JL_Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Icons/logout (1).png"))); // NOI18N
        JL_Exit.setText("Quitter");
        JL_Exit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        JL_Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JL_ExitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(JL_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(JL_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                .addComponent(JL_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 461, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(74, 74, 74)
                    .addComponent(jScrollPane2)
                    .addGap(75, 75, 75)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(J_Panel_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(J_Panel_Edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Liste des utilisateurs", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1142, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JL_ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JL_ExitMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_JL_ExitMouseClicked

    private void AddMode() {
        System.out.println("add mode active");
        
        mode = Add_mode;
    }

    private void JL_AddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JL_AddMouseClicked
        // TODO add your handling code here:

        AddMode();

        ShowEditPanel();

        // pack();
    }//GEN-LAST:event_JL_AddMouseClicked

    private void JL_CommitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JL_CommitMouseClicked
        // TODO add your handling code here:

        switch (mode) {
            case Add_mode: {
                System.out.println("do insert");
                HideEditPanel();
            }
            break;
            case Edit_mode: {
                System.out.println("do Edit");
                HideEditPanel();
            }
            break;
        }
    }//GEN-LAST:event_JL_CommitMouseClicked
    private void EditMode() {
        System.out.println("Edit mode active");
        JL_EditMode_Title.setText("  Mode d'édition ");
        mode = Edit_mode;
    }
    private void JL_EditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JL_EditMouseClicked
        // TODO add your handling code here:
        EditMode();
        ShowEditPanel();
        //pack();
    }//GEN-LAST:event_JL_EditMouseClicked

    private void JL_Commit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JL_Commit1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JL_Commit1MouseClicked

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
            java.util.logging.Logger.getLogger(Permission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Permission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Permission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Permission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Permission().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Permission.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL_Add;
    private javax.swing.JLabel JL_Commit;
    private javax.swing.JLabel JL_Commit1;
    private javax.swing.JLabel JL_Delete;
    private javax.swing.JLabel JL_Edit;
    private javax.swing.JLabel JL_EditMode_Title;
    private javax.swing.JLabel JL_Exit;
    private javax.swing.JPanel J_Panel_Edit;
    private javax.swing.JTable J_Table_Users;
    private javax.swing.JLabel J_User_Name;
    private javax.swing.JLabel J_User_Name2;
    private javax.swing.JLabel J_User_Name3;
    private javax.swing.JLabel J_User_Name4;
    private javax.swing.JLabel J_User_Name5;
    private javax.swing.JTextField J_User_Search_Value;
    private javax.swing.JComboBox<String> J_Users_Filter;
    private javax.swing.JPasswordField J_Users_Pass;
    private javax.swing.JPasswordField J_Users_PassConfirm;
    private javax.swing.JComboBox<String> J_Users_Profile;
    private javax.swing.JCheckBox J_Users_State;
    private javax.swing.JTextField J_users_UserName;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}