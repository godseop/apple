package org.godseop.apple.repository;

import org.apache.ibatis.annotations.Mapper;
import org.godseop.apple.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    List<User> selectUserListAll();
}
