package org.example.dao;

import org.example.models.Address;
import org.example.models.Group;

public interface AddressDao extends CrudDao<Address>{
    Address save(Address address);
}
