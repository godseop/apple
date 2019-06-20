package org.godseop.apple.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.godseop.apple.entity.Dummy;
import org.godseop.apple.entity.Member;
import org.godseop.apple.model.Condition;
import org.godseop.apple.model.Result;
import org.godseop.apple.service.DummyService;
import org.godseop.apple.service.S3Service;
import org.godseop.apple.util.PageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping(value="/dummy")
@RequiredArgsConstructor
public class DummyRestController {

    private final S3Service s3Service;

    private final DummyService dummyService;

    @PostMapping(value="json")
    public ResponseEntity<Result> testJson(@RequestBody Member member) {
        Result result = new Result();
        log.error("MEMBER INFO : {}", member);
        result.put("member", member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="encoded")
    public ResponseEntity<Result> testEncoded(@ModelAttribute Member member) {
        Result result = new Result();
        result.put("member", member);

        log.error("MEMBER INFO : {}", member);
        log.error("POST LIST : {}", member.getPostList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="multipart")
    public ResponseEntity<Result> testMultipart(
            @ModelAttribute Member member,  // can't use @RequestBody cuz it means use of JSON or XML data
            @RequestPart("fileMultiple") MultipartFile[] fileArray,
            @RequestPart("fileOne") MultipartFile file) {
        Result result = new Result();

        log.info("MEMBER INFO : {}", member);
        result.put("member", member);
        log.info("POST LIST : {}", member.getPostList());

        String uploadPath = s3Service.uploadBucket(file);
        result.put("uploadPath", uploadPath);

        Stream.concat(Arrays.stream(fileArray), Stream.of(file)).forEach(x -> {
            Map<String, Object> map = new HashMap<>();
            String name = x.getOriginalFilename();
            map.put("name", name);
            map.put("extension", FilenameUtils.getExtension(name));
            map.put("size", x.getSize());
            map.put("type", x.getContentType());
            result.put(name, map);
        });


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="paging")
    public ResponseEntity<Result> testPaging(@RequestBody Condition condition) {
        Result result = new Result();

        int totalCount = dummyService.selectDummyListCount();
        condition.setTotalCount(totalCount);
        List<Dummy> dummyList = dummyService.selectDummyList(condition);

        result.put("list", dummyList);
        result.put("page", PageUtils.getPage(condition));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value="s3list")
    public ResponseEntity<Result> testS3list() {
        Result result = new Result();

        result.put("list", s3Service.getFileListOnBucket("static"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
