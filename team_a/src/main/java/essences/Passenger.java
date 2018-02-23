package essences;

public class Passenger {
    private int passengerId;
    private String name;
    private String lastName;
    private int age;
    private boolean sex;
    private int kmFlightScore;

    public Passenger(int passengerId, String name, String lastName, int age, boolean sex) {
        this.passengerId = passengerId;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.kmFlightScore = 0;
    }

    public Passenger(int passengerId, String name, String lastName, int age, int sex) {
        this.passengerId = passengerId;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        if (sex == 0) {this.sex = false;}
        else {this.sex = true;}
        this.kmFlightScore = 0;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", kmFlightScore=" + kmFlightScore +
                '}';
    }

    public Passenger(int passengerId, String name, String lastName, int age, int sex, int kmFlightScore) {
        this.passengerId = passengerId;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        if (sex == 0) {this.sex = false;}
        else {this.sex = true;}
        this.kmFlightScore = kmFlightScore;
    }

    public Passenger(int passengerId, String name, String lastName, int age, boolean sex, int kmFlightScore) {
        this.passengerId = passengerId;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.kmFlightScore = kmFlightScore;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        if (sex = true)
            return 1;
        else
            return 0;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getKmFlightScore() {
        return kmFlightScore;
    }

    public void setKmFlightScore(int kmFlightScore) {
        this.kmFlightScore = kmFlightScore;
    }

}
