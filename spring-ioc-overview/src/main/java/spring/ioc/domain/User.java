package spring.ioc.domain;

import org.springframework.core.io.Resource;

import java.util.List;

public class User {
    private Long id;

    private String name;

    private CityEnum cityEnum;


    private List<CityEnum> cities;

    private Resource resourceLocation;


    public CityEnum getCityEnum() {
        return cityEnum;
    }

    public void setCityEnum(CityEnum cityEnum) {
        this.cityEnum = cityEnum;
    }

    public List<CityEnum> getCities() {
        return cities;
    }

    public void setCities(List<CityEnum> cities) {
        this.cities = cities;
    }

    public Resource getResourceLocation() {
        return resourceLocation;
    }

    public void setResourceLocation(Resource resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cityEnum=" + cityEnum +
                ", cities=" + cities +
                ", resourceLocation=" + resourceLocation +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(2L);
        user.setName("超哥");
        return user;
    }
}
