package edu.uark.registerapp.commands.employees;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class EmployeeUpdateCommand implements ResultCommandInterface<Employee> {
	@Transactional
	@Override
	public Employee execute() {
		this.validateProperties();

		final Optional<EmployeeEntity> EmployeeEntity =
			this.EmployeeRepository.findById(this.EmployeeId);
		if (!EmployeeEntity.isPresent()) { // No record with the associated record ID exists in the database.
			throw new NotFoundException("Employee");
		}

		// Synchronize any incoming changes for UPDATE to the database.
		this.apiEmployee = emoployeeEntity.get().synchronize(this.apiEmployee);

		// Write, via an UPDATE, any changes to the database.
		this.employeeRepository.save(employeeEntity.get());

		return this.apiEmployee;
	}

	// Helper methods
	private void validateProperties() {
		if (StringUtils.isBlank(this.apiEmployee.getLookupCode())) {
			throw new UnprocessableEntityException("lookupcode");
		}
	}
	public void validateEmployee(){
		Employee employee = this.getApiEmployee();

		if(employee.getFirstName().isEmpty()){
			throw new UnprocessableEntityException("First Name cannot be blank");
		}

		if(employee.getLastName().isEmpty()){
			throw new UnprocessableEntityException("Last Name cannot be blank");
		}
	}

	// Properties
	private UUID EmployeeId;
	public UUID getEmployeeId() {
		return this.employeeId;
	}
	public EmployeeUpdateCommand setEmployeeId(final UUID employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	private Employee apiEmployee;
	public Employee getApiEmployee() {
		return this.apiEmployee;
	}
	public EmployeeUpdateCommand setApiEmployee(final Employee apiEmployee) {
		this.apiEmployee = apiEmployee;
		return this;
	}
	
	@Autowired
	private EmployeeRepository EmployeeRepository;
}
