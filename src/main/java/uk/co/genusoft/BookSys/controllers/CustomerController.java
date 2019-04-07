package uk.co.genusoft.BookSys.controllers;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uk.co.genusoft.BookSys.data.models.Customer;
import uk.co.genusoft.BookSys.dtos.CreateCustomerDto;
import uk.co.genusoft.BookSys.services.CustomerService;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Customer getCustomer(@PathVariable final Long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> postCustomer(@RequestBody final CreateCustomerDto customerRequest) {
        Customer customer = customerService.createCustomer(customerRequest);
        URI uri = ServletUriComponentsBuilder.fromPath("/v1/customer/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(customer);
    }

    @ApiImplicitParams({@ApiImplicitParam(dataType = "CustomerRequest", name = "updatedFields", paramType = "body", required = true)})
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchCustomer(@PathVariable final Long id, @RequestBody final Map<String, Object> updatedFields) {
        customerService.updateCustomer(id, updatedFields);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable final Long id) {
        this.customerService.deleteCustomer(id);
    }


}
