package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class GeneratorTest {
    public static final String TEMPLATE = "I am a ${name}, Who are ${subject}? ";
    Generator generator = new Generator3d();

    @Disabled
    @Test
    void whenNamePetrArsentevSubjectYou() {
        Map<String, String> args =
                Map.of("name", "Petr Arsentev", "subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";
        String result = generator.produce(TEMPLATE, args);
        assertThat(result).isEqualTo(expected);
    }

    @Disabled
    @Test
    void whenKeysNotInMap() {
        Map<String, String> args = Map.of("name", "Petr Arsentev");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> generator.produce(TEMPLATE, args));
    }

    @Disabled
    @Test
    void whenExcessKeysInMap() {
        Map<String, String> args =
                Map.of(
                        "name", "Petr Arsentev",
                        "subject", "you",
                        "sex", "male");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> generator.produce(TEMPLATE, args));
    }
}