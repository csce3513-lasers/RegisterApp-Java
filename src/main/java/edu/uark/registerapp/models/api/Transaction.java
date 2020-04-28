package edu.uark.registerapp.models.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;

@Entity
@Table(name="transaction")
public class Transaction extends ApiResponse{
    @Id
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final UUID id;

	public UUID getId() {
		return this.id;
	}

    @Column(name="cashierid")
    private UUID cashierId;

	public UUID getCashierId() {
		return this.cashierId;
	}

	public Transaction setCashierId(final UUID cashierId) {
		this.cashierId = cashierId;
		return this;
	}

	@Column(name="transactionid")
    private UUID transactionId;

	public UUID getTransactionId() {
		return this.transactionId;
	}

	public Transaction setTransactionId(final UUID transactionId) {
		this.transactionId = transactionId;
		return this;
	}

    @Column(name="total")
    private long total;

	public long getTotal() {
		return this.total;
	}

	public Transaction setTotal(final long total) {
		this.total = total;
		return this;
	}

	private int count;

	public int getCount() {
		return this.count;
	}

	public Transaction setCount(final int count) {
		this.count = count;
		return this;
	}

	
    @Column(name="price")
    private long price;

	public long getPrice() {
		return this.price;
	}

	public Transaction setPrice(final long price) {
		this.price = price;
		return this;
	}
	


	@Column(name = "transactiontype")
	private int type; // TODO: The idea is to map this to different types of transactions: Sale, Return, etc.

	public int getType() {
		return this.type;
	}

	public Transaction setType(final int type) {
		this.type = type;
		return this;
	}

    @Column(name="transactionreferenceid")
    private UUID referenceId;

	public UUID getReferenceId() {
		return this.referenceId;
	}

	public Transaction setReferenceId(final UUID referenceId) {
		this.referenceId = referenceId;
		return this;
	}

	@Column(name = "createdon", insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String createdOn;

	public String getCreatedOn() {
		return this.createdOn;
	}


	public Transaction setCreatedOn(final LocalDateTime createdOn) {
		this.createdOn =
			createdOn.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

		return this;
	}

	public Transaction() {
		super();

		this.type = -1;
		this.total = 0L;
		this.id = new UUID(0, 0);
		this.cashierId = new UUID(0, 0);
		this.referenceId = new UUID(0, 0);
	}

	public Transaction(
		final UUID cashierId,
		final long total,
		final int type,
		final UUID referenceId
	) {

		this.type = type;
		this.total = total;
		this.id = new UUID(0, 0);
		this.cashierId = cashierId;
		this.referenceId = referenceId;
	}

	public Transaction(final TransactionEntryEntity transactionEntryEntity) {
		super(false);

		this.id = transactionEntryEntity.getId();
		this.transactionId = transactionEntryEntity.getTransactionId();
		this.price=transactionEntryEntity.getPrice();


		this.setCreatedOn(transactionEntryEntity.getCreatedOn());
	}
}
