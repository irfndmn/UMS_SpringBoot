package com.dmn.repository;
import com.dmn.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // opsiyonel
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Boolean existsByEmail(String email);

    List<Customer> findAllBylastname(String lastname);

    List<Customer> findAllBygrade(Integer grade);
    //JpaRepository<Entity Class DT,ID nin data type>
}
