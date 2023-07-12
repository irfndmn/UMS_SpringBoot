package com.dmn.controller;

import com.dmn.domain.Customer;
import com.dmn.dto.CustomerDTO;
import com.dmn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("/customers")// http://localhost:8080/customers
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    @GetMapping("/greet")  //http://localhost:8080/customers/great
    private String great() {

        return "Hi Every Body... I'm doing Spring Boot App";

    }




    // http://localhost:8080/customers +GET

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customers=customerService.getAllCustomer();
        //return new ResponseEntity<>(customers, HttpStatus.OK );
        return ResponseEntity.ok(customers);

    }


    @PostMapping
    public ResponseEntity<Map<String,String>> createCustomer(@Valid @RequestBody Customer customer){

        customerService.saveCustomer(customer);

        Map<String,String> response=new HashMap<>();
        response.put("message","Customer has added successfully...");
        response.put("status","Successfull");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }





    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer1(@PathVariable("id") Long id ){       /// GetCustomerById with using @PathVariable("id") annotation

        Customer customer=customerService.getCustomerById(id);

        return ResponseEntity.ok(customer);

    }



    @GetMapping("/query")
    public ResponseEntity<Customer> getCustomer(@RequestParam("id") Long id ){

        Customer customer=customerService.getCustomerById(id);

        return ResponseEntity.ok(customer);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deletedCustomer(@PathVariable("id") Long id){

        customerService.deleteById(id);
        Map<String,String> response=new HashMap<>();
        response.put("message","Customer has deleted successfully...");
        response.put("status","Successfull");
        return new ResponseEntity<>(response,HttpStatus.OK);

    }




    // belirli bir id ile customeri update etme(sadece name , lastname , grade, email, phonenumber )

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateCustom(@PathVariable("id") Long id,
                                                           @Valid @RequestBody CustomerDTO customerDTO ){

        customerService.updateCustomerById(id,customerDTO);
        Map<String,String> response=new HashMap<>();
        response.put("message","Customer has updated successfully...");
        response.put("status","Successfull");
        return new ResponseEntity<>(response,HttpStatus.OK);

    }




    //pagiantion (sayfalandirma)
//
//    @GetMapping("/page")
//    public ResponseEntity<Page<Customer>> getCustomerByPage(@RequestParam("page") int page,   //hangi page gosterilsin
//                                                                  @RequestParam("size") int size,  // her bir page te kac kayit bulunsun
//                                                                  @RequestParam("sort") String prop, // kayitlar hangi filde gore
//                                                                  @RequestParam("direction")Sort.Direction direction){  //yon
//
//        Pageable pageable= PageRequest.of(page,size,Sort.by(direction,prop));
//        Page<Customer> allCus=customerService.getAllCustmByPaging(pageable);
//
//        return new ResponseEntity<>(allCus,HttpStatus.OK);
//
//    }



    //pagiantion (sayfalandirma)

    @GetMapping("/page")
    public ResponseEntity<Page<Customer>> getCustomerByPage(@RequestParam(value="page", required = false,defaultValue = "0") int page,   //bu sekilde zorunluluk kaldirip default deger atamamizi sagliyo
                                                            @RequestParam(value="size", required = false, defaultValue = "2") int size,  // her bir page te kac kayit bulunsun
                                                            @RequestParam("sort") String prop, // kayitlar hangi fielde gore
                                                            @RequestParam("direction")Sort.Direction direction){  //yon

        Pageable pageable= PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Customer> allCus=customerService.getAllCustmByPaging(pageable);

        return new ResponseEntity<>(allCus,HttpStatus.OK);

    }



    //lastname ile customerlari getirme

    @GetMapping("/lastnamequery")
    public ResponseEntity<List<Customer>> getCusByLastName(@RequestParam("lastname")String lastname){

        List<Customer> customer=customerService.getCustomerBylasName(lastname);

        return new ResponseEntity<>(customer,HttpStatus.OK);

    }

    @GetMapping("/gradeq/{grade}")
    public ResponseEntity<List<Customer>> getAllCustByGrade(@PathVariable("grade") Integer grade){


        List<Customer> customerList=customerService.getAllCustByGrade(grade);

        return ResponseEntity.ok(customerList);

    }



    @GetMapping("/gradequery/{grade}")
    public ResponseEntity<List<Customer>> getAllCustByGradeQuery(@PathVariable("grade") Integer grade){


        //List<Customer> customerList=customerService.getAllCustByGrade(grade);

        List<Customer> customerList=customerService.getAllCustByGradeWithQuery(grade);

        return ResponseEntity.ok(customerList);

    }







    // id si verilen bir customer i customerDTO olarak dondurelim

    @GetMapping("/bydto/{id}")
    public ResponseEntity<CustomerDTO> getCustDTObyId(@PathVariable("id") Long id){



        CustomerDTO customerDTO=customerService.getCustomerDTObyId(id);

       return new ResponseEntity<>(customerDTO,HttpStatus.OK);



    }

















}
