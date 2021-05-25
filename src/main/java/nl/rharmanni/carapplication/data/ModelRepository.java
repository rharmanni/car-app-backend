package nl.rharmanni.carapplication.data;

import nl.rharmanni.carapplication.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface ModelRepository extends JpaRepository<Model, Integer>, JpaSpecificationExecutor<Model> {

    @Query
    void findByBrandId(Integer brandId);

    @Query
    void deleteByBrandId(Integer brandId);

}
