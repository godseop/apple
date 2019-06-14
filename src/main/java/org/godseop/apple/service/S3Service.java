package org.godseop.apple.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class S3Service {

    private final AmazonS3 amazonS3;

    S3Service() {
        BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAXZFJGQFVHXJ2QT7Q", "DGZChQL9rAO62+QmYfXKzSQBnLdH4DOzUwNWbXi9");
        amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }

    private String bucketName = "apple-storage";



    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File file = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("FILE 전환 실패"));

        return upload(file, dirName);
    }

    public String upload(File file, String dirName) {
        String fileName = dirName + "/" + file.getName();
        String uploadPath = putS3(file, fileName);
        removeNewFile(file);
        return uploadPath;
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

}
