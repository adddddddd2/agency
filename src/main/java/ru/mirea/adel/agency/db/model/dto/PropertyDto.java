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
public class PropertyDto {
    private Integer id;
    private String name;
    private BigDecimal area;
    private BigDecimal cadastralPrice;
    private BigDecimal price;
    private GeographyDto geography;
    private UserDto owner;
    private String propertyTypeName;
}
