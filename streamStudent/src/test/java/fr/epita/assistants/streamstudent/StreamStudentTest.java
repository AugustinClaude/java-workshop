package fr.epita.assistants.streamstudent;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.*;

public class StreamStudentTest {
    static void assertStreamEquals(Stream<Pair<Integer, String>> expectedStream,
                                   Stream<Pair<Integer, String>> actualStream) {
        // Get iterators from stream
        Iterator<Pair<Integer, String>> iterator1 = expectedStream.iterator();
        Iterator<Pair<Integer, String>> iterator2 = actualStream.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            // Get next objects
            Pair<Integer, String> login1 = iterator1.next();
            Pair<Integer, String> login2 = iterator2.next();

            // Check if pairs are equal
            assertEquals(login1, login2);
        }

        assertTrue(!iterator1.hasNext() && !iterator2.hasNext(),
                "Streams do not have the same length");

    }

    @Test
    public void validatorLoginContainsTwoOrMoreUnderscore() {
        Pair<Integer, String> loginTwoUnderscore = new Pair<>(50, "xavier_login_");
        Pair<Integer, String> loginValid = new Pair<>(90, "xavier_login");
        Pair<Integer, String> loginMultipleUnderscord = new Pair<>(10, "_login__x");
        Streamer streamer = new Streamer();

        var loginList = List.of(loginTwoUnderscore, loginValid, loginMultipleUnderscord);

        var expected = List.of(loginValid).stream();
        var actual = streamer.validator(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void validatorTest() {
        Pair<Integer, String> s1 = new Pair<>(-1, "abc_z");
        Pair<Integer, String> s2 = new Pair<>(10, "loginx");
        Pair<Integer, String> s3 = new Pair<>(10, "login_x");
        Pair<Integer, String> s4 = new Pair<>(95, "asdf.x_poi");
        Streamer streamer = new Streamer();

        var loginList = List.of(s1, s2, s3, s4);

        var expected = Stream.of(s3);
        var actual = streamer.validator(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void orderGradeTest() {
        Pair<Integer, String> s1 = new Pair<>(3, "abc_z");
        Pair<Integer, String> s2 = new Pair<>(10, "login_f");
        Pair<Integer, String> s3 = new Pair<>(82, "login.d");
        Pair<Integer, String> s4 = new Pair<>(16, "login.c");
        Pair<Integer, String> s5 = new Pair<>(32, "login.b");
        Pair<Integer, String> s6 = new Pair<>(64, "login_a");
        Pair<Integer, String> s7 = new Pair<>(45, "login.x");
        Pair<Integer, String> s8 = new Pair<>(95, "asdfx_poi");
        Pair<Integer, String> s9 = new Pair<>(21, "asdfx_poiddd");
        Streamer streamer = new Streamer();

        var loginList = List.of(s1, s2, s3, s4, s5, s6, s7, s8, s9);

        var expected = Stream.of(s1, s2, s4, s9, s5, s7, s6, s3, s8);
        var actual = streamer.orderGrade(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void headOfTheClassTest() {
        Pair<Integer, String> s1 = new Pair<>(5, "abc_a");
        Pair<Integer, String> s2 = new Pair<>(15, "abc_z");
        Pair<Integer, String> s3 = new Pair<>(15, "abc_b");
        Streamer streamer = new Streamer();

        var loginList = List.of(s1, s2, s3);

        var expected = Optional.of(s3);
        var actual = streamer.headOfTheClass(loginList.stream());

        assertEquals(expected, actual);
    }

    @Test
    public void quickFixTest() {
        Pair<Integer, String> s1 = new Pair<>(60, "abc_a");
        Pair<Integer, String> s2 = new Pair<>(60, "mAximu_m");
        Pair<Integer, String> s3 = new Pair<>(100, "mAximu_m");
        Streamer streamer = new Streamer();

        var loginList = List.of(s1, s2);

        var expected = Stream.of(s1, s3);
        var actual = streamer.quickFix(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void encryptionTest() {
        Pair<Integer, String> s1 = new Pair<>(10, "tigrou_c");
        Pair<Integer, String> s2 = new Pair<>(10, "login_x");
        Pair<Integer, String> s3 = new Pair<>(10, "ou_ctigr");
        Pair<Integer, String> s4 = new Pair<>(10, "in_xlog");
        Streamer streamer = new Streamer();

        var loginList = List.of(s1, s2);

        var expected = Stream.of(s3, s4);
        var actual = streamer.encryption(loginList.stream());

        assertStreamEquals(expected, actual);
    }
}
