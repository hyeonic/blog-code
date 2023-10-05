package c.hyeoni.lockfreestackusingjava;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClassicStackTest {

    @DisplayName("classicStack을 검증한다.")
    @Test
    void test1() {
        ClassicStack<Integer> stack = new ClassicStack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        List<Integer> result = List.of(
                stack.pop(),
                stack.pop(),
                stack.pop()
        );

        assertThat(stack.pop()).isNull();
        assertThat(result).size()
                .isEqualTo(3);
        assertThat(result).containsExactly(3, 2, 1);
    }
}
