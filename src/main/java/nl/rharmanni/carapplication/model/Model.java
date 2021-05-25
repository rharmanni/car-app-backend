package nl.rharmanni.carapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Length(min = 1, max = 100)
    private String name;
    @Min(0)
    @Max(1000)
    private Integer generation;
    @Min(value = 1800)
    @Max(value = 2100)
    private Integer yearIntroduction;
    @Min(value = 1800)
    @Max(value = 2100)
    private Integer yearDiscontinued;
    private String imageUrl;
    @ManyToOne
    @JsonIgnoreProperties("models")
    private Brand brand;
    @OneToMany(mappedBy = "model", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnoreProperties({"model"})
    private List<Trim> trims;

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

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public Integer getYearIntroduction() {
        return yearIntroduction;
    }

    public void setYearIntroduction(Integer yearIntroduction) {
        this.yearIntroduction = yearIntroduction;
    }

    public Integer getYearDiscontinued() {
        return yearDiscontinued;
    }

    public void setYearDiscontinued(Integer yearDiscontinued) {
        this.yearDiscontinued = yearDiscontinued;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Trim> getTrims() {
        return trims;
    }

    public void setTrims(List<Trim> trims) {
        this.trims = trims;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", generation=" + generation +
                ", yearIntroduction=" + yearIntroduction +
                ", yearDiscontinued=" + yearDiscontinued +
                ", imageUrl='" + imageUrl + '\'' +
                ", brand=" + brand.getName() +
                ", trims=" + trims +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(name, model.name) &&
                Objects.equals(generation, model.generation) &&
                Objects.equals(yearIntroduction, model.yearIntroduction) &&
                Objects.equals(yearDiscontinued, model.yearDiscontinued) &&
                Objects.equals(imageUrl, model.imageUrl) &&
                Objects.equals(brand, model.brand) &&
                Objects.equals(trims, model.trims);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, generation, yearIntroduction, yearDiscontinued, imageUrl, brand, trims);
    }
}
