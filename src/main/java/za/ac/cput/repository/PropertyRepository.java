package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {

}
