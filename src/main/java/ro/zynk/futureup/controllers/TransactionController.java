package ro.zynk.futureup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.zynk.futureup.controllers.responses.ErrorResponse;
import ro.zynk.futureup.exceptions.NotFoundException;
import ro.zynk.futureup.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /* Assignment 2 */
    @GetMapping(value = "")
    public Object getallTransactions() {
        try {
            return new ResponseEntity<>(transactionService.getallTransactions(), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /* Assignment 2 */
    @GetMapping(value = "/total_transaction_price_greater_than/ {amountOfUSD}")
    public Object getTransactions(@PathVariable("amountOfUSD") float amountOfUSD) {
        try {
            return new ResponseEntity<>(transactionService.getTransactions(amountOfUSD), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}