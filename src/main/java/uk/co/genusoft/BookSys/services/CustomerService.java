package uk.co.genusoft.BookSys.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import uk.co.genusoft.BookSys.data.models.Customer;
import uk.co.genusoft.BookSys.data.repositories.CustomerRepository;
import uk.co.genusoft.BookSys.dtos.CreateCustomerDto;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Map;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EntityMapper entityMapper;
    private final ConstraintsValidator constraintsValidator;
    private final DtoMapper dtoMapper;

    public Customer getCustomer(@NonNull final Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find resource by id: " + customerId));
    }

    public Customer createCustomer(@NonNull final CreateCustomerDto request) {
        constraintsValidator.validate(request);

        final String number = "";

        customerRepository.findByMobileOrTelephone(number)
                .ifPresent(u -> {
                    throw new EntityExistsException(String.format("Customer with mobile or telephone number %s already exists", number));
                });

        Customer Customer = dtoMapper.map(request, Customer.class);
        return customerRepository.save(Customer);
    }

    public void updateCustomer(@NonNull final Long CustomerId, @NonNull final Map<String, Object> updatedFields) {
        constraintsValidator.validate(CreateCustomerDto.class, updatedFields);

        Customer Customer = getCustomer(CustomerId);
        CreateCustomerDto request = dtoMapper.map(Customer, CreateCustomerDto.class);

        CreateCustomerDto updatedEntity = entityMapper.mergeFieldsWithEntity(CreateCustomerDto.class, request, updatedFields);

        Customer entity = dtoMapper.map(updatedEntity, Customer.class);
        entity.setId(CustomerId);

        customerRepository.save(entity);
    }

    public void deleteCustomer(@NonNull final Long CustomerId) {
        getCustomer(CustomerId);
        customerRepository.deleteById(CustomerId);
    }

}
