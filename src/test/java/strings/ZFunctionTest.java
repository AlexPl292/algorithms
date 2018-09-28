package strings;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Alex Plate on 28.09.2018.
 */
@RunWith(JUnitParamsRunner.class)
public class ZFunctionTest {

    public Object parametersForTestZFunction() {
        return new Object[]{
                new Object[]{"", new int[0], "Empty string"},
                new Object[]{"a", new int[]{1}, "One char string"},
                new Object[]{"aaaaa", new int[]{5, 4, 3, 2, 1}, "Same char string"},
                new Object[]{"aaaaabbbb", new int[]{9, 4, 3, 2, 1, 0, 0, 0, 0}, "Several chars string"},
                new Object[]{"abcdef", new int[]{6, 0, 0, 0, 0, 0}, "Different chars"},
                new Object[]{"abcdefabcabcda", new int[]{14, 0, 0, 0, 0, 0, 3, 0, 0, 4, 0, 0, 0, 1}, "Difficult string"}
        };
    }

    @Test
    @Parameters
    @TestCaseName("{2}")
    public void testZFunction(String str, int[] expected, String testName) throws Exception {
        int[] zFunction = ZFunction.getFunction(str);

        assertArrayEquals(testName + ": test failed", expected, zFunction);
    }
}