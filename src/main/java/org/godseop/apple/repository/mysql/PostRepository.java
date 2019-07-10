package org.godseop.apple.repository.mysql;

import org.godseop.apple.entity.mysql.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
