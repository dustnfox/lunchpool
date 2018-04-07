package ru.javawebinar.lunchpool.model;

public class Restaurant extends AbstractNamedEntity {

    private String address;

    private String description;

    public Restaurant(String name, String address) {
        this(null, name, address, null);
    }

    public Restaurant(String name, String address, String description) {
        this(null, name, address, description);
    }

    private Restaurant(Integer id, String name, String address, String description) {
        super(id, name);
        this.address = address;
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
