package uk.co.genusoft.BookSys.data.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uk.co.genusoft.BookSys.data.models.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where c.mobile = :number or c.telephone = :number")
    Optional<Customer> findByMobileOrTelephone(@Param("number") @NonNull final String number);
}
