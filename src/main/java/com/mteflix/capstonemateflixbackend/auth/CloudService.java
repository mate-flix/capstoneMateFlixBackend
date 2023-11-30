package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.exceptions.MediaUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudService {
    String upload(MultipartFile multipartFile) throws MediaUploadException;
}
