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
public class PropertyRequest {
    private String name;
    private BigDecimal area;
    private BigDecimal cadastralPrice;
    private GeographyDto geography;
    private String owner;
    private String propertyTypeName;
}
