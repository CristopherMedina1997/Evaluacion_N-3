
package com.mycompany.user.repository;

import com.mycompany.user.model.UserEntity;
import java.sql.SQLException;

public interface IUserRepository {
    UserEntity getByLogin (final String user, final String password) throws SQLException;
}
