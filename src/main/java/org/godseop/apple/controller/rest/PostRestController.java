package org.godseop.apple.controller.rest;

import org.godseop.apple.service.PostServiceJooq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/post")
public class PostRestController {

    @Autowired
    private PostServiceJooq postService;

    @GetMapping(value="test")
    public void jooqTest() {
        postService.test();
    }
    
}
