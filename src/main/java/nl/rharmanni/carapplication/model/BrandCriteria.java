package nl.rharmanni.carapplication.model;

public class BrandCriteria {
    private String name;
    private String country;
    private Integer from;
    private Integer to;

    public BrandCriteria() {
    }

    public BrandCriteria(String name) {
        this.name = name;
    }

    public BrandCriteria(String name, String country, Integer from, Integer to) {
        this.name = name;
        this.country = country;
        this.from = from;
        this.to = to;
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

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}
