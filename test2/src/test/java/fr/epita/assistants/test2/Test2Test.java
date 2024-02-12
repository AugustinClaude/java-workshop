package fr.epita.assistants.test2;

import fr.epita.assistants.server.MyServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class Test2Test {
    @Test
    void division1() {
        assertThrows(Exception.class, () -> Test2.division(5, 0));
    }

    @Test
    void division2() {
        int r = Test2.division(6, 2);
        assertEquals(r, 3);
    }

    @Test
    void division3() {
        int r = Test2.division(12, 2);
        assertEquals(2 * r, 12);
    }

    @Test
    void division4() {
        int r = Test2.division(1, 2);
        assertEquals(r, 0);
    }

    @Test
    void server1() {
        assertThrows(Exception.class, Test2::serverGetResponseCode);
    }

    @Test
    void server2() {
        assertDoesNotThrow(MyServer::launchServer);
        try {
            long code = Test2.serverGetResponseCode();
            assertEquals(200, code);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertDoesNotThrow(MyServer::stopServer);
    }

    @Test
    void server3() throws IOException {
        assertDoesNotThrow(MyServer::launchServer);
        assertThrows(Exception.class, MyServer::launchServer);
        assertEquals(200, Test2.serverGetResponseCode());
        assertDoesNotThrow(MyServer::stopServer);
    }

    @Test
    void server4() {
        assertDoesNotThrow(MyServer::launchServer);
        assertDoesNotThrow(MyServer::stopServer);
        assertThrows(Exception.class, Test2::serverGetResponseCode);
    }

    @Test
    @Timeout(value = 1, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void server5() throws IOException {
        assertDoesNotThrow(MyServer::launchServer);
        for (int i = 0; i < 42; i++)
            assertEquals(200, Test2.serverGetResponseCode());

        assertDoesNotThrow(MyServer::stopServer);
    }

    @Test
    void tribonacci1() {
        long res = Test2.tribonacci(0);
        assertEquals(res, 0);

    }

    @Test
    void tribonacci2() {
        long res = Test2.tribonacci(1);
        assertEquals(res, 1);
    }

    @Test
    void tribonacci3() {
        long res = Test2.tribonacci(2);
        assertEquals(res, 1);
    }


    @Test
    void tribonacci4() {
        long res = Test2.tribonacci(4);
        assertEquals(res, 4);
    }


    @Test
    void tribonacci5() {
        long res = Test2.tribonacci(5);
        assertEquals(res, 7);
    }


    @Test
    void tribonacci6() {
        long res = Test2.tribonacci(9);
        assertEquals(res, 81);
    }


    @Test
    void tribonacci7() {
        long res = Test2.tribonacci(20);
        assertEquals(res, 66012);
    }

    @Test
    void tribonacci8() {
        assertThrows(Exception.class, () -> Test2.tribonacci(-2));
    }

    @Test
    @Timeout(1)
    void tribonacci9() {
        Test2.tribonacci(999999999);
    }
}
