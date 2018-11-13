import org.junit.jupiter.api.Test;
import valuestreams.Value;
import valuestreams.pipeline.Pipeline;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PipelineTests {
    @Test
    void basicUsage() {
        Pipeline<String, String> pipeline = Pipeline.input(String.class)
                .map(Integer::valueOf)
                .validate(i -> i > 10)
                .map(Object::toString);

        Value<String> success = pipeline.apply("12");
        Value<String> failIntegerParse = pipeline.apply("a");
        Value<String> failValidation = pipeline.apply("5");

        assertTrue(success.isPresent());
        assertEquals("12", success.getNullable());

        assertFalse(failIntegerParse.isPresent());
        assertFalse(failValidation.isPresent());
    }

    @Test
    void extendingPipeline() {
        Pipeline<String, Integer> base = Pipeline.input(String.class)
                .map(Integer::valueOf)
                .validate(i -> i > 1);

        Pipeline<String, Integer> square = base.map(i -> i*i);
        Pipeline<String, Integer> cube = base.map(i -> i*i*i);

        Value<Integer> squared = square.apply("5");
        Value<Integer> cubed = cube.apply("5");

        assertTrue(squared.isPresent());
        assertTrue(cubed.isPresent());

        assertTrue(squared.getNullable().intValue() != cubed.getNullable().intValue());
    }

    static int mapException(String str) throws IOException {
        throw new IOException("Can't map this.");
    }

    static boolean validateException(String str) throws IOException {
        throw new IOException("Can't validate this");
    }

    @Test
    void exceptionsPipeline() {
        Pipeline<String, Integer> mapPipeline = Pipeline.input(String.class)
                .mapWithException(PipelineTests::mapException);

        Pipeline<String, String> validatePipeline = Pipeline.input(String.class)
                .validateWithException(PipelineTests::validateException);

        assertFalse(mapPipeline.apply("").isPresent());
        assertFalse(validatePipeline.apply("").isPresent());
    }

    @Test
    void staticStreamPipeline() {
        Pipeline<String, String> pipeline = Pipeline.input(String.class)
                .map(Integer::valueOf)
                .validate(i -> i > 10)
                .map(Object::toString);

        Stream<String> inputStream = Stream.<String>builder().add("12").add("b").add("20").build();
        assertEquals(3, pipeline.applyStream(inputStream).count());

        inputStream = Stream.<String>builder().add("12").add("b").add("20").build();
        assertEquals(2, pipeline.applyStreamAndFilter(inputStream).count());
    }

    @Test
    void infiniteStreamPipeline() {
        Pipeline<Integer, String> pipeline = Pipeline.input(Integer.class)
                .validate(i -> i > 10)
                .map(Object::toString);

        Stream<Integer> inputStream = Stream.generate(() -> (int)((Math.random() * 20) + 8)).limit(10);
        Stream<Value<String>> outputStream = pipeline.applyStream(inputStream);

        assertEquals(10, outputStream.count());
    }
}
