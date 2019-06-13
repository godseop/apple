package org.godseop.apple.repository;

import org.godseop.apple.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member getByUid(String uid);

    Member findByUid(String uid);

    Member findByNickname(String nickname);

}
