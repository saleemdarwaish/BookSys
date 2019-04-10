package uk.co.genusoft.BookSys.factories;

import uk.co.genusoft.BookSys.data.models.Customer;

public abstract class CustomerFactory {

  public static Customer createCustomer(){
    return Customer.builder()

        .build();
  }


}
