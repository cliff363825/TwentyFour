package com.onevgo.springboot.repository.yii2;

import com.onevgo.springboot.entity.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TbUserRepository extends JpaSpecificationExecutor<TbUser>, JpaRepository<TbUser, Integer> {
}
