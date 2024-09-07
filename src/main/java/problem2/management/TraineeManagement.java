package problem2.management;

import problem2.dao.TraineeDao;
import problem2.entities.Trainee;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class TraineeManagement {
    private static TraineeDao traineeDao;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            traineeDao = new TraineeDao();
        } catch (IOException e) {
            System.out.println("Error initializing TraineeDao: " + e.getMessage());
            return;
        }

        int choice;
        do {
            System.out.println("======== FA System ========");
            System.out.println("1. Create Trainee");
            System.out.println("2. Update Trainee");
            System.out.println("3. Report incompleted training");
            System.out.println("4. List excellent trainees");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            try {
                switch (choice) {
                    case 1:
                        saveTrainee();
                        break;
                    case 2:
                        updateTrainee();
                        break;
                    case 3:
                        System.out.println(TraineeDao.findIncompletedTrainee());
                        break;
                    case 4:
                        System.out.println(TraineeDao.findExcellentTrainee());
                        break;
                    case 5:
                        System.out.println("Exiting the program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (choice != 5);
    }
    private static void saveTrainee() throws IOException {
        System.out.println("----------Create Trainee ----------");
        System.out.print("Enter Trainee id: ");
        String id = scanner.nextLine();
        System.out.print("Enter Trainee account: ");
        String account = scanner.nextLine();
        System.out.print("Enter Full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Birth date: ");
        int birthDate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Gender (1 for male, 0 for female): ");
        int gender = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Status (active or in-active): ");
        String status = scanner.nextLine();
        System.out.print("Enter GPA: ");
        double gpa = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("----------------------------------------");

        Trainee newTrainee = new Trainee(id, account, fullName, birthDate, gender, gpa);

        newTrainee.setStatus(status);
        traineeDao.save(newTrainee);
        System.out.println("Trainee saved successfully!");
    }

    private static void updateTrainee() {
        System.out.println("Enter the ID of the trainee you wish to update:");
        String id = scanner.nextLine();

        try {
// Attempt to find the trainee with the given ID
            Trainee existingTrainee = traineeDao.findById(id);
            if (existingTrainee != null) {
// If the trainee is found, prompt the user for new details
                System.out.println("Found trainee: " + existingTrainee.getTraineeId());
                System.out.println("Enter new account (leave blank to keep current):");
                String account = scanner.nextLine();
                if (!account.isEmpty()) {
                    existingTrainee.setAccount(account);
                }

                System.out.println("Enter new full name (leave blank to keep current):");
                String fullName = scanner.nextLine();
                if (!fullName.isEmpty()) {
                    existingTrainee.setFullName(fullName);
                }

                System.out.println("Enter new birth date year (leave blank to keep current):");
                String birthDateStr = scanner.nextLine();
                if (!birthDateStr.isEmpty()) {
                    int birthDate = Integer.parseInt(birthDateStr);
                    existingTrainee.setBirthDate(birthDate);
                }

                System.out.println("Enter new gender (1 for male, 0 for female, leave blank to keep current):");
                String genderStr = scanner.nextLine();
                if (!genderStr.isEmpty()) {
                    int gender = Integer.parseInt(genderStr);
                    existingTrainee.setGender(gender);
                }

                System.out.println("Enter new status (active or in-active, leave blank to keep current):");
                String status = scanner.nextLine();
                if (!status.isEmpty()) {
                    existingTrainee.setStatus(status);
                }

                System.out.println("Enter new GPA (leave blank to keep current):");
                String gpaStr = scanner.nextLine();
                if (!gpaStr.isEmpty()) {
                    double gpa = Double.parseDouble(gpaStr);
                    existingTrainee.setGpa(gpa);
                }

// Update the trainee information in the Excel file
                traineeDao.update(id, existingTrainee);
                System.out.println("Trainee information updated successfully.");
            } else {
                System.out.println("No trainee found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for number fields. Please try again.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the trainee: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
