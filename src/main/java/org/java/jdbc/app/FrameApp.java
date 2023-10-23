/*
 * Copyright (c) 2023, yorly
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
package org.java.jdbc.app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author yorly
 */
public class FrameApp extends JFrame{
    private JComboBox sections;
    private JComboBox countries;
    private JTextArea results;
    private PreparedStatement sendQuerySection;
    private PreparedStatement sendQueryCountry;
    private PreparedStatement sendQueryAll;
    private final String sectionQuery = "SELECT * FROM products WHERE"
            + " section = ?;";
    private final String countryQuery = "SELECT * FROM products WHERE"
            + " country = ?;";
    private final String allQuery = "SELECT * FROM products WHERE"
            + " section = ? AND country = ?;";
    private Connection myConn;
    
    public FrameApp(){
        setTitle("DB Query");
        setBounds(500,300,400,400);
        setLayout(new BorderLayout());
        JPanel menus = new JPanel();
        menus.setLayout(new FlowLayout());
        sections = new JComboBox();
        sections.addItem("All");
        countries = new JComboBox();
        countries.addItem("All");
        results = new JTextArea(4,50);
        results.setEditable(false);
        add(results);
        menus.add(sections);
        menus.add(countries);
        add(menus, BorderLayout.NORTH);
        add(results, BorderLayout.CENTER);
        JButton buttonQuery = new JButton("Search");
        buttonQuery.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sendQuery();
            }
        
        });
        
        add(buttonQuery, BorderLayout.SOUTH);
        
        
        // Connect with DB
        
        String url = "jdbc:mariadb://localhost:3306/test?user=yorlys&password=yorlys";
        try {
            myConn = DriverManager.getConnection(url);
            Statement myStatement = myConn.createStatement();
            String querSql = "SELECT DISTINCTROW section FROM products;";
            ResultSet result = myStatement.executeQuery(querSql);
            while(result.next()){
                sections.addItem(result.getString("section"));
            }
            result.close();
            querSql = "SELECT DISTINCTROW country FROM products;";
            result = myStatement.executeQuery(querSql);
            while(result.next()){
                countries.addItem(result.getString("country"));
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrameApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendQuery(){
        ResultSet result = null;
        String section = sections.getSelectedItem().toString();
        String country = countries.getSelectedItem().toString();
        try {
            results.setText("");
            if(!section.equals("All") && country.equals("All")){
                sendQuerySection = myConn.prepareStatement(sectionQuery);
                sendQuerySection.setString(1, section);
                result = sendQuerySection.executeQuery();
            } else if(section.equals("All") && !country.equals("All")){
                sendQueryCountry = myConn.prepareStatement(countryQuery);
                sendQueryCountry.setString(1, country);
                result = sendQueryCountry.executeQuery();
            } else if(!section.equals("All") && !country.equals("All")){
                sendQueryAll = myConn.prepareStatement(allQuery);
                sendQueryAll.setString(1, section);
                sendQueryAll.setString(2, country);
                result = sendQueryAll.executeQuery();
            } else {
                Statement myStatement = myConn.createStatement();
                result = myStatement.executeQuery("SELECT * FROM products;");
            }
            
            String products = "";
            while(result.next()){
                products += "ID: " + result.getString("id") + " Name: " +
                        result.getString("name") + " Code: " + result.getString("code") +
                        " Price: " + result.getString("price") + " Date: " + result.getString("date")
                + " Country: " + result.getString("country") + 
                        " Setcion: " + result.getString("section") + "\n";
            }
            results.append(products);
        } catch (SQLException ex) {
            Logger.getLogger(FrameApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
