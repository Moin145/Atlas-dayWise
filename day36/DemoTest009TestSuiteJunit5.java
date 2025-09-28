import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        org.example.DemoTest007Tag_FilterJunit5.class,
        DemoTest008AfterBeforeJunit5.class
})
public class DemoTest009TestSuiteJunit5 {
    // no code needed inside, JUnit will run the selected classes
}