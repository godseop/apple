package org.godseop.apple.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.godseop.apple.entity.Dummy;
import org.godseop.apple.entity.Condition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("dummyMapper")
public interface DummyMapper {

    // MyBatis Return
    // SELECT - select에 해당하는 결과
    // INSERT - 1 (여러개일 경우도 1)
    // UPDATE - update된 행의 개수 (없다면 0)
    // DELETE - delete된 행의 개수 (없다면 0)

    int selectDummyListCount(Condition condition);

    List<Dummy> selectDummyList(Condition condition);

    List<Dummy> selectDummy(Dummy dummy);

    int insertDummy(Dummy dummy);

    int updateDummy(Dummy dummy);

    void upsertDummy(Dummy dummy);

    int deleteDummy(Long id);
}
