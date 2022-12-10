package io.github.hyeonic.javastring.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StringTest {

    @Test
    void 생성자로_문자열을_생성한다() {
        String name = new String("hyeonic");

        assertThat(name).isEqualTo("hyeonic");
    }

    @Test
    void 문자열을_비교한다() {
        String name1 = new String("hyeonic");
        String name2 = "hyeonic";

        assertThat(name1.equals(name2)).isTrue();
        assertThat(name1 == name2).isFalse();
    }

    @Test
    void hashCode를_비교한다() {
        String name1 = new String("hyeonic");
        String name2 = "hyeonic";

        assertThat(name1.hashCode() == name2.hashCode()).isTrue();
    }

    @Test
    void 실제_주소를_비교한다() {
        String name1 = new String("hyeonic");
        String name2 = "hyeonic";

        assertThat(System.identityHashCode(name1) != System.identityHashCode(name2)).isTrue();
    }

    @Test
    void 문자열_set을_생성한다() {
        Set<String> names = new HashSet<>();
        names.add("hyeonic");
        names.add(new String("hyeonic"));

        assertThat(names).hasSize(1);
    }
}
