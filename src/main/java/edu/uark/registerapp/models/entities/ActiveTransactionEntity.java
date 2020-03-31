package edu.uark.registerapp.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import edu.uark.registerapp.models.api.ActiveTransaction;

@Entity
@Table(name="activetransaction")
public class ActiveTransactionEntity {
    @Id
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final UUID id;

	public UUID getId() {
		return this.id;
	}

	@Column(name = "lookupcode")
	private String lookupCode;

	public String getLookupCode() {
		return this.lookupCode;
	}

	public ActiveTransactionEntity setLookupCode(final String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}

	@Column(name = "count")
	private int count;

	public int getCount() {
		return this.count;
	}

	public ActiveTransactionEntity setCount(final int count) {
		this.count = count;
		return this;
	}

	@Column(name = "createdon", insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private LocalDateTime createdOn;

	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}

	public ActiveTransaction synchronize(final ActiveTransaction apiActiveTransaction) {
		this.setCount(apiActiveTransaction.getCount());
		this.setLookupCode(apiActiveTransaction.getLookupCode());

		apiActiveTransaction.setId(this.getId());
		apiActiveTransaction.setCreatedOn(this.getCreatedOn());

		return apiActiveTransaction;
	}

	public ActiveTransactionEntity() {
		this.count = -1;
		this.id = new UUID(0, 0);
		this.lookupCode = StringUtils.EMPTY;
	}

	public ActiveTransactionEntity(final String lookupCode, final int count) {
		this.count = count;
		this.id = new UUID(0, 0);
		this.lookupCode = lookupCode;
	}

	public ActiveTransactionEntity(final ActiveTransaction apiActiveTransaction) {
    	this.id = new UUID(0, 0);
		this.count = apiActiveTransaction.getCount();
		this.lookupCode = apiActiveTransaction.getLookupCode();
	}
}
