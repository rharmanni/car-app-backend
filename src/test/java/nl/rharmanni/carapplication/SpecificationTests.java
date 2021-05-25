package nl.rharmanni.carapplication;

import nl.rharmanni.carapplication.data.*;
import nl.rharmanni.carapplication.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SpecificationTests {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private TrimRepository trimRepository;

    private List<Brand> expectedBrands = new ArrayList<>();
    private List<Model> expectedModels = new ArrayList<>();
    private List<Trim> expectedTrims = new ArrayList<>();

    @Before
    public void init() {
        Brand bmw = new Brand();
        bmw.setName("BMW");
        bmw.setDateFounded(1916);
        bmw.setCountry("Germany");

        Model series3 = new Model();
        series3.setName("3 Series (G20)");
        series3.setGeneration(7);
        series3.setBrand(bmw);
        series3.setYearIntroduction(2018);

        Model series5 = new Model();
        series5.setName("5 Series (G30)");
        series5.setGeneration(7);
        series5.setBrand(bmw);
        series5.setYearIntroduction(2016);

        Model series7 = new Model();
        series7.setName("7 Series (G11)");
        series7.setGeneration(7);
        series7.setBrand(bmw);
        series7.setYearIntroduction(2015);

        Trim bmw518d = new Trim();
        bmw518d.setName("518d");
        bmw518d.setEngine("B47D20");
        bmw518d.setTransmission("Manual");
        bmw518d.setBodyStyle("Sedan");
        bmw518d.setYearBuilt(2019);
        bmw518d.setModel(series5);

        Trim bmw520d = new Trim();
        bmw520d.setName("520d");
        bmw520d.setEngine("B47D20");
        bmw520d.setTransmission("Manual");
        bmw520d.setBodyStyle("Sedan");
        bmw520d.setYearBuilt(2019);
        bmw520d.setModel(series5);

        Trim bmw530d = new Trim();
        bmw530d.setName("530d");
        bmw530d.setEngine("B47D30");
        bmw530d.setTransmission("Manual");
        bmw530d.setBodyStyle("Sedan");
        bmw530d.setYearBuilt(2019);
        bmw530d.setModel(series5);

        expectedTrims.add(bmw518d);
        expectedTrims.add(bmw520d);
        expectedTrims.add(bmw530d);
        series5.setTrims(expectedTrims);

        expectedModels.add(series3);
        expectedModels.add(series5);
        expectedModels.add(series7);
        bmw.setModels(expectedModels);

        expectedBrands.add(bmw);
    }

    @Test
    public void getBrandsByName() {
        BrandCriteria criteria = new BrandCriteria();
        criteria.setName("BMW");
        BrandSpecification specification = new BrandSpecification(criteria);
        List<Brand> brands = brandRepository.findAll(specification);
        Assert.assertEquals(brands, expectedBrands);
    }

    @Test
    public void getModelsByName() {
        ModelCriteria criteria = new ModelCriteria();
        criteria.setName("3 Series");
        ModelSpecification specification = new ModelSpecification(criteria);
        List<Model> models = modelRepository.findAll(specification);
        Assert.assertEquals(models, expectedModels);
    }

    @Test
    public void getTrimsByName() {
        TrimCriteria criteria = new TrimCriteria();
        criteria.setName("330d");
        TrimSpecification specification = new TrimSpecification(criteria);
        List<Trim> trims = trimRepository.findAll(specification);
        Assert.assertEquals(trims, expectedTrims);
    }

}
