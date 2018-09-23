package strings;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Alex Plate on 21.09.2018.
 */
public class SequenceCounterTest {
    @Test
    public void testCharSeqCounter_EmptyString_EmptyMap() {
        String str = "";

        Map<Character, Integer> count = SequenceCounter.count(str);
        for (Map.Entry<Character, Integer> characterIntegerEntry : count.entrySet()) {
            assertEquals((Integer) 0, characterIntegerEntry.getValue());
        }
    }

    @Test
    public void testCharSeqCounter_OneCharString_OneEntry() {
        String str = "a";

        Map<Character, Integer> count = SequenceCounter.count(str);
        assertEquals((Integer) 1, count.get('a'));
    }

    @Test
    public void testCharSeqCounter_MultipleOneCharString_OneEntryMultipleTimes() {
        String str = "aaaaaaaaaa";

        Map<Character, Integer> count = SequenceCounter.count(str);
        assertEquals((Integer) 10, count.get('a'));
    }

    @Test
    public void testCharSeqCounter_MultipleTwoCharString_TwoEntryMultipleTimes() {
        String str = "aaaaaaaaaabbbaa";

        Map<Character, Integer> count = SequenceCounter.count(str);
        assertEquals((Integer) 10, count.get('a'));
        assertEquals((Integer) 3, count.get('b'));
    }

    @Test
    public void testCharSeqCounter_MultipleTwoCharString_TwoEntryMultipleTimes2() {
        String str = "aabbbaaaaaaaaaa";

        Map<Character, Integer> count = SequenceCounter.count(str);
        assertEquals((Integer) 10, count.get('a'));
        assertEquals((Integer) 3, count.get('b'));
    }

    @Test
    public void testCharSeqCounter_DifficultString_TwoEntryMultipleTimes2() {
        String str = "aabbbaaaaaaaaaacbbbaaaffffff6666666666666666661";

        Map<Character, Integer> count = SequenceCounter.count(str);
        assertEquals((Integer) 10, count.get('a'));
        assertEquals((Integer) 3, count.get('b'));
        assertEquals((Integer) 6, count.get('f'));
        assertEquals((Integer) 1, count.get('c'));
        assertEquals((Integer) 18, count.get('6'));
        assertEquals((Integer) 1, count.get('1'));
    }
}