import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {

    @Test
    public void TestAarryDeque() {
        StudentArrayDeque<Integer> stuAD = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> Ad = new ArrayDequeSolution<>();
        //testAddLast
        for (int i = 0; i < 10; i++) {
            Integer randomNum = StdRandom.uniform(100);
            stuAD.addLast(randomNum);
            Ad.addLast(randomNum);

        }
        for (int i = 0; i < 10; i++) {
            Integer actual = stuAD.get(i);
            Integer expected = Ad.get(i);
            assertEquals("Oh noooo!\nthis is bad in addLast():\n Random number " + actual + " not equal to " + expected + "!", expected, actual);
        }

        //testaddFirst
        for (int i = 0; i < 10; i++) {
            Integer randomNum = StdRandom.uniform(100);
            stuAD.addFirst(randomNum);
            Ad.addFirst(randomNum);
        }
        for (int i = 0; i < 10; i++) {
            Integer actual = stuAD.get(i);
            Integer expected = Ad.get(i);
            expected = Ad.get(i);
            assertEquals("Oh noooo!\nThis is bad in addFirst():\n Random number" + actual + " not equal to " + expected + "!", expected, actual);
        }

        //testRemoveFirst()
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList();

        for (int i = 0; i < 10; i++) {
            Integer randomNum = StdRandom.uniform(100);
            actualList.add(Ad.removeFirst());
            expectedList.add(stuAD.removeFirst());
        }
        for (int i = 0; i < 10; i++) {
            Integer actual = Ad.get(i);
            Integer expected = stuAD.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst()\n Random number " + actual + "not equal to" + expected + "!", expected, actual);

        }
        for (int i = 0; i < 10; i++) {
            Integer actual = actualList.get(i);
            Integer expected = expectedList.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst()\n Random number " + actual + "not equal to" + expected + "!"
                    , expected, actual);


        }
        //testRemoveLast
        actualList.clear();
        expectedList.clear();

        for (int i = 0; i < 10; i++) {
            actualList.add(Ad.removeLast());
            expectedList.add(stuAD.removeLast());

        }
        int actualSize = Ad.size();
        int expectedSize = stuAD.size();
        assertEquals("Oh noooo!\nThis is bad in removeLast():\n   actual size " + actualSize
                + " not equal to " + expectedSize + "!", expectedSize, actualSize);
        for (int i = 0; i < 10; i++) {
            Integer actual = actualList.get(i);
            Integer expected = expectedList.size();
            assertEquals("Oh noooo!\nThis is bad in removeLast()\n Random number " + actual + "not equal to" + expected + "!"
                    , expected, actual);

        }



    }


}
