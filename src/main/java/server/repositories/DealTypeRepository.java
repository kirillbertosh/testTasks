package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.DealType;

import java.util.Optional;

@Repository
public interface DealTypeRepository extends JpaRepository<DealType, Long> {

    Optional<DealType> findByName(String dealName);

    Optional<DealType> findById(Long dealTypeId);
}
