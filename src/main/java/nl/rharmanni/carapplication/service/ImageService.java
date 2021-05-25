package nl.rharmanni.carapplication.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    public static String imagePath = "images/";

    public void saveImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Path path = Paths.get("images/" + file.getOriginalFilename());
            Files.write(path, file.getBytes());
        }
    }

}
