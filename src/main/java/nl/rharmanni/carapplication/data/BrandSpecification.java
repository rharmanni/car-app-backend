package nl.rharmanni.carapplication.data;

import nl.rharmanni.carapplication.model.Brand;
import nl.rharmanni.carapplication.model.BrandCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BrandSpecification implements Specification<Brand> {

    private final BrandCriteria criteria;

    public BrandSpecification(BrandCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> p = new ArrayList<>();
        if (criteria.getName() != null)
            p.add(builder.like(builder.upper(root.get("name")), "%" + criteria.getName().toUpperCase() + "%"));
        if (criteria.getCountry() != null)
            p.add(builder.like(builder.upper(root.get("country")), "%" + criteria.getCountry().toUpperCase() + "%"));
        if (criteria.getFrom() != null)
            p.add(builder.greaterThanOrEqualTo(root.get("dateFounded"), criteria.getFrom()));
        if (criteria.getTo() != null) p.add(builder.lessThanOrEqualTo(root.get("dateFounded"), criteria.getTo()));
        return builder.and(p.toArray(new Predicate[0]));
    }
}
