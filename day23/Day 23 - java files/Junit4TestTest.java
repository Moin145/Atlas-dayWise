import org.junit.*;
import static org.junit.Assert.*;

public class Junit4TestTest {

    private Junit4Test obj;

    @Before
    public void setUp() {
        obj = new Junit4Test();
    }

    @Test
    public void testCompare_FirstGreater() {
        int result = obj.compare(10, 5);
        assertEquals(1, result);
    }

    @Test
    public void testCompare_SecondGreater() {
        int result = obj.compare(5, 10);
        assertEquals(-1, result);
    }

    @Test
    public void testCompare_BothEqual() {
        int result = obj.compare(5, 5);
        // Depending on your logic, this currently returns -1
        assertEquals(-1, result); // or modify original method to return 0 if you want
    }
}
