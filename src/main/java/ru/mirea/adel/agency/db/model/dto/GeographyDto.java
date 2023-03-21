package ru.mirea.adel.agency.db.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeographyDto {
    private BigDecimal lan;
    private BigDecimal lon;
    private String country;
    private String city;
    private String street;
    private String buildingNum;
    private String zipCode;
}
