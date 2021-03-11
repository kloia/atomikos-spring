package com.kloia.atomikos.customerservice.dto;

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
public class CustomerAddressAndAccountCoreUpdateRequestDto implements Serializable {

    private Integer id;

    private String zipCode;

    private Integer accountCoreId;
}
