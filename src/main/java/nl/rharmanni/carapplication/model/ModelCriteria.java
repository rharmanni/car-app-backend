package nl.rharmanni.carapplication.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class ModelCriteria {

    private Integer brandId;
    private String name;
    private Integer generation;

    public ModelCriteria(String name) {
        this.name = name;
    }

    public ModelCriteria() {
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
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

    @Override
    public String toString() {
        return "ModelCriteria{" +
                "brandId=" + brandId +
                ", name='" + name + '\'' +
                ", generation=" + generation +
                '}';
    }
}
