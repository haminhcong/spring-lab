package com.spring.ws.controller.v1;

import com.spring.ws.service.TestTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Slf4j
public class CustomerController {

  private TestTransactionService testTransactionService;

  @Autowired
  public CustomerController(
      TestTransactionService testTransactionService) {
    this.testTransactionService = testTransactionService;
  }


  @GetMapping(value = "/test-transaction-without-transaction-context")
  public String testTransaction() {
    log.info("Before test transaction");
    testTransactionService.printCurrentData();
    try {
      testTransactionService.testTransactionWithoutTransactionContext();
    } catch (Exception e) {
      log.error("Error ", e);
    }
    log.info("After test transaction");
    testTransactionService.printCurrentData();
    return "request-done";
  }

  @GetMapping(value = "/test-transaction-with-transaction-context")
  public String testTransaction2() {
    log.info("Before test transaction");
    testTransactionService.printCurrentData();
    try {
      testTransactionService.testTransactionWithTransactionContext();
    } catch (Exception e) {
      log.error("Error ", e);
    }
    log.info("After test transaction");
    testTransactionService.printCurrentData();
    return "request-done";
  }

  @GetMapping(value = "/test-transaction-with-transaction-require-new")
  public String testTransaction3() {
    log.info("Before test transaction");
    testTransactionService.printCurrentData();
    try {
      testTransactionService.testTransactionRequireNewTransaction();
    } catch (Exception e) {
      log.error("Error ", e);
    }
    log.info("After test transaction");
    testTransactionService.printCurrentData();
    return "request-done";
  }


  @GetMapping(value = "/check-data")
  public String checkData() {
    log.info("check-data");
    testTransactionService.printCurrentData();
    return "request-done";
  }


}