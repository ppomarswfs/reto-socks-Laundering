package test.java.socks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import main.java.com.ppomar.socks.Solution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SolutionTest {

    private final Solution solution = new Solution();

    private static Stream<Arguments> testData() {
        return Stream.of(
                new TestCaseBuilder().capacity(0)
                        .clean(new int[] {1, 2, 1, 1})
                        .dirty(new int[] {1, 4, 3, 2, 4})
                        .solution(1)
                        .build(),
                new TestCaseBuilder().capacity(0)
                        .clean(new int[] {1})
                        .dirty(new int[] {1})
                        .solution(0)
                        .build(),
                new TestCaseBuilder().capacity(1)
                        .clean(new int[] {1})
                        .dirty(new int[] {1})
                        .solution(1)
                        .build(),
                new TestCaseBuilder().capacity(1)
                        .clean(new int[] {1})
                        .dirty(new int[] {2,2})
                        .solution(0)
                        .build(),
                new TestCaseBuilder().capacity(2)
                        .clean(new int[] {1})
                        .dirty(new int[] {2,2})
                        .solution(1)
                        .build(),
                new TestCaseBuilder().capacity(2)
                        .clean(new int[] {1, 2, 1, 1})
                        .dirty(new int[] {1, 4, 3, 2, 4})
                        .solution(3)
                        .build(),
                new TestCaseBuilder().capacity(1)
                        .clean(new int[] {1, 1, 2, 3})
                        .dirty(new int[] {3, 3})
                        .solution(2)
                        .build(),
                new TestCaseBuilder().capacity(2)
                        .clean(new int[] {1, 1, 2, 3})
                        .dirty(new int[] {3, 3, 3})
                        .solution(2)
                        .build(),
                new TestCaseBuilder().capacity(3)
                        .clean(new int[] {1, 1, 2, 3})
                        .dirty(new int[] {3, 3, 3})
                        .solution(3)
                        .build(),
                new TestCaseBuilder().capacity(50)
                        .clean(new int[] {1, 1, 2, 3, 3, 2, 1, 2, 3, 4, 1, 2, 3, 4, 1})
                        .dirty(new int[] {3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1})
                        .solution(17)
                        .build(),
                new TestCaseBuilder().capacity(50)
                        .clean(new int[] {1, 2, 3, 4, 5, 6, 7, 8})
                        .dirty(new int[] {1, 1, 2, 3, 4, 5, 6, 7, 8})
                        .solution(8)
                        .build(),
                new TestCaseBuilder().capacity(50)
                        .clean(new int[] {1, 1, 2, 3, 4, 5, 6, 7, 8})
                        .dirty(new int[] {1, 1, 2, 3, 4, 5, 6, 7, 8})
                        .solution(9)
                        .build(),
                new TestCaseBuilder().capacity(7)
                        .clean(new int[] {2, 3, 4, 5, 6, 7, 8})
                        .dirty(new int[] {1, 1, 2, 3, 4, 5, 6, 7, 8})
                        .solution(7)
                        .build()
        );
    }

    @ParameterizedTest(name = "Solution for k={0}, clean={1}, dirty={2} must be {3}")
    @MethodSource("testData")
    void test_do_laundry(int k, int[] clean, int[] dirty, int expectedResult) {
        assertEquals(expectedResult, solution.solution(k, clean, dirty));
    }

    private static class TestCaseBuilder {

        private int k;
        private int[] clean;
        private int[] dirty;
        private int result;

        public TestCaseBuilder capacity(int k) {
            this.k = k;
            return this;
        }

        public TestCaseBuilder clean(int[] clean) {
            this.clean = clean;
            return this;
        }

        public TestCaseBuilder dirty(int[] dirty) {
            this.dirty = dirty;
            return this;
        }

        public TestCaseBuilder solution(int result) {
            this.result = result;
            return this;
        }

        public Arguments build() {
            return Arguments.of(k, clean, dirty, result);
        }
    }
}
