package com.haliri.israj.appcore.dao.common;

import com.haliri.israj.appcore.domain.common.User;
import org.springframework.stereotype.Service;

/**
 * Created by israjhaliri on 11/21/17.
 */
@Service
public interface UserDAO extends BaseDAO<User, Object> {

    void saveToken(String token, String username);

    void deleteToken(String username);
}