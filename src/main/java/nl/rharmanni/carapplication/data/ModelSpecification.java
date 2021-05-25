package nl.rharmanni.carapplication.data;

import nl.rharmanni.carapplication.model.BrandCriteria;
import nl.rharmanni.carapplication.model.Model;
import nl.rharmanni.carapplication.model.ModelCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ModelSpecification implements Specification<Model> {

    private final ModelCriteria criteria;

    public ModelSpecification(ModelCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> p = new ArrayList<>();
        if (criteria.getName() != null)
            p.add(builder.like(builder.upper(root.get("name")), "%" + criteria.getName().toUpperCase() + "%"));
        if (criteria.getGeneration() != null)
            p.add(builder.equal(root.get("generation"), criteria.getGeneration()));
        if (criteria.getBrandId() != null)
            p.add(builder.equal(root.get("brand").get("id"), criteria.getBrandId()));
        return builder.and(p.toArray(new Predicate[0]));
    }
}
