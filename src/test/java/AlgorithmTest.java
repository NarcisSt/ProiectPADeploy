import com.project.deploy.algorithm.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

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

    @Test
    void given_wordIstanbul_when_normalize_then_shouldBeNormalized() {
        //Arrange
        String word = "İstanbul";
        Algorithm algorithm = new Algorithm();

        //Act
        String normalized = algorithm.normalize(word);

        //Assert
        assertEquals("istanbul", normalized);
    }

    @Test
    void correctAddress_withIasi() {
        Algorithm algorithm = new Algorithm();
        algorithm.setField5(new String[]{"Iasi","Iasi","romania"});
        algorithm.start();
        LinkedList<String> newAddress = algorithm.getCorrectAddress();
        LinkedList<String> mightBeCorrect = new LinkedList<>(Arrays.asList("romania", "Iasi", "Iasi"));

        assertEquals(mightBeCorrect, newAddress);

    }

    @Test
    void correctAddress_Iasi_withStreet() {
        Algorithm algorithm = new Algorithm();
        algorithm.setField5(new String[]{"strada Sf Lazar","nr 3252","bloc 3", "ap 2", "Iasi", "Iasi", "Romania"});
        algorithm.start();
        LinkedList<String> newAddress = algorithm.getCorrectAddress();
        LinkedList<String> mightBeCorrect = new LinkedList<>(Arrays.asList("Romania", "Iasi", "Iasi"));

        assertEquals(mightBeCorrect, newAddress);

    }

    @Test
    void correctAddress_withMulteCuvinte() {
        Algorithm algorithm = new Algorithm();
        algorithm.setField2(new String[]{"gorj"});
        algorithm.setField4(new String[]{"ro"});
        algorithm.setField5(new String[]{"Rosia de Amaradia"});
        algorithm.start();
        LinkedList<String> newAddress = algorithm.getCorrectAddress();
        LinkedList<String> mightBeCorrect = new LinkedList<>(Arrays.asList("ro", "gorj", "Rosia de Amaradia"));

        assertEquals(mightBeCorrect, newAddress);

    }

    @Test
    void correctAddress_withBacau() {
        Algorithm algorithm = new Algorithm();
        algorithm.setField5(new String[]{"Iasi","romania", "Bacau", "bacau"});
        algorithm.start();
        LinkedList<String> newAddress = algorithm.getCorrectAddress();
        LinkedList<String> mightBeCorrect = new LinkedList<>(Arrays.asList("romania", "Bacau", "bacau"));

        assertEquals(mightBeCorrect, newAddress);
    }

    @Test
    void correctAddress_Germany_Saxony() {
        Algorithm algorithm = new Algorithm();
        algorithm.setField2(new String[]{"DE"});
        algorithm.setField3(new String[]{"Glashutte"});
        algorithm.setField4(new String[]{"7235241"});
        algorithm.setField5(new String[]{"saxony"});
        algorithm.start();
        LinkedList<String> newAddress = algorithm.getCorrectAddress();
        LinkedList<String> mightBeCorrect = new LinkedList<>(Arrays.asList("DE", "saxony", "Glashutte"));

        assertEquals(mightBeCorrect, newAddress);
    }

}