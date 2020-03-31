package edu.uark.registerapp.commands.activeTransactions;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;

@Service
public class ActiveTransactionQuery implements ResultCommandInterface<Product> {
	@Override
	public Product execute() {
		final Optional<ProductEntity> productEntity =
			this.productRepository.findById(this.productId);
		if (productEntity.isPresent()) {
			return new Product(productEntity.get());
		} else {
			throw new NotFoundException("Product");
		}
	}

	// Properties
	private UUID productId;
	public UUID getProductId() {
		return this.productId;
	}
	public ActiveTransactionQuery setProductId(final UUID productId) {
		this.productId = productId;
		return this;
	}

	@Autowired
	private ProductRepository productRepository;
}
