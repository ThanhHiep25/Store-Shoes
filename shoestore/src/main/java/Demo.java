import com.example.shoestore.config.PasswordUtil;

public class Demo {

	public static void main(String[] args) {
	    String password = "1";
	    String hashedPassword = null;
		try {
			hashedPassword = PasswordUtil.hashPassword(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    System.out.println("Hashed Password: " + hashedPassword);

	    boolean isPasswordValid = false;
		try {
			isPasswordValid = PasswordUtil.checkPassword(password, hashedPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Password Valid: " + isPasswordValid);
	}


}
