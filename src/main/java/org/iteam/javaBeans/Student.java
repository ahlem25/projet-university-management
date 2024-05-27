package main.java.org.iteam.javaBeans;

public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String cin;
	private String level;
	
	public Student() {
		super();
	}

	public Student(int id, String firstName, String lastName, String email, String cin, String level) {
		this(firstName, lastName, email, cin, level);
		this.id = id;
	}

    public Student(String firstName, String lastName, String email, String cin, String level) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.cin = cin;
		this.level = level;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
	
}
