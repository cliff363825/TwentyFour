package com.onevgo.springdata;

import org.springframework.data.repository.Repository;

public interface AddressRepository extends Repository<Address, Integer> {

    Address findById(Integer id);

}
