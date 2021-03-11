package com.kloia.atomikos.customerservice.model.customeraddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressId implements Serializable {

    @NotNull
    private Integer id;

    @NotNull
    @Column(name = "ZIP_CODE")
    private String zipCode;

}
