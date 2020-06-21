package com.onevgo.springboot.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * <code>@ConfigurationProperties</code> 告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
 * prefix="person" 配置文件中哪个下面的所有属性进行一一映射
 * <p>
 * 只有这个组件是容器中的组件，才能使用容器提供的 @ConfigurationProperties 功能
 * <code>@ConfigurationProperties(prefix = "person")</code> 默认从全局配置文件中获取值
 */
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {
    // @Value("${person.last-name}")
    @NotBlank(message = "名字不能为空")
    private String lastName;
    @Min(value = 0, message = "年龄不能是负数")
    @Max(value = 100, message = "年龄不能大于100")
    // @Value("#{11*2}")
    private Integer age;
    // @Value("true")
    private Boolean boss;
    @Past(message = "生日日期不正确")
    private Date birth;
    @Email(message = "邮箱格式不正确")
    private String email;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
