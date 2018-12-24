package com.spring.ws.service;

import com.spring.ws.entity.Address;
import com.spring.ws.entity.Customer;
import com.spring.ws.repository.AddressRepository;
import com.spring.ws.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestTransactionDAO {

  private CustomerRepository customerRepository;
  private AddressRepository addressRepository;

  @Autowired
  public TestTransactionDAO(
      CustomerRepository customerRepository,
      AddressRepository addressRepository) {
    this.customerRepository = customerRepository;
    this.addressRepository = addressRepository;
  }

  @Transactional(rollbackFor = Exception.class)
  public void addData(List<Customer> customerList, List<Address> addressList) {
    this.customerRepository.saveAll(customerList);
    this.addressRepository.saveAll(addressList);
  }

  @Transactional(rollbackFor = Exception.class)
  public void addDataException(List<Customer> customerList, List<Address> addressList)
      throws Exception {
    this.customerRepository.saveAll(customerList);
    this.addressRepository.saveAll(addressList);
    throw new Exception("test-transaction");
  }


  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
  public void addDataRequireNewTransaction(List<Customer> customerList, List<Address> addressList) {
    this.customerRepository.saveAll(customerList);
    this.addressRepository.saveAll(addressList);
  }

  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
  public void addDataExceptionRequireNewTransaction(List<Customer> customerList, List<Address> addressList)
      throws Exception {
    this.customerRepository.saveAll(customerList);
    this.addressRepository.saveAll(addressList);
    throw new Exception("test-transaction");
  }

  public List<Customer> getCustomerList() {
    return customerRepository.findAll();
  }

  public List<Address> getAddressList() {
    return addressRepository.findAll();
  }
}
