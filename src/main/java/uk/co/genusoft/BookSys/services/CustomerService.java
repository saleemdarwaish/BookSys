package uk.co.genusoft.BookSys.services;

import java.util.Map;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import uk.co.genusoft.BookSys.data.models.Customer;
import uk.co.genusoft.BookSys.data.repositories.CustomerRepository;
import uk.co.genusoft.BookSys.dtos.CreateCustomerDto;

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

        Customer Customer = dtoMapper.toCustomer(request);
        return customerRepository.save(Customer);
    }

    public void updateCustomer(@NonNull final Long CustomerId, @NonNull final Map<String, Object> updatedFields) {
        constraintsValidator.validate(CreateCustomerDto.class, updatedFields);

        Customer Customer = getCustomer(CustomerId);
        CreateCustomerDto request = dtoMapper.toCreateCustomerDto(Customer);

        CreateCustomerDto updatedEntity = entityMapper.mergeFieldsWithEntity(CreateCustomerDto.class, request, updatedFields);

        Customer entity = dtoMapper.toCustomer(updatedEntity);
        entity.setId(CustomerId);

        customerRepository.save(entity);
    }

    public void deleteCustomer(@NonNull final Long CustomerId) {
        getCustomer(CustomerId);
        customerRepository.deleteById(CustomerId);
    }

}
