import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {

    private String encryption;
    private static final String letters = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "1234567890";
    private static final String special = "+%^&/*()?+£$!#@-";
    private boolean foundPassword = false;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private StringBuilder password = new StringBuilder();

    PasswordGenerator(String encryption) {
        this.encryption = encryption;
    }

    public String loopThroughPossibilities() {

        for (int i = 0; i < letters.length(); i++) {
            List<String> characterList = new ArrayList<>();
            characterList.add("0");
            characterList.add("0");
            characterList.add("0");

            characterList.set(0, Character.toString(letters.charAt(i)));
            for (int j = 0; j < numbers.length(); j++) {
                characterList.set(1, Character.toString(numbers.charAt(j)));
                for (int k = 0; k < special.length(); k++) {
                    characterList.set(2, Character.toString(special.charAt(k)));

                    for (String character : characterList) {
                        password.append(character);
                    }

                    permutation(password.toString());
                    if (foundPassword) {
                        return password.toString();
                    }
                    password.delete(0, 3);
                }
            }
        }
        return password.toString();
    }

    //Had to google how to do the below...
    public boolean permutation(String str) {
        return permutation("", str);
    }

    private boolean permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            password.delete(0, password.length());
            password.append(prefix);
            System.out.println(password);
            if (bCryptPasswordEncoder.matches(password,
                    encryption)) {
                System.out.println("Password is : " + password);
                foundPassword = true;
                return true;
            }
        } else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
        return false;
    }
}