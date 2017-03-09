package uk.co.xenonique.clients.unisys;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * The type CommonTestUtility
 *
 * @author Peter Pilgrim (peter)
 */
public class CommonTestUtility {

    public static void assertLightDisplayReset( LightContainer container, LightDisplayStrategy strategy )
    {
        final int N = (int)(3 + Math.random() * 100);
        for ( int j=0; j< N; ++j) {
            strategy.nextSequence();
        }
        assertThat( strategy.getSequenceCount(), is(N));

        strategy.reset();
        assertThat( strategy.getSequenceCount(), is(0));
        for (int j=0; j<container.size(); ++j) {
            assertThat( container.getLight(j).isLit(), is(false));
        }
    }
}
