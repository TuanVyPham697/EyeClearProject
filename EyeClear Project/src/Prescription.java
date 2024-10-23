import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
	private String firstName;
	private String lastName;
	private String address;
	private double sphere;
	private double cylinder;
	private int axis;
	private String examinationDate;
	private String optometrist;
	private ArrayList<String> postRemarks = new ArrayList<>();
	private String[] remarkTypes = { "client", "optometrist" }; // Declare remarkTypes

	// Constructor (updated to accept the correct parameters)
	public Prescription(String firstName, String lastName, String address, double sphere, double cylinder, int axis,
			String examinationDate, String optometrist) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.sphere = sphere;
		this.cylinder = cylinder;
		this.axis = axis;
		this.examinationDate = examinationDate;
		this.optometrist = optometrist;
	}

	// Method to add a prescription to a TXT file
	public boolean addPrescription() {
		// Check condition: First Name and Last Name length
		if (firstName.length() < 4 || firstName.length() > 15) {
			System.out.println("Invalid first name: " + firstName);
			return false;
		}
		if (lastName.length() < 4 || lastName.length() > 15) {
			System.out.println("Invalid last name: " + lastName);
			return false;
		}

		// Check condition: Address length
		if (address.length() < 20) {
			System.out.println("Invalid address: " + address);
			return false;
		}

		// Check condition: Sphere, Cylinder, and Axis ranges
		if (sphere < -20.00 || sphere > 20.00) {
			System.out.println("Invalid sphere value: " + sphere);
			return false;
		}
		if (cylinder < -4.00 || cylinder > 4.00) {
			System.out.println("Invalid cylinder value: " + cylinder);
			return false;
		}
		if (axis < 0 || axis > 180) {
			System.out.println("Invalid axis value: " + axis);
			return false;
		}

		// Check condition: Date format (DD/MM/YY)
		if (!isValidDate(examinationDate)) {
			System.out.println("Invalid examination date: " + examinationDate);
			return false;
		}

		// Check condition: Optometrist name length
		if (optometrist.length() < 8 || optometrist.length() > 25) {
			System.out.println("Invalid optometrist name: " + optometrist);
			return false;
		}

		// All conditions met, write to file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("presc.txt", true))) {
			writer.write(firstName + " " + lastName + ", " + address + ", Sphere: " + sphere + ", Cylinder: " + cylinder
					+ ", Axis: " + axis + ", Exam Date: " + examinationDate + ", Optometrist: " + optometrist);
			writer.newLine();
			System.out.println("Valid prescription added: " + firstName + " " + lastName);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	// Method to add a remark to a TXT file
	public boolean addRemark(String remark, String type) {
		// Check condition: Remark length (6-20 words) and first letter of first word is
		// uppercase
		String[] words = remark.split(" ");
		if (words.length < 6 || words.length > 20 || !Character.isUpperCase(remark.charAt(0))) {
			System.out.println("Invalid remark: " + remark);
			return false;
		}

		// Check condition: Remark type and number of remarks (must be "client" or
		// "optometrist" and <= 2 remarks)
		if (!type.equals(remarkTypes[0]) && !type.equals(remarkTypes[1])) {
			System.out.println("Invalid remark type: " + type);
			return false;
		}
		if (postRemarks.size() >= 2) {
			System.out.println("No more than 2 remarks allowed");
			return false;
		}

		// All conditions met, add remark to file and list
		postRemarks.add(remark);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("remark.txt", true))) {
			writer.write(type + ": " + remark);
			writer.newLine();
			System.out.println("Valid remark added: " + remark);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	// Helper method to check valid date format
	private boolean isValidDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		sdf.setLenient(false);
		try {
			Date parsedDate = sdf.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static void main(String[] args) {

		Prescription prescription1 = new Prescription("John", "Smith", "123 Long Avenue, Sydney, Australia", 1.50,
				-1.00, 90, "12/03/23", "Dr. Williams");
		Prescription prescription2 = new Prescription("Jane", "Doe", "456 Park Street, Melbourne, Australia", -2.00,
				-0.50, 120, "15/04/23", "Dr. Emily Carter");

		prescription1.addPrescription();
		prescription2.addPrescription();

		prescription1.addRemark("This is a valid client remark with ten words.", "client");
		prescription1.addRemark("Another valid optometrist remark here.", "optometrist");
	}
}
