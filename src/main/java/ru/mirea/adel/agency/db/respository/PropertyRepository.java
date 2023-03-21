package ru.mirea.adel.agency.db.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.adel.agency.db.model.Property;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    List<Property> findByOwnerUsernameAndPropertyTypeName(String ownerUsername, String propertyTypeName);
    List<Property> findByOwnerUsername(String ownerUsername);
    List<Property> findByPropertyTypeName(String propertyTypeName);
}
