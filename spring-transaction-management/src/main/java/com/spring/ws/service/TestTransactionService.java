package com.spring.ws.service;

import com.spring.ws.entity.Address;
import com.spring.ws.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TestTransactionService {

  private TestTransactionDAO testTransactionDAO;
  public TestTransactionService(
      TestTransactionDAO testTransactionDAO
  ){
    this.testTransactionDAO =  testTransactionDAO;
  }

  public void testTransactionWithoutTransactionContext(){
    log.info("test-transaction");

    List<Customer> initCustomerList =
        createCustomerList("customer init 1", "customer init 2");
    List<Address> initAddressList =
        createAddressList("address init 1", "address init 2");

    testTransactionDAO.addData(initCustomerList, initAddressList);

    try {
      List<Customer> testCustomerList =
          createCustomerList("test customer 1", "test customer 2");
      List<Address> testAddressList =
          createAddressList("test address 1", "test address 2");
      testTransactionDAO.addDataException(testCustomerList, testAddressList);
    } catch (Exception e) {
      log.error("Error", e);
    }

    List<Customer> testCustomerList =
        createCustomerList("test customer 3", "test customer 4");
    List<Address> testAddressList =
        createAddressList("test address 3", "test address 4");
    testTransactionDAO.addData(testCustomerList, testAddressList);
  }


  @Transactional(rollbackFor = Exception.class)
  public void testTransactionWithTransactionContext(){
    log.info("test-transaction");

    List<Customer> initCustomerList =
        createCustomerList("customer init 1", "customer init 2");
    List<Address> initAddressList =
        createAddressList("address init 1", "address init 2");

    testTransactionDAO.addData(initCustomerList, initAddressList);

    try {
      List<Customer> testCustomerList =
          createCustomerList("test customer 1", "test customer 2");
      List<Address> testAddressList =
          createAddressList("test address 1", "test address 2");
      testTransactionDAO.addDataException(testCustomerList, testAddressList);
    } catch (Exception e) {
      log.error("Error", e);
    }

    List<Customer> testCustomerList =
        createCustomerList("test customer 3", "test customer 4");
    List<Address> testAddressList =
        createAddressList("test address 3", "test address 4");
    testTransactionDAO.addData(testCustomerList, testAddressList);
  }


  @Transactional(rollbackFor = Exception.class)
  public void testTransactionRequireNewTransaction(){
    log.info("test-transaction");

    List<Customer> initCustomerList =
        createCustomerList("customer init 1", "customer init 2");
    List<Address> initAddressList =
        createAddressList("address init 1", "address init 2");

    testTransactionDAO.addDataRequireNewTransaction(initCustomerList, initAddressList);

    try {
      List<Customer> testCustomerList =
          createCustomerList("test customer 1", "test customer 2");
      List<Address> testAddressList =
          createAddressList("test address 1", "test address 2");
      testTransactionDAO.addDataExceptionRequireNewTransaction(testCustomerList, testAddressList);
    } catch (Exception e) {
      log.error("Error", e);
    }

    List<Customer> testCustomerList =
        createCustomerList("test customer 3", "test customer 4");
    List<Address> testAddressList =
        createAddressList("test address 3", "test address 4");
    testTransactionDAO.addDataRequireNewTransaction(testCustomerList, testAddressList);
  }



  public void printCurrentData() {
    List<Customer> customerList = testTransactionDAO.getCustomerList();
    List<Address> addressList = testTransactionDAO.getAddressList();

    log.info("Customer List: ");
    for (Customer customer : customerList) {
      log.info("{} - {}", customer.getId(), customer.getAccountName());
    }

    log.info("Address List: ");
    for (Address address : addressList) {
      log.info("{} - {}", address.getId(), address.getAddress());
    }
  }

  public List<Customer> createCustomerList(String... customerNameList) {
    List<Customer> customerList = new ArrayList<>();

    for (String customerName : customerNameList) {
      Customer customer = new Customer();
      customer.setAccountName(customerName);
      customerList.add(customer);
    }
    return customerList;
  }

  public List<Address> createAddressList(String... addressNameList) {
    List<Address> addressList = new ArrayList<>();

    for (String addressName : addressNameList) {
      Address address = new Address();
      address.setAddress(addressName);
      addressList.add(address);
    }
    return addressList;
  }
}
