package uk.co.genusoft.BookSys.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.co.genusoft.BookSys.data.models.Customer;
import uk.co.genusoft.BookSys.factories.CustomerFactory;
import uk.co.genusoft.BookSys.services.CustomerService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  private final Long customerId = 1L;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService customerService;
  private String baseUrl = "/v1/customer";

  @Test
  void getCustomer_shouldReturnACustomerById() throws Exception {
    // GIVEN
    final Customer customer = CustomerFactory.createCustomer();

    given(customerService.getCustomer(customerId)).willReturn(customer);

    // WHEN THEN
    mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + customerId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("id").value(customer.getId()));
  }

  @Test
  void getUser_shouldReturn404_whenUserIsNotFound() throws Exception {
    // GIVEN
    given(customerService.getCustomer(customerId)).willThrow(new EntityNotFoundException());

    // WHEN THEN
    mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + customerId))
        .andExpect(status().isNotFound());
  }

  @Test
  void postCustomer_shouldCreateCustomer() {

  }

  @Test
  void postCustomer_shouldReturn400_WhenInvalidRequestIsProvided() {

  }

  @Test
  void postCustomer_shouldReturn409_WhenCustomerWithNumberAlreadyExists() {

  }

  @Test
  void patchCustomer_shouldUpdateTheFieldsPassedInThePayload() {

  }

  @Test
  void deleteCustomer_shouldDeleteTheUser() {

  }

}