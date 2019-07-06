package org.godseop.apple.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.model.Result;
import org.godseop.apple.service.DummyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value="/external")
@RequiredArgsConstructor
public class ExternalRestController {

    private final DummyService dummyService;


    @PostMapping(value="/google/book")
    public ResponseEntity<Result> testBookInfo(@RequestParam("isbn") String isbn) {
        Result result = new Result();

        result.put("book", dummyService.getBookInfo(isbn));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value="/github/user")
    public ResponseEntity<Result> testUserInfo(@RequestParam("githubId") String githubId) {
        Result result = new Result();

        result.put("user", dummyService.getGithubUserInfo(githubId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
