package com.kloia.atomikos.core.model.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(name = "CORE_CODE")
    private Integer coreCode;

    @NotNull
    @Column(name = "CORE_BALANCE")
    private BigDecimal coreBalance;

}
