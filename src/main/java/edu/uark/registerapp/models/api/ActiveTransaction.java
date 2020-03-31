package edu.uark.registerapp.models.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.registerapp.models.entities.ActiveTransactionEntity;

public class ActiveTransaction extends ApiResponse {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public ActiveTransaction setId(final UUID id) {
		this.id = id;
		return this;
	}

	private String lookupCode;

	public String getLookupCode() {
		return this.lookupCode;
	}

	public ActiveTransaction setLookupCode(final String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}

	private int count;

	public int getCount() {
		return this.count;
	}

	public ActiveTransaction setCount(final int count) {
		this.count = count;
		return this;
	}

	private String createdOn;

	public String getCreatedOn() {
		return this.createdOn;
	}

	public ActiveTransaction setCreatedOn(final String createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public ActiveTransaction setCreatedOn(final LocalDateTime createdOn) {
		this.createdOn =
			createdOn.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

		return this;
	}

	public ActiveTransaction() {
		super();

		this.count = -1;
		this.id = new UUID(0, 0);
		this.lookupCode = StringUtils.EMPTY;

		this.setCreatedOn(LocalDateTime.now());
	}

	public ActiveTransaction(final ActiveTransactionEntity ActiveTransactionEntity) {
		super(false);

		this.id = ActiveTransactionEntity.getId();
		this.count = ActiveTransactionEntity.getCount();
		this.lookupCode = ActiveTransactionEntity.getLookupCode();

		this.setCreatedOn(ActiveTransactionEntity.getCreatedOn());
	}
}
