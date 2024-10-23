import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PrescriptionTest {

	// =================== Test cases for addPrescription() ===================

	// Test case 1: Valid and invalid first names for Prescription
	@Test
	public void testFirstNameValidity() {
		// Invalid first name (too short)
		Prescription invalidFirstName = new Prescription("Al", "Doe", "12 Hill Street, Perth, Australia", 1.25, -0.75,
				80, "15/07/23", "Dr. Rachel Lee");
		assertFalse(invalidFirstName.addPrescription(), "Expected invalid first name: Al");

		// Valid first name
		Prescription validFirstName = new Prescription("Alice", "Doem", "12 Hill Street, Perth, Australia", 1.25, -0.75,
				80, "15/07/23", "Dr. Rachel Lee");
		assertTrue(validFirstName.addPrescription(), "Expected valid first name: Alice");
	}

	// Test case 2: Valid and invalid last names for Prescription
	@Test
	public void testLastNameValidity() {
		// Invalid last name (too short)
		Prescription invalidLastName = new Prescription("James", "K", "456 Willow Street, Brisbane, Australia", 0.50,
				-2.00, 100, "20/08/23", "Dr. Michael Brown");
		assertFalse(invalidLastName.addPrescription(), "Expected invalid last name: K");

		// Valid last name
		Prescription validLastName = new Prescription("James", "Kimberly", "456 Willow Street, Brisbane, Australia",
				0.50, -2.00, 100, "20/08/23", "Dr. Michael Brown");
		assertTrue(validLastName.addPrescription(), "Expected valid last name: Kimberly");
	}

	// Test case 3: Valid and invalid addresses for Prescription
	@Test
	public void testAddressValidity() {
		// Invalid address (too short)
		Prescription invalidAddress = new Prescription("John", "Smith", "Short Address", 2.50, -3.00, 110, "22/09/23",
				"Dr. Sarah Miller");
		assertFalse(invalidAddress.addPrescription(), "Expected invalid address: Short Address");

		// Valid address
		Prescription validAddress = new Prescription("John", "Smith", "789 Long Avenue, Sydney, Australia", 2.50, -3.00,
				110, "22/09/23", "Dr. Sarah Miller");
		assertTrue(validAddress.addPrescription(), "Expected valid address: 789 Long Avenue");
	}

	// Test case 4: Valid and invalid sphere values for Prescription
	@Test
	public void testSphereValidity() {
		// Invalid sphere (out of range)
		Prescription invalidSphere = new Prescription("Emma", "Stone", "234 River Road, Melbourne, Australia", 25.00,
				-1.50, 90, "02/10/23", "Dr. Andrew Carter");
		assertFalse(invalidSphere.addPrescription(), "Expected invalid sphere: 25.00");

		// Valid sphere
		Prescription validSphere = new Prescription("Emma", "Stone", "234 River Road, Melbourne, Australia", -5.00,
				-1.50, 90, "02/10/23", "Dr. Andrew Carter");
		assertTrue(validSphere.addPrescription(), "Expected valid sphere: -5.00");
	}

	// Test case 5: Valid and invalid cylinder values for Prescription
	@Test
	public void testCylinderValidity() {
		// Invalid cylinder (out of range)
		Prescription invalidCylinder = new Prescription("Daniel", "Park", "567 Maple Lane, Adelaide, Australia", -4.50,
				5.00, 120, "08/11/23", "Dr. Jennifer Smith");
		assertFalse(invalidCylinder.addPrescription(), "Expected invalid cylinder: 5.00");

		// Valid cylinder
		Prescription validCylinder = new Prescription("Daniel", "Park", "567 Maple Lane, Adelaide, Australia", -4.00,
				2.00, 120, "08/11/23", "Dr. Jennifer Smith");
		assertTrue(validCylinder.addPrescription(), "Expected valid cylinder: 2.00");
	}

	// Test case 6: Valid and invalid axis values for Prescription
	@Test
	public void testAxisValidity() {
		// Invalid axis (out of range)
		Prescription invalidAxis = new Prescription("Sophia", "Johnson", "123 Elm Street, Canberra, Australia", -2.00,
				-1.50, 190, "05/12/23", "Dr. George White");
		assertFalse(invalidAxis.addPrescription(), "Expected invalid axis: 190");

		// Valid axis
		Prescription validAxis = new Prescription("Sophia", "Johnson", "123 Elm Street, Canberra, Australia", -2.00,
				-1.50, 90, "05/12/23", "Dr. George White");
		assertTrue(validAxis.addPrescription(), "Expected valid axis: 90");
	}

	// =================== Test cases for addRemark() ===================

	// Test case 1: Valid and invalid remark lengths for Remark
	@Test
	public void testRemarkLength() {
		// Invalid remark (too short)
		Prescription prescription = new Prescription("Olivia", "Williams", "901 Pine Street, Darwin, Australia", 1.75,
				-0.50, 85, "07/01/24", "Dr. Victoria Lewis");
		assertFalse(prescription.addRemark("Too short", "client"), "Expected invalid remark: Too short");

		// Valid remark
		assertTrue(prescription.addRemark("This is a valid client remark with ten words.", "client"),
				"Expected valid remark: This is a valid client remark.");
	}

	// Test case 2: Valid and invalid remark types for Remark
	@Test
	public void testRemarkType() {
		// Invalid remark type
		Prescription prescription = new Prescription("Henry", "Taylor", "678 Oak Street, Hobart, Australia", 0.00,
				-3.25, 100, "10/02/24", "Dr. William Anderson");
		assertFalse(prescription.addRemark("This is a valid remark with ten words.", "invalidType"),
				"Expected invalid remark type: invalidType");

		// Valid remark type
		assertTrue(prescription.addRemark("This is a valid remark with ten words.", "optometrist"),
				"Expected valid remark type: optometrist");
	}

	// Test case 3: Valid and invalid capitalization in Remark
	@Test
	public void testRemarkCapitalization() {
		// Invalid remark (not capitalized)
		Prescription prescription = new Prescription("Isabella", "Brown", "543 Birch Street, Geelong, Australia", 0.50,
				-1.75, 65, "15/03/24", "Dr. Steven Moore");
		assertFalse(prescription.addRemark("this remark starts with lowercase.", "client"),
				"Expected invalid remark: this remark starts with lowercase.");

		// Valid remark (capitalized)
		assertTrue(prescription.addRemark("This remark starts with uppercase.", "client"),
				"Expected valid remark: This remark starts with uppercase.");
	}

	// Test case 4: Valid and invalid number of remarks
	@Test
	public void testNumberOfRemarks() {
		Prescription prescription = new Prescription("Liam", "Garcia", "765 Cedar Street, Gold Coast, Australia", -1.00,
				-2.50, 55, "20/04/24", "Dr. Emily Thompson");

		// Add first valid remark
		assertTrue(prescription.addRemark("This is the first valid remark.", "client"), "Expected valid first remark.");

		// Add second valid remark
		assertTrue(prescription.addRemark("This is the second valid remark.", "client"),
				"Expected valid second remark.");

		// Add third remark (should fail since maximum 2 remarks allowed)
		assertFalse(prescription.addRemark("This is the third remark.", "client"), "Expected invalid third remark.");
	}

	// Test case 5: Valid and invalid date format in Remark
	@Test
	public void testDateInRemark() {
		Prescription prescription = new Prescription("Charlotte", "Lee", "876 Forest Avenue, Sydney, Australia", -1.75,
				-2.00, 70, "25/06/23", "Dr. Jack Thomas");

		// Invalid date format in the remark
		assertFalse(prescription.addRemark("The date was wrong: 2023/06/25.", "client"),
				"Expected invalid remark: wrong date format.");

		// Valid remark with date
		assertTrue(prescription.addRemark("The date was correct: 25/06/23.", "client"),
				"Expected valid remark with date.");
	}

	// Test case 6: Valid and invalid remarks with excessive words
	@Test
	public void testRemarkWordLimit() {
		Prescription prescription = new Prescription("Ethan", "Davies", "321 Palm Avenue, Melbourne, Australia", 2.00,
				-3.00, 100, "10/05/24", "Dr. Karen Wilson");

		// Invalid remark (too many words)
		assertFalse(prescription.addRemark(
				"This is an invalid remark because it has way too many words than the limit allows for a remark.",
				"optometrist"), "Expected invalid remark: too many words.");

		// Valid remark (within word limit)
		assertTrue(prescription.addRemark("This is a valid remark within the word limit.", "optometrist"),
				"Expected valid remark: within word limit.");
	}
}
