package org.godseop.apple.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Member;
import org.godseop.apple.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value="/dummy")
public class DummyRestController {

    @PostMapping(value="json")
    public ResponseEntity<Result> testJson(@RequestBody Member member) {
        Result result = new Result();
        result.put("member", member);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="encoded")
    public ResponseEntity<Result> testEncoded(@ModelAttribute Member member) {
        Result result = new Result();
        result.put("member", member);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
