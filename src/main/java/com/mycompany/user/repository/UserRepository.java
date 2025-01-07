
package com.mycompany.user.repository;

import com.mycompany.connection.MysqlDbConnection;
import com.mycompany.user.model.UserEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    private final static String QUERY_LOGIN =  new StringBuilder()
    .append("SELECT id_user, user_name, password FROM user WHERE user_name = ´%S´ AND password = ´%S´").toString();
    @Override
    public UserEntity getByLogin(String user, String password) throws SQLException {
        UserEntity response = new UserEntity();
        try(MysqlDbConnection db = MysqlDbConnection.getInstance()){
            ResultSet result = db.excuteQuery(String.format(QUERY_LOGIN, user,password));
            while (result.next()){
                response.setId(result.getInt("id_user"));
                response.setUsername(result.getNString("user_name"));
                response.setPassword(result.getString("password"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return response;
    }
    
}
