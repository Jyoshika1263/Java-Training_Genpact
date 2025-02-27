package HMSSystem;

public class Doctor {
	private String name;
	private String specialty;
	private String contact;
	public Doctor(String name, String specialty, String contact) {
		super();
		this.name = name;
		this.specialty = specialty;
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpeciality() {
		return specialty;
	}
	public void setSpeciality(String specialty) {
		this.specialty = specialty;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
    
}
