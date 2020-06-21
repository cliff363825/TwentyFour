package com.onevgo.jpa.helloworld;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "JPA_CUSTOMERS")
@Cacheable(true)
@NamedQuery(name = "testNamedQuery", query = "select c from Customer c where c.id=?1")
public class Customer {
    private Integer id;
    private String lastName;
    private String email;
    private int age;
    private Date createdTime;
    private Date birth;
    private Set<Order> orders = new HashSet<>();

    public Customer() {
    }

    public Customer(String lastName, int age) {
        this.lastName = lastName;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @TableGenerator(name = "ID_GENERATOR",
//            table = "jpa_id_generators",
//            pkColumnName = "PK_NAME",
//            pkColumnValue = "CUSTOMER_ID",
//            valueColumnName = "PK_VALUE",
//            allocationSize = 100
//    )
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Temporal(TemporalType.DATE)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * 映射单向 1-n 的关联关系
     * 使用 @OneToMany 来映射 1-n 的关联关系
     * 使用 @JoinColumn 来映射外键列的名称
     * 可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
     * 可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略
     * 注意：若在 1 的一端的 @OneToMany 中使用 mappedBy 属性，则 @OneToMany 端就不能在使用 @JoinColumn 属性了
     *
     * @return
     */
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE},
            mappedBy = "customer")
//    @JoinColumn(name = "CUSTOMER_ID")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    /**
     * 工具方法，不需要映射为数据表的一列
     *
     * @return
     */
    @Transient
    public String getInfo() {
        return "lastName: " + lastName + ", email: " + email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", createdTime=" + createdTime +
                ", birth=" + birth +
                '}';
    }
}
