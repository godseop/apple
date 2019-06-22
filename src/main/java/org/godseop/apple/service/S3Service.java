package org.godseop.apple.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.exception.SystemException;
import org.godseop.apple.model.Error;
import org.godseop.apple.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:aws.properties")
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String appleBucket;

    @Value("${local.upload.path}")
    private String localPath;


    public File uploadLocal(MultipartFile multipartFile) {
        checkFileValidation(multipartFile);

        return writeFile(multipartFile).orElseThrow(SystemException::new);
    }

    public String uploadBucket(MultipartFile multipartFile) {
        checkFileValidation(multipartFile);

        String fileName = setFileNameWithPath(multipartFile);

        try {
            amazonS3.putObject(
                    new PutObjectRequest(appleBucket,
                            fileName,
                            multipartFile.getInputStream(),
                            getObjectMetadata(multipartFile))
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return amazonS3.getUrl(appleBucket, fileName).toString();
        } catch (IOException exception) {
            throw new AppleException(Error.FILE_UPLOAD_TO_S3_FAIL);
        }
    }


    public String uploadBigBucket(MultipartFile multipartFile) {
        checkFileValidation(multipartFile);

        String fileName = setFileNameWithPath(multipartFile);

        try {
            TransferManager transferManager = TransferManagerBuilder.standard()
                    .withS3Client(amazonS3)
                    .build();

            Upload upload = transferManager.upload(appleBucket, fileName, multipartFile.getInputStream(), getObjectMetadata(multipartFile));

            upload.waitForCompletion();

            return amazonS3.getUrl(appleBucket, fileName).toString();
        } catch (IOException | InterruptedException exception) {
            throw new AppleException(Error.FILE_UPLOAD_TO_S3_FAIL);
        }
    }

    public List<String> getFileListOnBucket(String dirName) {
        List<S3ObjectSummary> summaryList =  amazonS3.listObjects(appleBucket, dirName).getObjectSummaries();

        return summaryList.stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }


    private boolean deleteLocalFile(String filePath) {
        return new File(filePath).delete();

    }

    private boolean checkFileValidation(MultipartFile multipartFile) {
        Optional.ofNullable(multipartFile)
                .orElseThrow(() -> new AppleException(Error.MULTIPART_FILE_NOT_EXISTS));

        // TODO file content-type 정합성 체크로직을 여기에 작성하세요
        String contentType = multipartFile.getContentType();
        return true;
    }

    private String setFileNameWithPath(MultipartFile multipartFile) {
        // TODO 파일타입이나 용도에 따른 경로 설정로직을 여기에 작성하세요
        return "static/" + DateUtils.getDateTimeStamp() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
    }


    private ObjectMetadata getObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        return objectMetadata;
    }


    private Optional<File> writeFile(MultipartFile multipartFile) {
        String fileName = setFileNameWithPath(multipartFile);

        try {
            File localFile = new File(localPath + fileName);
            multipartFile.transferTo(localFile);

            return Optional.of(localFile);
        } catch (IOException exception) {
            throw new AppleException(Error.FILE_UPLOAD_TO_LOCAL_FAIL);
        }
    }

}
