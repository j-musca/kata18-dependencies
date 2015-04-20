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

        dependencies.addDirect('A', Lists.newArrayList('B'));
        dependencies.addDirect('B', Lists.newArrayList('C'));
        dependencies.addDirect('C', Lists.newArrayList('A'));
    }

    @Test
    public void testComponentA() {
        assertThat(dependencies.getDependenciesFor('A')).isEqualTo("B C");
    }

    @Test
    public void testComponentB() {
        assertThat(dependencies.getDependenciesFor('B')).isEqualTo("A C");
    }

    @Test
    public void testComponentC() {
        assertThat(dependencies.getDependenciesFor('C')).isEqualTo("A B");
    }
}
