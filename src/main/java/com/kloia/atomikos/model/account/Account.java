package com.kloia.atomikos.model.account;

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
@Table(name = "account", schema = "account")
@SequenceGenerator(name = "account_id_seq", schema = "account", sequenceName = "account_id_seq", allocationSize = 1)
public class Account {

    @Id
    @GeneratedValue(generator = "account_id_seq")
    private Integer id;

    @NotNull
    private Integer code;

    @NotNull
    private BigDecimal balance;

}
