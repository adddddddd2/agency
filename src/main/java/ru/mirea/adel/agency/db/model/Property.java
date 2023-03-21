package ru.mirea.adel.agency.db.model;

import jakarta.persistence.*;
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
public class Property {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private BigDecimal area;
    private BigDecimal cadastralPrice;
    private BigDecimal price;
    @OneToOne
    private Geography geography;
    @ManyToOne
    private User owner;
    @ManyToOne
    private PropertyType propertyType;
}
