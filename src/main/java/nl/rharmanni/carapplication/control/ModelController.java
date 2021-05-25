package nl.rharmanni.carapplication.control;

import nl.rharmanni.carapplication.model.Model;
import nl.rharmanni.carapplication.model.ModelCriteria;
import nl.rharmanni.carapplication.service.ImageService;
import nl.rharmanni.carapplication.service.ModelService;
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
public class ModelController {

    private final ModelService modelService;
    private final ImageService imageService;

    public ModelController(ModelService modelService, ImageService imageService) {
        this.modelService = modelService;
        this.imageService = imageService;
    }

    @GetMapping("/models")
    public ResponseEntity<List<Model>> getModels() {
        return new ResponseEntity<>(modelService.getModels(), HttpStatus.OK);
    }

    @GetMapping("/models/{id}")
    public ResponseEntity<Model> getModel(@PathVariable Integer id) {
        return new ResponseEntity<>(modelService.getModel(id), HttpStatus.OK);
    }

    @PostMapping("/models")
    public ResponseEntity<Model> addModel(@RequestPart("file") MultipartFile file,
                                          @Valid @RequestPart("model") Model model,
                                          BindingResult result) {
        try {
            imageService.saveImage(file);
            if (!file.isEmpty()) {
                model.setImageUrl(file.getOriginalFilename());
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelService.addModel(model), HttpStatus.CREATED);
    }

    @PutMapping("/models/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable Integer id,
                                             @RequestPart("file") MultipartFile file,
                                             @Valid @RequestPart("model") Model model,
                                             BindingResult result) {
        try {
            imageService.saveImage(file);
            if (!file.isEmpty()) {
                model.setImageUrl(file.getOriginalFilename());
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelService.updateModel(id, model), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/models/{id}")
    public ResponseEntity<Model> deleteModel(@PathVariable Integer id) {
        modelService.deleteModel(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/models-search") // TODO: Aanpassen, geen models-search maar /models in GET request.
    public ResponseEntity<List<Model>> search(@RequestBody ModelCriteria criteria) {
        System.out.println(criteria.getName());
        System.out.println(modelService.search(criteria).get(0));
        return new ResponseEntity<>(modelService.search(criteria), HttpStatus.OK);
    }


}
