/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristopher
 */
public final class MysqlDbConnection implements Closeable {
    
    private static MysqlDbConnection instance;
    
    private static final String User ="root"; 
    private static final String Password ="admin"; 
    private static final String URL ="jdbc:mysql://localhost:3306/db_user?serverTimezone=UTC"; 
    private static final String Driver ="com.mysql.jdbc.Driver";
    
    private Connection connection; 
    
    private PreparedStatement preparedSatatement;
    
    private MysqlDbConnection (){
        try {
            Class.forName(Driver);
            connection = DriverManager.getConnection(URL, User, Password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MysqlDbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MysqlDbConnection getInstance(){
        return instance == null ? new MysqlDbConnection() : instance; 
    }
    
    public ResultSet excuteQuery (final String query) throws SQLException{
     this.preparedSatatement = connection.prepareStatement(query);
     return this.preparedSatatement.executeQuery();
    }
    
    @Override
    public void close() throws IOException {
        try {
            if(!connection.isClosed()){
                this.connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
