package com.onevgo.jpa.helloworld;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "JPA_ITEMS")
public class Item {
    private Integer id;
    private String itemName;

    private Set<Category> categories = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ITEM_NAME")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 使用 @ManyToMany 注解来映射多对多关联关系
     * 使用 @JoinTable 来映射中间表
     * <ul>
     * <li>1. name 指向中间表的名字</li>
     * <li>2. joinColumns 映射当前类所在的表在中间表中的外键</li>
     * <ul>
     * <li>2.1 name 指向外键列的列名</li>
     * <li>2.2 referencedColumnName 指定外键列关联当前表的哪一列</li>
     * </ul>
     * <li>3. inverseJoinColumns 映射关联的类所在中间表的外键</li>
     * </ul>
     *
     * @return
     */
    @ManyToMany
    @JoinTable(name = "ITEM_CATEGORY",
            joinColumns = {@JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")}
    )
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
