package nl.rharmanni.carapplication.control;

import nl.rharmanni.carapplication.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

    private final ImageService imageService;

    public FileController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<MultipartFile> uploadFile(MultipartFile file) {
        try {
            imageService.saveImage(file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
