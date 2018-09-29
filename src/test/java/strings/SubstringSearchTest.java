package strings;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Alex Plate on 29.09.2018.
 */
@RunWith(JUnitParamsRunner.class)
public class SubstringSearchTest {
    public Object parametersForTestSearch() {
        return new Object[]{
                new Object[]{"", "", new Integer[0], "Empty Strings"},
                new Object[]{"a", "a", new Integer[]{0}, "One char"},
                new Object[]{"b", "a", new Integer[]{}, "One Char, No Substring"},
                new Object[]{"a", "aaabba", new Integer[]{0, 1, 2, 5}, "One Char, Several Substrings"},
                new Object[]{"abc", "aabcbbbcbbbabccbcabcabc", new Integer[]{1, 11, 17, 20}, "Difficult string"}
        };
    }

    @Test
    @Parameters
    @TestCaseName("{3}")
    public void testSearch(String str, String source, Integer[] expected, String testName) throws Exception {
        Integer[] indexes = SubstringSearch.getIndexes(str, source);

        assertArrayEquals(testName + ": test failed", expected, indexes);
    }
}