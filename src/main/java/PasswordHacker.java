import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class PasswordHacker {

    private String encryption;
    private int passwordLength;
    private static final String letters = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "1234567890";
    private static final String special = "+%^&/*()?+£$!#@-";
    private boolean foundPassword = false;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private StringBuilder password = new StringBuilder();
    private List<String> characterList = new ArrayList<>();

    PasswordHacker(String encryption, int passwordLength) {
        this.encryption = encryption;
        this.passwordLength = passwordLength;
    }

    public String loopThroughPossibilities() {
        setCharacterListSize();
        for (int i = 0; i < letters.length(); i++) {
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
                    password.delete(0, passwordLength);
                }
            }
        }
        return password.toString();
    }

    private void setCharacterListSize() {
        for (int j = 0; j < passwordLength; j++) {
            characterList.add("0");
        }
    }

    //Had to google how to do the below...
    public void permutation(String characterCombination) {
        permutation("", characterCombination);
    }

    private void permutation(String prefix, String characterCombination) {
        int characterCombinationLength = characterCombination.length();
        if (characterCombinationLength == 0) {
            password.delete(0, password.length());
            password.append(prefix);
            System.out.println(password);

            if (bCryptPasswordEncoder.matches(password, encryption)) {
                foundPassword = true;
            }
        }
        else {
            for (int i = 0; i < characterCombinationLength; i++)
                permutation(prefix + characterCombination.charAt(i),
                        characterCombination.substring(0, i) + characterCombination.substring(i + 1, characterCombinationLength));
        }
    }
}