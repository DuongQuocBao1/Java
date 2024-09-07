package problem2.entities;

public class Trainee {
    private String trainee_id;
    private String account;
    private String full_name;
    private int birth_date;
    private Integer gender;
    private String status;
    private double gpa;

    public Trainee() {
        this.status = "active";
        this.gender = null;
    }

    public Trainee(String trainee_id, String account, String full_name, int birth_date, Integer gender, double gpa) {
        this.trainee_id = trainee_id;
        this.account = account;
        this.full_name = full_name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.status = "active";
        this.gpa = gpa;
    }

    public static void text(){}

    public String getTraineeId() {
        return trainee_id;
    }

    public void setTraineeId(String trainee_id) {
        this.trainee_id = trainee_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public int getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(int birth_date) {
        this.birth_date = birth_date;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "trainee_id='" + trainee_id + '\'' +
                ", account='" + account + '\'' +
                ", full_name='" + full_name + '\'' +
                ", birth_date=" + birth_date +
                ", gender=" + (gender == 1 ? "male" : "female") +
                ", status='" + status + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}