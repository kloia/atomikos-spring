package com.kloia.atomikos.customerservice.model.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CUSTOMER", schema = "C##CUSTOMER")
@SequenceGenerator(name = "customer_id_seq", schema = "C##CUSTOMER", sequenceName = "customer_id_seq", allocationSize = 1)
public class Customer {

    @Id
    @GeneratedValue(generator = "customer_id_seq")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer age;

    @Column(name = "account_id")
    private Long accountId;

}
