package edu.uark.registerapp.models.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import edu.uark.registerapp.models.entities.ActiveTransactionEntity;


public interface ActiveTransactionRepository extends CrudRepository<ActiveTransactionEntity, UUID> {
	Optional<ActiveTransactionEntity> findById(UUID id);
	Optional<ActiveTransactionEntity> findByLookupCode(String lookupCode);
}
