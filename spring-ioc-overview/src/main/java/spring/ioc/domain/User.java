package spring.ioc.domain;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

public class User implements BeanNameAware {
    private Long id;

    private String name;

    private CityEnum cityEnum;

    private Company company;


    private List<CityEnum> cities;

    private Resource resourceLocation;

    private transient String beanName;

    public String getBeanName() {
        return beanName;
    }

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cityEnum=" + cityEnum +
                ", company=" + company +
                ", cities=" + cities +
                ", resourceLocation=" + resourceLocation +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(2L);
        user.setName("超哥");
        return user;
    }

    @PostConstruct
    public void init() {
        System.out.printf("当前Bean name [%s] 初始化中.....\n",this.beanName);
    }

    @PreDestroy
    public void destory() {
        System.out.printf("当前Bean name:[%s] 已销毁.....\n",this.beanName);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;

    }
}
