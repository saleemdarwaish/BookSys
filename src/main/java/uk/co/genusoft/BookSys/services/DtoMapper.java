package uk.co.genusoft.BookSys.services;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Service;
import uk.co.genusoft.BookSys.data.models.Customer;
import uk.co.genusoft.BookSys.dtos.CreateCustomerDto;

@Service
public class DtoMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        registerCustomerClassMap(factory);
    }

    private void registerCustomerClassMap(final MapperFactory factory) {
        factory.classMap(Customer.class, CreateCustomerDto.class)
                .exclude("id")
                .byDefault()
                .register();
    }

}
