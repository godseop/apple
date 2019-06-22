package org.godseop.apple.controller.rest;

import lombok.RequiredArgsConstructor;
import org.godseop.apple.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/post")
public class PostRestController {

    private PostService postService;

    @GetMapping(value="/test")
    public void test() {
        postService.test();
    }
    
}
