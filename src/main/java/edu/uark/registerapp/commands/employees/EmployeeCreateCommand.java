package edu.uark.registerapp.commands.employees;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.ConflictException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class EmployeeCreateCommand implements ResultCommandInterface<Employee> {
	@Override
	public Employee execute() {
		this.validateProperties();

		final EmployeeEntity createdEmployeeEntity = this.createEmployeeEntity();

		// Synchronize information generated by the database upon INSERT.
		this.apiEmployee.setId(createdEmployeeEntity.getId()); 
		this.apiEmployee.setCreatedOn(createdEmployeeEntity.getCreatedOn());

		return this.apiEmployee;
	}

	// Helper methods
	private void validateProperties() {
		if (StringUtils.isBlank(this.apiEmployee.getId())) {
			throw new UnprocessableEntityException("Id");
		}
	}
	public void validateEmployee(){
		final Employee employee = this.getApiEmployee();

		if (employee.getFirstName().isEmpty()) {
			throw new UnprocessableEntityException("First Name cannot be blank");
		}

		if (employee.getLastName().isEmpty()) {
			throw new UnprocessableEntityException("Last Name cannot be blank");
		}

		if (employee.getPassword().isEmpty()) {
			throw new UnprocessableEntityException("Password cannot be blank");
		}
	}

	public void setDefaultsForEmployee() {
		final Employee employee = this.getApiEmployee();

		if (this.IsInitialEmployee()) {
			// general manager
		}
	}



	@Transactional
	private EmployeeEntity createEmployeeEntity() {
		final Optional<EmployeeEntity> queriedEmployeeEntity = this.EmployeeRepository
				.findById(this.apiEmployee.getId());

		if (queriedEmployeeEntity.isPresent()) {
			// Lookupcode already defined for another Employee.
			throw new ConflictException("Id");
		}

		// No ENTITY object was returned from the database, thus the API object's
		// lookupcode must be unique.

		// Write, via an INSERT, the new record to the database.
		return this.EmployeeRepository.save(new EmployeeEntity(apiEmployee));
	}

	// Properties
	private Employee apiEmployee;
	private boolean setIsInitialEmployeeFlag;
	private boolean IsInitialEmployeeFlag;

	public Employee getApiEmployee() {
		return this.apiEmployee;
	}

	public EmployeeCreateCommand setApiEmployee(final Employee apiEmployee) {
		this.apiEmployee = apiEmployee;
		return this;
	}

	public void setIsInitialEmployee(final boolean IsInitialEmployee) {
		this.IsInitialEmployeeFlag = IsInitialEmployee;
	}
	public boolean IsInitialEmployee(){
		return this.IsInitialEmployeeFlag;
	}

	@Autowired
	private EmployeeRepository EmployeeRepository;
}