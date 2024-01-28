package org.example.homework;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BirdUtilsTest {

    private static final String CUCKOO_WORD = "ку-ку";
    private static final String NIGHTINGALE_WORD = "чик-чирик";
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(originalOut);
    }

    @BeforeEach
    void resetStreams() {
        outContent.reset();
    }

    @Test
    void testCallCuckoo() {
        BirdUtils.callCuckoo();
        String output = outContent.toString();
        int numMatches = StringUtils.countMatches(output, CUCKOO_WORD);

        assertTrue(0 <= numMatches);
        assertTrue(100 > numMatches);
    }

    @Test
    void testCallNightingale() {
        BirdUtils.callNightingale();
        String output = outContent.toString();
        int numMatches = StringUtils.countMatches(output, NIGHTINGALE_WORD);

        assertTrue(0 <= numMatches);
        assertTrue(100 > numMatches);
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    void testCallBirds(GeneratedData data) {
        data.callBird.run();
        String output = outContent.toString();
        int numMatches = StringUtils.countMatches(output, data.word);

        assertTrue(0 <= numMatches);
        assertTrue(100 > numMatches);
    }

    private static Stream<GeneratedData> argumentsStream() {
        return Stream.of(
                new GeneratedData(BirdUtils::callCuckoo, CUCKOO_WORD),
                new GeneratedData(BirdUtils::callNightingale, NIGHTINGALE_WORD)
        );
    }

    static class GeneratedData {
        Runnable callBird;
        String word;

        public GeneratedData(Runnable callBird, String word) {
            this.callBird = callBird;
            this.word = word;
        }
    }
}