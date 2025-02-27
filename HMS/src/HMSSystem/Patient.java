package HMSSystem;

	public class Patient {
		private String first_name;
		private String last_name;
		private int age;
		private String gender;
		private String contact_number;
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getContact_number() {
			return contact_number;
		}
		public void setContact_number(String contact_number) {
			this.contact_number = contact_number;
		}
		public Patient(String first_name, String last_name, int age, String gender, String contact_number) {
			super();
			this.first_name = first_name;
			this.last_name = last_name;
			this.age = age;
			this.gender = gender;
			this.contact_number = contact_number;
		}


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//	//adding all columns as instance mebers
//	private int id;
//	private String first_name;
//	private String last_name;
//	private int age;
//	private String gender;
//	private String contact_number;
//	public Patient(String first_name, String last_name, int age, String gender, String contact_number) {
//		super();
//		this.id = id;
//		this.first_name = first_name;
//		this.last_name = last_name;
//		this.age = age;
//		this.gender = gender;
//		this.contact_number = contact_number;
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getFname() {
//		return first_name;
//	}
//	public void setFname(String first_name) {
//		this.first_name = first_name;
//	}
//	public String getLname() {
//		return last_name;
//	}
//	public void setLname(String last_name) {
//		this.last_name = last_name;
//	}
//	public int getAge() {
//		return age;
//	}
//	public void setAge(int age) {
//		this.age = age;
//	}
//	public String getGender() {
//		return gender;
//	}
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//	public String getContactNumber() {
//		return contact_number;
//	}
//	public void setContactNumber(String contact_number) {
//		this.contact_number = contact_number;
//	}
//	
//
//}
