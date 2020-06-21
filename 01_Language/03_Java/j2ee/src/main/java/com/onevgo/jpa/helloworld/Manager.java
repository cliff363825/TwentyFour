package com.onevgo.jpa.helloworld;

import javax.persistence.*;

@Entity
@Table(name = "JPA_MANAGER")
public class Manager {
    private Integer id;
    private String mgrName;
    private Department dept;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "MGR_NAME")
    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    /**
     * 对于不维护关联关系，没有外键的一方，使用 @OneToOne 来进行映射，建议设置 mappedBy
     *
     * @return
     */
    @OneToOne(mappedBy = "mgr")
    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}
