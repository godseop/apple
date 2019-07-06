package org.godseop.apple.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Dummy;
import org.godseop.apple.entity.Condition;
import org.godseop.apple.model.Error;
import org.godseop.apple.model.Result;
import org.godseop.apple.service.DummyService;
import org.godseop.apple.service.S3Service;
import org.godseop.apple.util.PageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value="/dummy")
@RequiredArgsConstructor
public class DummyRestController {

    private final S3Service s3Service;

    private final DummyService dummyService;

    @PostMapping(value="/json")
    public ResponseEntity<Result> testJson(@RequestBody Dummy dummy) {
        Result result = new Result();

        log.error("Dummy => {}", dummy);
        result.put("dummy", dummyService.getDummy(dummy.getId()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/encoded")
    public ResponseEntity<Result> testEncoded(@ModelAttribute Dummy dummy) {
        Result result = new Result();

        log.error("Dummy => {}", dummy);
        result.put("dummy", dummy);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/multipart")
    public ResponseEntity<Result> testMultipart(
            @ModelAttribute Dummy dummy,  // can't use @RequestBody cuz it means use of JSON or XML data
            @RequestPart("fileMultiple") List<MultipartFile> fileList,
            @RequestPart("fileOne") MultipartFile file) throws InterruptedException {
        Result result = new Result();

        log.error("Dummy => {}", dummy);
        dummyService.mergeDummy(dummy);
        result.put("dummy", dummy);

        //멀티-단일파일 병합(concat)후 처리
        //Stream.concat(Arrays.stream(fileArray), Stream.of(file)).forEach(x-> {});
        if (!file.isEmpty()) {
            String uploadPath = s3Service.uploadBucket(file);
            result.put(file.getOriginalFilename(), uploadPath);
        }

        for (MultipartFile multipartFile : fileList) {
            if (multipartFile.isEmpty()) continue;
            File localFile = s3Service.uploadLocal(multipartFile);
            TimeUnit.MILLISECONDS.sleep(500);
            result.put(multipartFile.getOriginalFilename(), localFile.getAbsolutePath());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/bigfile")
    public ResponseEntity<Result> testBigfile(
            @RequestPart("fileList") List<MultipartFile> fileList) {
        Result result = new Result();

        if (fileList.size() == 0) {
            result.put(Error.WRONG_USER_INPUT);
        } else {
            fileList.forEach(file -> {
                String uploadPath = s3Service.uploadBigBucket(file);
                result.put(file.getOriginalFilename(), uploadPath);
            });
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value="/paging")
    public ResponseEntity<Result> testPaging(@RequestBody Condition condition) {
        Result result = new Result();
        log.error("condition => {}", condition);
        int totalCount = dummyService.getDummyListCount(condition);
        condition.setTotalCount(totalCount);
        List<Dummy> dummyList = dummyService.getDummyList(condition);

        result.put("list", dummyList);
        result.put("page", PageUtils.getPage(condition));
        //result.put("page", PageUtils.getPage(pageNumber, totalCount));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/locallist")
    public ResponseEntity<Result> testLocallist() {
        Result result = new Result();

        result.put("list", s3Service.getFileListOnLocal(""));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/s3list")
    public ResponseEntity<Result> testS3list() {
        Result result = new Result();

        result.put("list", s3Service.getFileListOnBucket(""));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/dummylist")
    public ResponseEntity<Result> testDummyListParameter(@RequestBody List<Dummy> dummyList) {
        Result result = new Result();

        log.error("dummyList : {}", dummyList);
        result.put("list", dummyList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/rest")
    public ResponseEntity<Result> testRestTemplate(@RequestParam("id") String id) {
        Result result = new Result();

        result.put("rest", dummyService.testRestTemplate(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
