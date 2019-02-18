/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordGeneratorTest {

    PasswordGenerator passwordGenerator = new PasswordGenerator("$2a$10$XEaF37eD5O4aLMtD30s1ouJencvNY3hPb1T5cPYzhmwwIYfLRwq0u", 3);

    @Test
    public void crackPassword() {
        assertThat(passwordGenerator.loopThroughPossibilities()).isEqualTo("/3e");
    }


}

