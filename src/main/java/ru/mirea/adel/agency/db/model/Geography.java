package ru.mirea.adel.agency.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Geography {
    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal lan;
    private BigDecimal lon;
    private String country;
    private String city;
    private String street;
    private String buildingNum;
    private String zipCode;
}
