package edu.uark.registerapp.commands.transactions;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.api.Transaction;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;

@Service
public class TransactionEntryQuery implements ResultCommandInterface<Transaction> {
	@Override
	public Transaction execute() {
		final Optional<TransactionEntryEntity> transactionEntryEntity =
			this.transactionEntryRepository.findById(this.transactionId);
		if (transactionEntryEntity.isPresent()) {
		}
			throw new NotFoundException("Product");
		}
	

	// Properties
	private UUID transactionId;
	public UUID getTransactionId() {
		return this.transactionId;
	}
	public TransactionEntryQuery setTransactionId(final UUID transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	@Autowired
    private TransactionEntryRepository transactionEntryRepository;
}
