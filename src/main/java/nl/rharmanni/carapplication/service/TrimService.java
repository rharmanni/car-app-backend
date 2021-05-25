package nl.rharmanni.carapplication.service;

import nl.rharmanni.carapplication.data.BrandSpecification;
import nl.rharmanni.carapplication.data.TrimRepository;

import nl.rharmanni.carapplication.data.TrimSpecification;
import nl.rharmanni.carapplication.model.Brand;
import nl.rharmanni.carapplication.model.BrandCriteria;
import nl.rharmanni.carapplication.model.Trim;
import nl.rharmanni.carapplication.model.TrimCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrimService {

    private final TrimRepository trimRepository;

    public TrimService(TrimRepository trimRepository) {
        this.trimRepository = trimRepository;
    }

    public List<Trim> getTrims() {
        return trimRepository.findAll();
    }

    public Trim getTrim(Integer id) { // Entity lazy loading
        Optional<Trim> Trim = trimRepository.findById(id);
        return Trim.orElse(null);
    }

    public Trim updateTrim(Integer id, Trim Trim) {
        Trim.setId(id); // TODO: Check als nodig.
        return trimRepository.saveAndFlush(Trim);
    }

    public Trim addTrim(Trim Trim) {
        return trimRepository.saveAndFlush(Trim);
    }

    public void deleteTrim(Integer id) {
        trimRepository.deleteById(id);
    }

    public List<Trim> search(TrimCriteria criteria) {
        return trimRepository.findAll(new TrimSpecification(criteria));
    }

}