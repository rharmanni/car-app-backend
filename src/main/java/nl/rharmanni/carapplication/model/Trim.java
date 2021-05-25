package nl.rharmanni.carapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;

@Entity
public class Trim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Length(min = 1)
    @Length(max = 100)
    private String name;
    @Length(max = 100)
    private String engine;
    @Length(max = 100)
    private String transmission;
    @Length(max = 100)
    private String bodyStyle;
    @Min(1800)
    @Max(2100)
    private Integer yearBuilt;
    private String imageUrl;
    @ManyToOne
    @JsonIgnoreProperties("trims")
    private Model model;
//    @ManyToMany // TODO:
//    private List<Driver> drivers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    @Override
    public String toString() {
        return "Trim{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", engine='" + engine + '\'' +
                ", transmission='" + transmission + '\'' +
                ", bodyStyle='" + bodyStyle + '\'' +
                ", yearBuilt=" + yearBuilt +
                ", imageUrl='" + imageUrl + '\'' +
                ", model=" + model.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trim trim = (Trim) o;
        return Objects.equals(id, trim.id) &&
                Objects.equals(name, trim.name) &&
                Objects.equals(engine, trim.engine) &&
                Objects.equals(transmission, trim.transmission) &&
                Objects.equals(bodyStyle, trim.bodyStyle) &&
                Objects.equals(yearBuilt, trim.yearBuilt) &&
                Objects.equals(imageUrl, trim.imageUrl) &&
                Objects.equals(model, trim.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, engine, transmission, bodyStyle, yearBuilt, imageUrl, model);
    }
}
