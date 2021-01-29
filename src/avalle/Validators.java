package avalle;

public class Validators {


    public static Boolean isValidateName (String name){
        return name.matches("[A-Z][a-z]*");
    }
}
