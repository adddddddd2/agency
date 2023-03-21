package ru.mirea.adel.agency.db.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.adel.agency.db.model.PropertyType;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, String> {
}
