package org.godseop.apple.mapper.mysql;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("commonMapper")
public interface CommonMapper {

    long selectServerTime();

}
