package org.godseop.apple.repository.mysql;

import org.godseop.apple.entity.mysql.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
