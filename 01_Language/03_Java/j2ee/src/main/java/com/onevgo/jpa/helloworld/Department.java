package com.onevgo.jpa.helloworld;

import javax.persistence.*;

@Entity
@Table(name = "JPA_DEPARTMENT")
public class Department {
    private Integer id;
    private String deptName;

    private Manager mgr;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "DEPT_NAME")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 使用 @OneToOne 来映射 1-1 关联关系
     * 若需要在当前数据表中添加主键则需要使用 @JoinColumn 来进行映射。注意，1-1 关联关系，所以需要添加 unique=true
     *
     * @return
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MGR_ID", unique = true)
    public Manager getMgr() {
        return mgr;
    }

    public void setMgr(Manager mgr) {
        this.mgr = mgr;
    }
}
