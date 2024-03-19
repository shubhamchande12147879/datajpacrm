package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    
    @PostMapping("/save")
    public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer){
        return ResponseEntity.ok(customerServiceImpl.save(customer));
    }
    
    @GetMapping("/signin/{custEmailId}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmailId,@PathVariable String custPassword){
        return ResponseEntity.ok(customerServiceImpl.signin(custEmailId,custPassword));
    }
    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Optional<Customer>> findbycustId(@PathVariable int custId){
        return ResponseEntity.ok(customerServiceImpl.findBycustId(custId));
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerServiceImpl.findAll());
    }

    @PutMapping("/update/{custId}")
    public ResponseEntity<Customer> update(@PathVariable int custId,@Valid @RequestBody Customer customer){
        Customer customer1=customerServiceImpl.findBycustId(custId).orElseThrow(()->new RecordNotFoundException("customer id dosent exist"));
        customer1.setCustContact(customer.getCustContact());
        customer1.setCustName(customer.getCustName());
        customer1.setCustEmailId(customer.getCustEmailId());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustBalance(customer.getCustBalance());
        customer1.setCustPassword(customer.getCustPassword());

        return ResponseEntity.ok(customerServiceImpl.update(customer1));
    }

    @DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String> deletebyId(@PathVariable int custId){
        customerServiceImpl.deleteById(custId);
        return ResponseEntity.ok("Customer Data deleted sucessfully");
    }
}
