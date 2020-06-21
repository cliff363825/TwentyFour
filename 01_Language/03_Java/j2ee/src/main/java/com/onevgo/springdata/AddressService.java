package com.onevgo.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public Address findAddressById(Integer id) {
        return addressRepository.findById(id);
    }
}
