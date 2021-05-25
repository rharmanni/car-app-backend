package nl.rharmanni.carapplication.control;

import nl.rharmanni.carapplication.model.Model;
import nl.rharmanni.carapplication.model.ModelCriteria;
import nl.rharmanni.carapplication.model.Trim;
import nl.rharmanni.carapplication.model.TrimCriteria;
import nl.rharmanni.carapplication.service.ImageService;
import nl.rharmanni.carapplication.service.TrimService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TrimController {

    private final TrimService trimService;
    private final ImageService imageService;

    public TrimController(TrimService trimService, ImageService imageService) {
        this.trimService = trimService;
        this.imageService = imageService;
    }

    @GetMapping("/trims")
    public ResponseEntity<List<Trim>> getTrims() {
        return new ResponseEntity<>(trimService.getTrims(), HttpStatus.OK);
    }

    @GetMapping("/trims/{id}")
    public ResponseEntity<Trim> getTrim(@PathVariable Integer id) {
        return new ResponseEntity<>(trimService.getTrim(id), HttpStatus.OK);
    }

    @PostMapping("/trims")
    public ResponseEntity<Trim> addTrim(@RequestPart("file") MultipartFile file,
                                        @Valid @RequestPart("trim") Trim trim,
                                        BindingResult result) {
        try {
            imageService.saveImage(file);
            if (!file.isEmpty()) {
                trim.setImageUrl(file.getOriginalFilename());
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(trimService.addTrim(trim), HttpStatus.CREATED);
    }

    @PutMapping("/trims/{id}")
    public ResponseEntity<Trim> updateTrim(@PathVariable Integer id,
                                           @RequestPart("file") MultipartFile file,
                                           @Valid @RequestPart("trim") Trim trim,
                                           BindingResult result) {
        try {
            imageService.saveImage(file);
            if (!file.isEmpty()) {
                trim.setImageUrl(file.getOriginalFilename());
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(trimService.updateTrim(id, trim), HttpStatus.OK);
    }

    @DeleteMapping("/trims/{id}")
    public ResponseEntity<Trim> deleteTrim(@PathVariable Integer id) {
        trimService.deleteTrim(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/trims-search") // TODO: Aanpassen, geen trims-search maar /trims in GET request.
    public ResponseEntity<List<Trim>> search(@RequestBody TrimCriteria criteria) {
        return new ResponseEntity<>(trimService.search(criteria), HttpStatus.OK);
    }

}