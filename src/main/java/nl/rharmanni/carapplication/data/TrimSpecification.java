package nl.rharmanni.carapplication.data;

import nl.rharmanni.carapplication.model.Trim;
import nl.rharmanni.carapplication.model.TrimCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class TrimSpecification implements Specification<Trim> {

    private final TrimCriteria criteria;

    public TrimSpecification(TrimCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Trim> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> p = new ArrayList<>();
        if (criteria.getName() != null && !criteria.getName().isEmpty())
            p.add(builder.like(builder.upper(root.get("name")), "%" + criteria.getName().toUpperCase() + "%"));
        if (criteria.getBrandId() != null)
            p.add(builder.equal(root.get("model").get("brand").get("id"), criteria.getBrandId()));
        if (criteria.getModelId() != null)
            p.add(builder.equal(root.get("model").get("id"), criteria.getModelId()));
        if (criteria.getEngine() != null && !criteria.getEngine().isEmpty())
            p.add(builder.like(builder.upper(root.get("engine")), criteria.getEngine().toUpperCase()));
        if (criteria.getTransmission() != null && !criteria.getTransmission().isEmpty())
            p.add(builder.like(builder.upper(root.get("transmission")), criteria.getTransmission().toUpperCase()));
        if (criteria.getBodyStyle() != null && !criteria.getBodyStyle().isEmpty())
            p.add(builder.like(builder.upper(root.get("bodyStyle")), criteria.getBodyStyle().toUpperCase()));
        if (criteria.getYearBuilt() != null)
            p.add(builder.equal(root.get("yearBuilt"), criteria.getYearBuilt()));
        return builder.and(p.toArray(new Predicate[0]));
    }
}
