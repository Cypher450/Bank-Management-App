package bank.app.entities;

public class Contact {

	private String full_name;
	private String email;
	private String message;

	public Contact() {
		
	}

	public Contact(String full_name, String email, String message) {
		super();
		this.full_name = full_name;
		this.email = email;
		this.message = message;
	}

	

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Contact [full_name=" + full_name + ", email=" + email + ", message=" + message + "]";
	}

}
