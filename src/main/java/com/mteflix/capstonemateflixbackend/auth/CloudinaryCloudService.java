package africa.semicolon.gemstube.services;

import africa.semicolon.gemstube.exceptions.MediaUploadException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class CloudinaryCloudService implements CloudService{
    private final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile multipartFile) throws MediaUploadException {

        try {
            Map<?, ?> uploadResponse = cloudinary.uploader().upload(multipartFile.getBytes(),
                    ObjectUtils.asMap(
                    "resource_type", "auto"
            ));
            String url = (String) uploadResponse.get("secure_url");
            return url;
        }catch (IOException exception){
            throw new MediaUploadException(exception.getMessage());
        }

    }
}
