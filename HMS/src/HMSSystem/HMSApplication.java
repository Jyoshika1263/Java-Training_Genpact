package HMSSystem;

import java.security.Timestamp;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
 
public class HMSApplication {
 
    public static void main(String[] args) throws SQLException {
        PatientDAO patientDatabase = new PatientDAO();
        DoctorDAO doctorDatabase = new DoctorDAO();
        AppointmentDAO appointmentDatabase = new AppointmentDAO();
        StaffDAO staffDatabase = new StaffDAO();
        Scanner scanner = new Scanner(System.in);
 
        while (true) {
            System.out.println("Choose category:");
            System.out.println("1 - Manage Patient");
            System.out.println("2 - Manage Doctor");
            System.out.println("3 - Manage Appointment");
            System.out.println("4 - Manage Staff");
            System.out.println("5 - Exit");
 
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();
 
            switch (categoryChoice) {
                case 1:
                    managePatients(patientDatabase, scanner);
                    break;
                case 2:
                    manageDoctors(doctorDatabase, scanner);
                    break;
                case 3:
                	appointmentMenu(scanner);
                    break;
                case 4:
                	manageStaff(staffDatabase,scanner);
                    break;
                    
                case 5:
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
 
    private static void managePatients(PatientDAO patientDatabase, Scanner scanner) {
        while (true) {
            System.out.println("Enter your choice:");
            System.out.println("1 - Add Patient");
            System.out.println("2 - View All Patients");
            System.out.println("3 - Update Patient");
            System.out.println("4 - Delete Patient");
            System.out.println("5 - Back to Main Menu");
 
            int choice = scanner.nextInt();
            scanner.nextLine();
 
            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter patient's first name:");
                        String firstName = scanner.nextLine();
 
                        System.out.println("Enter patient's last name:");
                        String lastName = scanner.nextLine();
 
                        System.out.println("Enter patient's age:");
                        int age = scanner.nextInt();
                        scanner.nextLine();
 
                        System.out.println("Enter patient's gender:");
                        String gender = scanner.nextLine();
 
                        System.out.println("Enter patient's contact number:");
                        String contactNumber = scanner.nextLine();
 
                        Patient newPatient = new Patient(firstName, lastName, age, gender, contactNumber);
                        patientDatabase.addPatient(newPatient);
                        break;
 
                    case 2:
                        List<Patient> patients = patientDatabase.getAllPatients();
                        System.out.println("Patients in the database:");
                        for (Patient patient : patients) {
                            System.out.println("First Name: " + patient.getFirst_name() +
                                               ", Last Name: " + patient.getLast_name() +
                                               ", Age: " + patient.getAge() +
                                               ", Gender: " + patient.getGender() +
                                               ", Contact Number: " + patient.getContact_number());
                        }
                        break;
 
                    case 3:
                        System.out.println("Enter patient ID to update:");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
 
                        System.out.println("Enter patient's first name (if there is any change):");
                        String updateFirstName = scanner.nextLine();
 
                        System.out.println("Enter patient's new last name (if there is any change):");
                        String updateLastName = scanner.nextLine();
 
                        System.out.println("Enter patient's new age (if there is any change):");
                        int updateAge = scanner.nextInt();
                        scanner.nextLine();
 
                        System.out.println("Enter patient's gender:");
                        String updateGender = scanner.nextLine();
 
                        System.out.println("Enter patient's new contact number (if there is any change):");
                        String updateContactNumber = scanner.nextLine();
 
                        Patient updatedPatient = new Patient(updateFirstName, updateLastName, updateAge, updateGender, updateContactNumber);
                        patientDatabase.updatePatient(updateId, updatedPatient);
                        break;
 
                    case 4:
                        System.out.println("Enter patient ID to delete:");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();
 
                        patientDatabase.deletePatient(deleteId);
                        break;
 
                    case 5:
                        return;
 
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
 
    private static void manageDoctors(DoctorDAO doctorDatabase, Scanner scanner) {
        while (true) {
            System.out.println("Enter your choice:");
            System.out.println("1 - Add Doctor");
            System.out.println("2 - View All Doctors");
            System.out.println("3 - Update Doctor");
            System.out.println("4 - Delete Doctor");
            System.out.println("5 - Back to Main Menu");
 
            int choice = scanner.nextInt();
            scanner.nextLine();
 
            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter doctor's name:");
                        String name = scanner.nextLine();
 
                        System.out.println("Enter doctor's specialty:");
                        String speciality = scanner.nextLine();
 
                        System.out.println("Enter doctor's contact:");
                        String contact = scanner.nextLine();
 
                        Doctor newDoctor = new Doctor(name, speciality, contact);
                        doctorDatabase.addDoctor(newDoctor);
                        break;
 
                    case 2:
                        List<Doctor> doctors = doctorDatabase.getAllDoctors();
                        System.out.println("Doctors in the database:");
                        for (Doctor doctor : doctors) {
                            System.out.println("Name: " + doctor.getName() +
                                               ", Specialty: " + doctor.getSpeciality() +
                                               ", Contact: " + doctor.getContact());
                        }
                        break;
 
                    case 3:
                        System.out.println("Enter doctor ID to update:");
                        int updateDoctorId = scanner.nextInt();
                        scanner.nextLine();
 
                        System.out.println("Enter doctor's name (if there is any change):");
                        String updateName = scanner.nextLine();
 
                        System.out.println("Enter doctor's new specialty (if there is any change):");
                        String updateSpeciality = scanner.nextLine();
 
                        System.out.println("Enter doctor's new contact (if there is any change):");
                        String updateContact = scanner.nextLine();
 
                        Doctor updatedDoctor = new Doctor(updateName, updateSpeciality, updateContact);
                        doctorDatabase.updateDoctor(updateDoctorId, updatedDoctor);
                        break;
 
                    case 4:
                        System.out.println("Enter doctor ID to delete:");
                        int deleteDoctorId = scanner.nextInt();
                        scanner.nextLine();
 
                        doctorDatabase.deleteDoctor(deleteDoctorId);
                        break;
 
                    case 5:
                        return;
 
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void appointmentMenu(Scanner scanner) throws SQLException {
        AppointmentDAO appointmentDAO = new AppointmentDAO();
 
        while (true) {
            System.out.println("\nAppointment Management Menu:");
            System.out.println("1. Add Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Update Appointment Details");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
 
            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    String appointmentDate = scanner.next();
 
                    Appointment newAppointment = new Appointment(patientId, doctorId, appointmentDate);
                    appointmentDAO.addAppointment(newAppointment);
                    System.out.println("Appointment added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Appointment ID: ");
                    int appointmentId = scanner.nextInt();
                    Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
                    if (appointment != null) {
                        System.out.println(appointment);
                    } else {
                        System.out.println("No appointment found with the given ID.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Appointment ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter New Patient ID: ");
                    int newPatientId = scanner.nextInt();
                    System.out.print("Enter New Doctor ID: ");
                    int newDoctorId = scanner.nextInt();
                    System.out.print("Enter New Appointment Date (YYYY-MM-DD HH:MM:SS): ");
                    String newAppointmentDate = scanner.next();
 
                    Appointment updatedAppointment = new Appointment(updateId, newPatientId, newDoctorId, newAppointmentDate);
                    appointmentDAO.updateAppointment(updateId, updatedAppointment);
                    System.out.println("Appointment updated successfully!");
                    break;
                case 4:
                    System.out.print("Enter Appointment ID to delete: ");
                    int deleteId = scanner.nextInt();
                    appointmentDAO.deleteAppointment(deleteId);
                    System.out.println("Appointment deleted successfully!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void manageStaff(StaffDAO staffDatabase, Scanner scanner) {
        while (true) {
            System.out.println("Enter your choice:");
            System.out.println("1 - Add Staff");
            System.out.println("2 - View All Staff");
            System.out.println("3 - Update Staff");
            System.out.println("4 - Delete Staff");
            System.out.println("5 - Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter staff name:");
                        String name = scanner.nextLine();

                        System.out.println("Enter staff contact number:");
                        String contactNumber = scanner.nextLine();

                        Staff newStaff = new Staff(name, contactNumber);
                        staffDatabase.addStaff(newStaff);
                        break;

                    case 2:
                        List<Staff> staffList = staffDatabase.getAllStaff();
                        for (Staff staff : staffList) {
                            System.out.printf(
                                "ID: %d, Name: %s, Contact Number: %s%n",
                                staff.getId(),
                                staff.getName(),
                                staff.getContactNumber()
                            );
                        }
                        break;

                    case 3:
                        System.out.println("Enter staff ID to update:");
                        int updateStaffId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter staff name (if there is any change):");
                        String updateName = scanner.nextLine();

                        System.out.println("Enter staff new contact number (if there is any change):");
                        String updateContactNumber = scanner.nextLine();

                        Staff updatedStaff = new Staff(updateName, updateContactNumber);
                        staffDatabase.updateStaff(updateStaffId, updatedStaff);
                        break;

                    case 4:
                        System.out.println("Enter staff ID to delete:");
                        int deleteStaffId = scanner.nextInt();
                        scanner.nextLine();

                        staffDatabase.deleteStaff(deleteStaffId);
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
