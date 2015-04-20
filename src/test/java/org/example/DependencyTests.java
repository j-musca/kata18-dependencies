package org.example;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DependencyTests {

    private Dependencies dependencies = new Dependencies();

    @Before
    public void setUp() throws Exception {
        dependencies = new Dependencies();

        dependencies.addDirect('A', Lists.newArrayList('B', 'C'));
        dependencies.addDirect('B', Lists.newArrayList('C', 'E'));
        dependencies.addDirect('C', Lists.newArrayList('G'));
        dependencies.addDirect('D', Lists.newArrayList('A', 'F'));
        dependencies.addDirect('E', Lists.newArrayList('F'));
        dependencies.addDirect('F', Lists.newArrayList('H'));
    }

    @Test
    public void testComponentA() {
        assertThat(dependencies.getDependenciesFor('A')).isEqualTo("B C E F G H");
    }

    @Test
    public void testComponentB() {
        assertThat(dependencies.getDependenciesFor('B')).isEqualTo("C E F G H");
    }

    @Test
    public void testComponentC() {
        assertThat(dependencies.getDependenciesFor('C')).isEqualTo("G");
    }

    @Test
    public void testComponentD() {
        assertThat(dependencies.getDependenciesFor('D')).isEqualTo("A B C E F G H");
    }

    @Test
    public void testComponentE() {
        assertThat(dependencies.getDependenciesFor('E')).isEqualTo("F H");
    }

    @Test
    public void testComponentF() {
        assertThat(dependencies.getDependenciesFor('F')).isEqualTo("H");
    }

    @Test
    public void testComponentG() {
        assertThat(dependencies.getDependenciesFor('G')).isEqualTo("");
    }
}
