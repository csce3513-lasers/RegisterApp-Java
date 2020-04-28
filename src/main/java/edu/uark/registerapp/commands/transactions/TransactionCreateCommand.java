package edu.uark.registerapp.commands.transactions;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.api.Transaction;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.entities.TransactionEntity;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;
import edu.uark.registerapp.models.repositories.TransactionRepository;

@Service
public class TransactionCreateCommand implements ResultCommandInterface<Transaction> {
	@Override
	public Transaction execute() {

		final TransactionEntryEntity createdTransactionEntity = this.createTransactionEntry();
		this.apiTransaction.setTransactionId(createdTransactionEntity.getTransactionId()); 
		this.apiTransaction.setCreatedOn(createdTransactionEntity.getCreatedOn());
		
		return this.apiTransaction;
	}

	


	// Helper methods
	@Transactional
	private TransactionEntryEntity createTransactionEntry() {

		final List<TransactionEntryEntity> queriedTransactionEntryEntity =
			this.transactionEntryRepository
				.findByTransactionId(this.apiTransaction.getTransactionId());

		for (TransactionEntryEntity transactionEntryEntity : queriedTransactionEntryEntity) {
			transactionEntryEntity.setTransactionId(transactionEntryEntity.getId());
		}
		return this.transactionEntryRepository.save(
				new TransactionEntryEntity(apiTransaction));
	}

	// Properties
	private UUID employeeId;
	private Transaction apiTransaction;

	public Transaction getApiTransaction() {
		return this.apiTransaction;
	}
	public TransactionCreateCommand setApiTransaction(final Transaction apiTransaction) {
		this.apiTransaction = apiTransaction;
		return this;
	}
	public UUID getEmployeeId() {
		return this.employeeId;
	}
	public TransactionCreateCommand setEmployeeId(final UUID employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private TransactionEntryRepository transactionEntryRepository;
}
