package org.godseop.apple.repository;

import org.godseop.apple.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	

}
