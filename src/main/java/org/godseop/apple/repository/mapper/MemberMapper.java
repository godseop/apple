package org.godseop.apple.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.godseop.apple.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("memberMapper")
public interface MemberMapper {

    // MyBatis Return
    // SELECT - select에 해당하는 결과
    // INSERT - 1 (여러개일 경우도 1)
    // UPDATE - update된 행의 개수 (없다면 0)
    // DELETE - delete된 행의 개수 (없다면 0)


    List<Member> selectMemberListAll();

    Long insertMember(Member member);

    int mergeMember(Member member);

}
