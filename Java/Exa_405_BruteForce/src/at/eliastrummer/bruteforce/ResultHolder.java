package at.eliastrummer.bruteforce;

public class ResultHolder {
    private final Person person;
    private final String password;
    private final long usedTime;

    public ResultHolder(Person person, String password, long usedTime) {
        this.person = person;
        this.password = password;
        this.usedTime = usedTime;
    }

    public Person getPerson() {
        return person;
    }

    public String getPassword() {
        return password;
    }

    public long getUsedTime() {
        return usedTime;
    }
}
