package nl.rharmanni.carapplication.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Length(min = 1, max = 100)
    @NotBlank
    private String name;
    @ManyToMany
    List<Trim> trims;

    public Driver() {
    }

    public Driver(Integer id, List<Trim> trims) {
        this.id = id;
        this.trims = trims;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Trim> getTrims() {
        return trims;
    }

    public void setTrims(List<Trim> trims) {
        this.trims = trims;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", trims=" + trims +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) &&
                Objects.equals(trims, driver.trims);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trims);
    }
}
