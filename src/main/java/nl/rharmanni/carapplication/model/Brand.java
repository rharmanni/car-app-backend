package nl.rharmanni.carapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Length(min = 1, max = 100)
    @NotBlank
    private String name;
    @Length(max = 100)
    private String country;
    @Min(1800)
    @Max(2100)
    private Integer dateFounded;
    private String imageUrl;
    @OneToMany(mappedBy = "brand", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnoreProperties({"trims", "brand"})
    private List<Model> models;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getDateFounded() {
        return dateFounded;
    }

    public void setDateFounded(Integer dateFounded) {
        this.dateFounded = dateFounded;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", dateFounded=" + dateFounded +
                ", imageUrl='" + imageUrl + '\'' +
                ", models=" + models +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id) &&
                Objects.equals(name, brand.name) &&
                Objects.equals(country, brand.country) &&
                Objects.equals(dateFounded, brand.dateFounded) &&
                Objects.equals(imageUrl, brand.imageUrl) &&
                Objects.equals(models, brand.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, dateFounded, imageUrl, models);
    }
}
