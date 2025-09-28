import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class DemoTest01Hamcrest {
    @Test
    public void hamcrestMethod() {
        String str1 = "Apple";
        String str2 = "Apple";
        assertThat(str1 ,equalToIgnoringCase(str2));
    }
}

