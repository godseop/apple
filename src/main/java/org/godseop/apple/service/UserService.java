package org.godseop.apple.service;

import org.godseop.apple.entity.User;
import org.godseop.apple.repository.UserDao;
import org.godseop.apple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

	@Qualifier("userDaoMybatis")
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserRepository userRepository;

    public List<User> getUserListAll() {
        return userDao.selectUserListAll();
    }
    
    public List<User> getUserListAllJpa() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.getOne(id);
    }

}
