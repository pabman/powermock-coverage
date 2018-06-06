package mocktest;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest(ClassA.class)
public class ClassATestWithRule {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    private ClassA classA = new ClassA();

    @Mock
    private ClassB mockClassB;

    @Test
    public void testDoSomethingWithoutMock() {
        String something = classA.doSomething();
        assertThat(something, is("The actual something"));
    }

    @Test
    public void testDoSomethingWithMock() throws Exception {
        PowerMockito.whenNew(ClassB.class).withNoArguments().thenReturn(mockClassB);
        when(mockClassB.getSomething()).thenReturn("Something else");

        String something = classA.doSomething();

        assertThat(something, is("Something else"));
    }

}
