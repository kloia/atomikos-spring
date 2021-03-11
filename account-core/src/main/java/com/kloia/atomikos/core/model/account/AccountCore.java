package com.kloia.atomikos.core.model.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ACCOUNT_CORE", schema = "C##ACCOUNT")
@SequenceGenerator(name = "account_core_id_seq", schema = "C##ACCOUNT", sequenceName = "account_core_id_seq", allocationSize = 1)
public class AccountCore {

    @Id
    @GeneratedValue(generator = "account_core_id_seq")
    private Integer id;

    @NotNull
    private Integer coreCode;

    @NotNull
    private BigDecimal coreBalance;

}
