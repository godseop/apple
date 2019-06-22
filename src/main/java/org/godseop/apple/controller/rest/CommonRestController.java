package org.godseop.apple.controller.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.model.Result;
import org.godseop.apple.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value="/common")
@RequiredArgsConstructor
public class CommonRestController {

    private final CommonService commonService;

    @PostMapping(value="/now")
    public ResponseEntity<Result> now() {
        Result result = new Result();
        result.put("now", commonService.getServerTime());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
