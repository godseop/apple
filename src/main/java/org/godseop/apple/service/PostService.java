package org.godseop.apple.service;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.godseop.apple.entity.Comment;
import org.godseop.apple.entity.Post;
import org.godseop.apple.repository.CommentRepository;
import org.godseop.apple.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

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

    }

}
