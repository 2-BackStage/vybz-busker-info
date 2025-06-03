package com.vybz.busker_info_service.common.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class AmazonS3UploaderUtil {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private static final List<String> ALLOWED_EXTENSIONS = List.of("jpg", "jpeg", "png", "webp");

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    public String upload(MultipartFile file, String dirName) {
        validateFile(file);

        String fileName = dirName + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(fileName)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, file.getSize()));

            return s3Client.utilities()
                    .getUrl(builder -> builder.bucket(bucket).key(fileName))
                    .toExternalForm();
        } catch (IOException e) {
            log.error("S3 업로드 실패", e);
            throw new IllegalStateException("S3 업로드 중 오류 발생", e);
        }
    }

    public void delete(String fileUrl) {
        String key = extractKeyFromUrl(fileUrl);

        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        s3Client.deleteObject(deleteRequest);
    }

    private String extractKeyFromUrl(String fileUrl) {
        return java.net.URI.create(fileUrl).getPath().substring(1);
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일은 업로드할 수 없습니다.");
        }

        String filename = file.getOriginalFilename();
        if (filename == null || !hasAllowedExtension(filename)) {
            throw new IllegalArgumentException("허용되지 않은 파일 형식입니다. (jpg, jpeg, png, webp 만 가능)");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("파일 용량은 5MB 이하만 업로드할 수 있습니다.");
        }
    }

    private boolean hasAllowedExtension(String filename) {
        String extension = getExtension(filename);
        return ALLOWED_EXTENSIONS.contains(extension.toLowerCase());
    }

    private String getExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex != -1) ? filename.substring(dotIndex + 1) : "";
    }
}