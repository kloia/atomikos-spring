package com.kloia.atomikos.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {

    private Date timestamp;

    private String message;

    private String details;

}