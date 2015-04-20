package org.example;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CircularDependencyTests {

    private Dependencies dependencies = new Dependencies();

    @Before
    public void setUp() throws Exception {
        dependencies = new Dependencies();

        dependencies.add('A', Lists.newArrayList('B'));
        dependencies.add('B', Lists.newArrayList('C'));
        dependencies.add('C', Lists.newArrayList('A'));
    }

    @Test
    public void testComponentA() {
        assertThat(dependencies.getDependencies('A')).isEqualTo("B C");
    }

    @Test
    public void testComponentB() {
        assertThat(dependencies.getDependencies('B')).isEqualTo("A C");
    }

    @Test
    public void testComponentC() {
        assertThat(dependencies.getDependencies('C')).isEqualTo("A B");
    }
}
