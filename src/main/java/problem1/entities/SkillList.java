package problem1.entities;

import java.util.ArrayList;
import java.util.List;

public class SkillList {
    private List<String> skills;

    public SkillList() {
        skills = new ArrayList<>();
        skills.add("Java");
        skills.add(".Net");
        skills.add("Android");
        skills.add("NodeJS");
        skills.add("Angular");
        skills.add("AI");
    }

    public void display() {
        for (String skill : skills) {
            System.out.println(skill);
        }
    }

    public String getHead(int position) {
        if (position < 0 || position >= skills.size() || skills.isEmpty()) {
            return "NULL";
        }
        return skills.get(position - 1);
    }

    public boolean remove(String skill) {
        if (!skills.contains(skill)) {
            System.out.println("Error: The skill '" + skill + "' does not exist in the list.");
            return false;
        }
        return skills.remove(skill);
    }

    public void update(int index, String skillNew) {
        if (index >= 0 && index < skills.size()) {
            skills.set(index - 1, skillNew);
        }
    }
}