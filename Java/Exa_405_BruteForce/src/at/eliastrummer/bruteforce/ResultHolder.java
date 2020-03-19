package at.eliastrummer.bruteforce;

public class ResultHolder {
    private final Person person;
    private final String password;

    public ResultHolder(Person person, String password) {
        this.person = person;
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public String getPassword() {
        return password;
    }
}
