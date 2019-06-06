package org.godseop.apple.service;

//import static org.godseop.apple.jooq.tables.Comment.COMMENT;
//import static org.godseop.apple.jooq.tables.Post.POST;

import javax.transaction.Transactional;

import org.godseop.apple.entity.Comment;
import org.godseop.apple.entity.Post;
import org.godseop.apple.repository.CommentRepository;
import org.godseop.apple.repository.PostRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
@Transactional
public class PostServiceJooq {

	@Autowired
	DSLContext create;

	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentRepository commentRepository;

	public void test() {
		commentRepository.deleteAll();
		postRepository.deleteAll();

		Post post = new Post();
		post.setContent("첫번째글 남깁니다요.");
		post.setTitle("첫 글");
		postRepository.save(post);

		Comment c1 = new Comment();
		c1.setContent("TEST1");
		c1.setPost(post);
		commentRepository.save(c1);

		Comment c2 = new Comment();
		c2.setContent("TEST2");
		c2.setPost(post);
		commentRepository.save(c2);

		/*
		 * http://www.jooq.org/doc/3.11/manual/sql-building/sql-statements/select-statement/join-clause/
		 * 
		 */

//		Result<?> result1 =
//				create
//				.select()
//				.from(POST.join(COMMENT).on(COMMENT.POST_ID.eq(COMMENT.ID)))
//				.fetch();
//		System.out.println(result1);
//
//		Result<?> result2 =
//				create
//				.select()
//				.from(POST)
//				.join(COMMENT)
//				.on(COMMENT.POST_ID.equal(POST.ID))
//				.fetch();
//		System.out.println(result2);

	}

}
