package nl.rharmanni.carapplication.data;

import nl.rharmanni.carapplication.model.Trim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface TrimRepository extends JpaRepository<Trim, Integer>, JpaSpecificationExecutor<Trim> {

    @Query
    void findByModel_BrandId(Integer brandId);

    @Query
    void findByModelId(Integer modelId);

    @Query
    void deleteByModel_BrandId(Integer brandId);

    @Query
    void deleteByModelId(Integer modelId);

//    @Query
//    List<Trim> findByModelNameAndModel_BrandName(String modelName, String brandName);
//
//    @Query
//    List<Trim> findByModel_BrandName(String brandName);
//
//    @Query
//    List<Trim> findByModelId(Integer modelId);
//
//    @Query
//    List<Trim> findByName(String trimName);

//    @Query("SELECT t from Trim t " +
//            "join t.model m " +
//            "join m.brand b where b.name = :brandName")
//    List<Trim> findByBrandName(String brandName);
//
//    @Query("SELECT t from Trim t " +
//            "join t.model m " +
//            "join m.brand b where b.name = :brandName and m.name = :modelName")
//    List<Trim> findByBrandAndModelName(String brandName, String modelName);
//
//    @Query("SELECT t from Trim t " +
//            "join t.model m " +
//            "join m.brand b where b.name = :brandName and m.name = :modelName and t.name = :trimName")
//    List<Trim> findByBrandAndModelAndTrimName(String brandName, String modelName, String trimName);


}