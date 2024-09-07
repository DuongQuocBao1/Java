package problem1.management;

import problem1.entities.SkillList;
import java.util.Scanner;

public class SkillManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SkillList skillList = new SkillList();
        int option;
        do {
            System.out.println("1. Display skill list");
            System.out.println("2. Get skill by position");
            System.out.println("3. Delete skill by value");
            System.out.println("4. Update skill by index");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    skillList.display();
                    break;
                case 2:
                    System.out.print("Enter position: ");
                    int position = scanner.nextInt();
                    System.out.println("Skill: " + skillList.getHead(position));
                    break;
                case 3:
                    System.out.print("Enter skill to remove: ");
                    String skillToRemove = scanner.next();
                    System.out.println("Successfully removed: " + skillList.remove(skillToRemove));
                    break;
                case 4:
                    System.out.print("Enter index to update: ");
                    int index = scanner.nextInt();
                    System.out.print("Enter new skill: ");
                    String newSkill = scanner.next();
                    skillList.update(index, newSkill);
                    break;
                case 5:
                    System.out.println("Close program");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 5);
        scanner.close();
    }
}