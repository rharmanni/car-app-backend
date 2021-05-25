package nl.rharmanni.carapplication.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class TrimCriteria {

    private Integer modelId;
    private Integer brandId;
    private String name;
    private String engine;
    private String transmission;
    private String bodyStyle;
    private Integer yearBuilt;

    public TrimCriteria() {
    }

    public TrimCriteria(String name) {
        this.name = name;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
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
        return "TrimCriteria{" +
                "modelId=" + modelId +
                ", brandId=" + brandId +
                ", name='" + name + '\'' +
                ", engine='" + engine + '\'' +
                ", transmission='" + transmission + '\'' +
                ", bodyStyle='" + bodyStyle + '\'' +
                ", yearBuilt=" + yearBuilt +
                '}';
    }

}
