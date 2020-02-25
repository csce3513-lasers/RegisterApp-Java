package edu.uark.registerapp.models.api;
import java.lang.String;


public class Employee extends ApiResponse
{
	private String employeeid=" ";
	private String firstName=" ";
	private String lastName= " ";
	private String password=" ";
	private boolean isActive=false;
	private int classification=0; 
	private Sting createdOn=" ";
	private boolean isInitaitalEmployee= true;
	private UUId id;
	private UUID managerId;
	
	
	
	public void setId(UUId id)
	{
		this.id=id;
	}
	
	public void setManagerId(UUID id)
	{
		managerId=id;
	}
	public void setEmployeeId(String id)
	{
		emplyeeid=id;
	}
	
	public void setFirstName(String name)
	{
		firstName=name;
	}
	public void setLastName(String name)
	{
		lastName=name;
	}public void setPassword(String password)
	{
		this.password=password;
	}
	public void setIsActive(boolean active)
	{
		isActive=active;
	}
	public void changeInitailStatus()
	{
		isInitaitalEmployee=false;
	}
	
	public void setClassification(int classifacation)
	{
		this.classification=classifacation;
	}
	
	
	
	public String getEmployeeId()
	{
		return emplyeeid;
	}
	
	public void getFirstName()
	{
		return firstName;
	}
	public string getLastName()
	{
		return lastName;
	}
	public string getPassword(String password)
	{
		return password;
	}
	public boolean getIsActive()
	{
		return isActive;
	}
	public boolean getInitailStatus()
	{
		return isInitaitalEmployee;
	}
	
	public int getClassification()
	{
		return this.classification;
	}
	
	public UUID setId(UUId id)
	{
		return this.id;
	}
	
	public UUID setManagerId(UUID id)
	{
		return managerId;
	}
	
	

}