package uk.co.genusoft.BookSys.services;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uk.co.genusoft.BookSys.data.models.Customer;
import uk.co.genusoft.BookSys.dtos.CreateCustomerDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DtoMapper {

  Customer toCustomer(final CreateCustomerDto createCustomerDto);

  CreateCustomerDto toCreateCustomerDto(final Customer customer);

}
