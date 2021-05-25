package nl.rharmanni.carapplication.control;

import nl.rharmanni.carapplication.data.BrandSpecification;
import nl.rharmanni.carapplication.model.Brand;
import nl.rharmanni.carapplication.model.BrandCriteria;
import nl.rharmanni.carapplication.model.Model;
import nl.rharmanni.carapplication.service.BrandService;
import nl.rharmanni.carapplication.service.ImageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BrandController {

    private final BrandService brandService;
    private final ImageService imageService;

    public BrandController(BrandService brandService, ImageService imageService) {
        this.brandService = brandService;
        this.imageService = imageService;
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getBrands() {
        return new ResponseEntity<>(brandService.getBrands(), HttpStatus.OK);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrand(@PathVariable Integer id) {
        return new ResponseEntity<>(brandService.getBrand(id), HttpStatus.OK);
    }

    @PostMapping(value = "/brands")
    public ResponseEntity<Brand> addBrand(@RequestPart("file") MultipartFile file,
                                          @Valid @RequestPart("brand") Brand brand,
                                          BindingResult result) {
        try {
            imageService.saveImage(file);
            if (!file.isEmpty()) {
                brand.setImageUrl(file.getOriginalFilename());
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(brandService.addBrand(brand), HttpStatus.CREATED);
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Integer id,
                                             @RequestPart("file") MultipartFile file,
                                             @Valid @RequestPart("brand") Brand brand,
                                             BindingResult result) {
        System.out.println(brand);
        try {
            imageService.saveImage(file);
            if (!file.isEmpty()) {
                brand.setImageUrl(file.getOriginalFilename());
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(brandService.updateBrand(id, brand), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Brand> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // TODO: Aanpassen, geen brands-search maar /brands in GET request.
    @RequestMapping(value = "/brands-search", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<List<Brand>> search(@Valid @RequestBody BrandCriteria criteria) { // Pageable pageable
        return new ResponseEntity<>(brandService.search(criteria), HttpStatus.OK);
    }

}