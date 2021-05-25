package nl.rharmanni.carapplication.service;

import nl.rharmanni.carapplication.data.ModelRepository;
import nl.rharmanni.carapplication.data.ModelSpecification;
import nl.rharmanni.carapplication.data.TrimRepository;
import nl.rharmanni.carapplication.model.Model;
import nl.rharmanni.carapplication.model.ModelCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> getModels() {
        return modelRepository.findAll();
    }

    public Model getModel(Integer id) { // Entity lazy loading
        Optional<Model> model = modelRepository.findById(id);
        return model.orElse(null);
    }

    public Model updateModel(Integer id, Model model) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()) {
            Model _model = modelOptional.get();
            model.setTrims(_model.getTrims()); // Set models voor voorkomen removal of reference.
        }
        model.setId(id); // TODO: Check if needed.
        return modelRepository.saveAndFlush(model);
    }

    public Model addModel(Model model) {
        System.out.println("Adding the model");
        return modelRepository.saveAndFlush(model);
    }

    public void deleteModel(Integer id) {
        modelRepository.deleteById(id);
    }

    public List<Model> search(ModelCriteria criteria) {
        return modelRepository.findAll(new ModelSpecification(criteria));
    }

}
