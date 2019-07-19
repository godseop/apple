package org.godseop.apple.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.godseop.apple.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("memberMapper")
public interface MemberMapper {

    List<Member> selectMemberListAll();

    int insertMember(Member member);

    int mergeMember(Member member);

}
