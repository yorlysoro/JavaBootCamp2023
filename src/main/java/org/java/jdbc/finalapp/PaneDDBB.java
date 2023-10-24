/*
 * Copyright (c) 2023, yorlysoropeza <yorlysoro@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.java.jdbc.finalapp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class PaneDDBB extends JPanel{
    private JComboBox tablesCombo;
    private JTextArea informationArea;
    private Connection myConn;
    private FileReader input;
    
    public PaneDDBB(){
        setLayout(new BorderLayout());
        tablesCombo = new JComboBox();
        informationArea = new JTextArea();
        add(informationArea, BorderLayout.CENTER);
        connectBD();
        getTables();
        tablesCombo.addActionListener((ActionEvent e) -> {
            String tableName = tablesCombo.getSelectedItem().toString();
            showInfoTable(tableName);
        });
        add(tablesCombo, BorderLayout.NORTH);
    }
    
    public void connectBD(){
        myConn = null;
        String dataConfig[] = new String[3];
        try {
            input = new FileReader("C:\\Users\\yorly\\Git\\JavaBootCamp2023\\src\\main\\java\\org\\java\\jdbc\\finalapp\\datas_config.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PaneDDBB.class.getName()).log(Level.SEVERE, null, ex);
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Config Files", "txt", "config");
            chooser.setFileFilter(filter);
            Integer returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                String path = chooser.getSelectedFile().getAbsolutePath();
                try {
                    input = new FileReader(path);
                } catch (FileNotFoundException ex1) {
                    Logger.getLogger(PaneDDBB.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }

        try {
            BufferedReader myBuffer = new BufferedReader(input);
            for(int i = 0; i <= 2; i++){
                dataConfig[i] = myBuffer.readLine();
            }
            myConn = DriverManager.getConnection(dataConfig[0], dataConfig[1], dataConfig[2]);
            input.close();
        } catch (SQLException ex) {
            Logger.getLogger(PaneDDBB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PaneDDBB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getTables(){
        ResultSet result = null;
        try {
            DatabaseMetaData metadata = myConn.getMetaData();
            result = metadata.getTables(null, null, null, null);
            while(result.next()){
                tablesCombo.addItem(result.getString("TABLE_NAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaneDDBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void showInfoTable(String tableName) {
        ArrayList<String> columnNames = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        Statement myStatement;
        try {
            myStatement = myConn.createStatement();
            ResultSet myResult = myStatement.executeQuery(query);
            ResultSetMetaData rsDB = myResult.getMetaData();
            for(int i = 1; i < rsDB.getColumnCount(); i++){
                columnNames.add(rsDB.getColumnLabel(i));
            }
            informationArea.setText("");
            while(myResult.next()){
                for(String column: columnNames){
                    informationArea.append(myResult.getString(column) + " ");
                }
                informationArea.append("\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaneDDBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
            
            
    
}
