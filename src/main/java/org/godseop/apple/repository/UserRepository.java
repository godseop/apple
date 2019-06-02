package org.godseop.apple.repository;

import org.godseop.apple.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	

}
