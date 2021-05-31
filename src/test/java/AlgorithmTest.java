import com.project.deploy.algorithm.Algorithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlgorithmTest {

    @Test
    void given_wordsWithLowerCase_when_normalize_then_shouldBeNormalized() {
        //Arrange
        String word = "test";
        Algorithm algorithm = new Algorithm();

        //Act
        String normalized = algorithm.normalize(word);

        //Assert
        assertEquals("test", normalized);
    }

    @Test
    void given_wordsWithUpperCase_when_normalize_then_shouldBeNormalized() {
        //Arrange
        String word = "TEST";
        Algorithm algorithm = new Algorithm();

        //Act
        String normalized = algorithm.normalize(word);

        //Assert
        assertEquals("test", normalized);
    }

    @Test
    void given_wordsWithSpecialCharacters_when_normalize_then_shouldBeNormalized() {
        //Arrange
        String word = "Teșț";
        Algorithm algorithm = new Algorithm();

        //Act
        String normalized = algorithm.normalize(word);

        //Assert
        assertEquals("test", normalized);
    }

    @Test
    void given_wordsWithDigits_when_normalize_then_shouldBeNormalized() {
        //Arrange
        String word = "TeSt123";
        Algorithm algorithm = new Algorithm();

        //Act
        String normalized = algorithm.normalize(word);

        //Assert
        assertEquals("test123", normalized);
    }

}