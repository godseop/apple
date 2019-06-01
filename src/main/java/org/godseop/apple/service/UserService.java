package org.godseop.apple.service;

import org.godseop.apple.model.User;
import org.godseop.apple.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getUserListAll() {
        return userDao.selectUserListAll();
    }

}
