
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
class JUnitTestRunner {
    public static void main(String[] args) {


        Result res = JUnitCore.runClasses(Junit4TestTest.class);
        for (Failure fobj : res.getFailures()) {
            System.out.println(fobj.toString());

        }
        System.out.println(res.wasSuccessful());

    }
}
