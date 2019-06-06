package org.godseop.apple.repository;

import org.apache.ibatis.annotations.Mapper;
import org.godseop.apple.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("userDaoMybatis")
public interface UserDao {

    List<User> selectUserListAll();
}
