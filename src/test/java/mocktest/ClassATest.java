package mocktest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClassA.class)
public class ClassATest {

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
