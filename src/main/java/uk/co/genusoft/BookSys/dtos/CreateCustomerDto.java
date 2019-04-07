package uk.co.genusoft.BookSys.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerDto {

    private String firstName;

    private String lastName;

    private String mobile;

    private String telephone;

}
