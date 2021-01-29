package avalle;

public class Student {

    private int ID;
    private String firstName, lastName;


    public int getID() {
        return ID;
    }

    // validate ID if it valid, it will store it. If not, it will throw and exception
    public void setID(int ID) {
        if(!Validators.isValidateID(ID)){
            throw new IllegalArgumentException("Enter a valid ID");
        }
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }
    // validate the value  if it valid, it will store it. If not, it will throw and exception
    public void setFirstName(String firstName) {
        if(!Validators.isValidateName(firstName)){
            throw new IllegalArgumentException("Enter a valid First Name");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // validate the value  if it valid, it will store it. If not, it will throw and exception
    public void setLastName(String lastName) {
        if(!Validators.isValidateName(lastName)){
            throw new IllegalArgumentException("Enter a valid Last Name");
        }
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}