package org.godseop.apple.repository.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.godseop.apple.entity.Dummy;
import org.godseop.apple.model.Condition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("dummyMapper")
public interface DummyMapper {

    int selectDummyListCount(Condition condition);

    List<Dummy> selectDummyList(Condition condition);

    Dummy selectDummy(Long id);
}
