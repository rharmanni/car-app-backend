package nl.rharmanni.carapplication.service;

import nl.rharmanni.carapplication.data.BrandRepository;
import nl.rharmanni.carapplication.data.BrandSpecification;
import nl.rharmanni.carapplication.data.ModelRepository;
import nl.rharmanni.carapplication.model.Brand;
import nl.rharmanni.carapplication.model.BrandCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrand(Integer id) { // Entity lazy loading
        Optional<Brand> brand = brandRepository.findById(id);
        return brand.orElse(null);
    }

    public Brand updateBrand(Integer id, Brand brand) {
        System.out.println(brand.getImageUrl());
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            Brand _brand = brandOptional.get();
            brand.setModels(_brand.getModels()); // Set models to prevent removal of reference.
        }
        brand.setId(id); // TODO: Check if needed.
        return brandRepository.saveAndFlush(brand);
    }

    public Brand addBrand(Brand brand) {
        return brandRepository.saveAndFlush(brand);
    }

    public void deleteBrand(Integer id) {
        brandRepository.deleteById(id);
    }

    public List<Brand> search(BrandCriteria criteria) {
        return brandRepository.findAll(new BrandSpecification(criteria));
    }

}
