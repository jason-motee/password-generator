import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator {


    private List<String> letters = new ArrayList<>();
    private List<String> digits = new ArrayList<>();
    private List<String> special = new ArrayList<>();
    private String specialString = "!@#$%&*()_+-=[]|,./?><";
    private int passwordLength;
    private int numberOfDigits;
    private int numberOfSpecial;
    private StringBuilder password = new StringBuilder();


    public List<String> getLetters() {
        return letters;
    }

    public List<String> getDigits() {
        return digits;
    }

    public List<String> getSpecial() {
        return special;
    }

    public PasswordGenerator(int passwordLength, int numberOfDigits, int numberOfSpecial) {
        generateLetters();
        generateNumbers();
        generateSpecial();
        this.passwordLength = passwordLength;
        this.numberOfDigits = numberOfDigits;
        this.numberOfSpecial = numberOfSpecial;
        generatePassword();
    }

    public void generatePassword() {
        List<String> password = new ArrayList<>();

        for (int i = 0; i < numberOfDigits; i++) {
            Collections.shuffle(digits);
            password.add(digits.get(0));
        }

        for (int i = 0; i < numberOfSpecial; i++) {
            Collections.shuffle(special);
            password.add(special.get(0));
        }

        while(password.size() < passwordLength) {
            Collections.shuffle(letters);
            password.add(letters.get(0));

        }

        Collections.shuffle(password);

        for (String character:password) {
            this.password.append(character);
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
        String[] specialArray = specialString.split("");
        special.addAll(Arrays.asList(specialArray));
    }

    public StringBuilder getPassword() {
        return password;
    }
}
