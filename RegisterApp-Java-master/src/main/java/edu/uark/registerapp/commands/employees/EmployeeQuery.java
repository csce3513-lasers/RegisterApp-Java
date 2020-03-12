package edu.uark.registerapp.commands.employees;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class EmployeeQuery implements ResultCommandInterface<Employee> {
	@Override
	public Employee execute() {
		final Optional<EmployeeEntity> employeeEntity =
			this.employeeRepository.findById(this.employeeRecordId);
		if (employeeEntity.isPresent()) {
			return new Employee(employeeEntity.get());
		} else {
			throw new NotFoundException("Employee ID  Not Found ");
		}
	}

	// Properties
	private UUID employeeRecordId;
	public UUID getEmployeeRecordId() {
		return this.employeeRecordId;
	}
	public EmployeeQuery setEmployeeRecordId(final UUID employeeRecordId) {
		this.employeeRecordId = employeeRecordId;
		return this;
	}

	@Autowired
	private EmployeeRepository employeeRepository;
}
