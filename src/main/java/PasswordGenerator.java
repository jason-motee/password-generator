import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator {

    private List<String> letters = new ArrayList<>();
    private List<String> digits = new ArrayList<>();
    private List<String> special = new ArrayList<>();
    private int passwordLength;
    private int numberOfDigits;
    private int numberOfSpecial;
    private StringBuilder password = new StringBuilder();

    public PasswordGenerator(int passwordLength, int numberOfDigits, int numberOfSpecial) {
        this.passwordLength = passwordLength;
        this.numberOfDigits = numberOfDigits;
        this.numberOfSpecial = numberOfSpecial;
        generateLetters();
        generateNumbers();
        generateSpecial();
        generatePassword();
    }

    public void generatePassword() {
        List<String> password = new ArrayList<>();
        addDigitsToPassword(password, numberOfDigits, digits);
        addSpecialToPassword(password, numberOfSpecial, special);
        addLettersToPassword(password);

        Collections.shuffle(password);
        for (String character:password) {
            this.password.append(character);
        }
    }

    private void addDigitsToPassword(List<String> password, int numberOfDigits, List<String> digits) {
        addSpecialToPassword(password, numberOfDigits, digits);
    }

    private void addSpecialToPassword(List<String> password, int numberOfSpecial, List<String> special) {
        for (int i = 0; i < numberOfSpecial; i++) {
            Collections.shuffle(special);
            password.add(special.get(0));
        }
    }

    private void addLettersToPassword(List<String> password) {
        while(password.size() < passwordLength) {
            Collections.shuffle(letters);
            password.add(letters.get(0));
        }
    }

    private void generateLetters() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            letters.add(Character.toString(letter));
            letters.add(Character.toString(letter).toUpperCase());
        }
    }

    private void generateNumbers() {
        for (int number = 0; number <= 9; number++) {
            digits.add(String.valueOf(number));
        }
    }

    private void generateSpecial() {
        String specialString = "!@#$%&*()_+-=[]|,./?><";
        String[] specialArray = specialString.split("");
        special.addAll(Arrays.asList(specialArray));
    }

    public StringBuilder getPassword() {
        return password;
    }

    public List<String> getLetters() {
        return letters;
    }

    public List<String> getDigits() {
        return digits;
    }

    public List<String> getSpecial() {
        return special;
    }
}
