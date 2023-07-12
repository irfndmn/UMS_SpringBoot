package com.dmn.repository;
import com.dmn.domain.Customer;
import com.dmn.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository  // opsiyonel
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Boolean existsByEmail(String email);

    List<Customer> findAllBylastname(String lastname);

    List<Customer> findAllBygrade(Integer grade);


//
//    //JPQL
//    @Query("FROM Customer c WHERE c.grade=:pGrade")
//    List<Customer> getAllCustomerByQuery(@Param("pGrade") Integer grade);
//    //JpaRepository<Entity Class DT,ID nin data type>


    //SQL  Custom native query

    @Query(value = "SELECT * FROM customer c WHERE c.grade=:pGrade", nativeQuery = true)
    List<Customer> getAllCustomerByQuery(@Param("pGrade") Integer grade);

    //JpaRepository<Entity Class DT,ID nin data type>



    @Query("SELECT new com.dmn.dto.CustomerDTO(c) FROM Customer c WHERE c.id=:pId")
    Optional<CustomerDTO> findByIdCustomerDTOWithJPQL(@Param("pId") Long id);



}
