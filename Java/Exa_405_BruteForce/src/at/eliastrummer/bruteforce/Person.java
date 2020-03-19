package at.eliastrummer.bruteforce;

public class Person {
    private String firstname;
    private String lastname;
    private String password;
    private String hash;

    public Person(String firstname, String lastname, String password, String hash) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.hash = hash;
    }
    
    public Person(String data){
        this(data.split(",")[0],
            data.split(",")[1],
            data.split(",")[2],
            data.split(",")[3]);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
