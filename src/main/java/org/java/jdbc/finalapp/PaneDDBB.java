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
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class PaneDDBB extends JPanel{
    private JComboBox tablesCombo;
    private JTextArea informationArea;
    private Connection myConn;
    
    public PaneDDBB(){
        setLayout(new BorderLayout());
        tablesCombo = new JComboBox();
        informationArea = new JTextArea();
        add(informationArea, BorderLayout.CENTER);
        add(tablesCombo, BorderLayout.NORTH);
        connectBD();
        getTables();
    }
    
    public void connectBD(){
        myConn = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test?user=yorlys&password=yorlys");
            
        } catch (SQLException ex) {
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
            
            
    
}
