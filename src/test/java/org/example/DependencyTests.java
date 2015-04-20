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

        dependencies.add('A', Lists.newArrayList('B', 'C'));
        dependencies.add('B', Lists.newArrayList('C', 'E'));
        dependencies.add('C', Lists.newArrayList('G'));
        dependencies.add('D', Lists.newArrayList('A', 'F'));
        dependencies.add('E', Lists.newArrayList('F'));
        dependencies.add('F', Lists.newArrayList('H'));
    }

    @Test
    public void testComponentA() {
        assertThat(dependencies.getDependencies('A')).isEqualTo("B C E F G H");
    }

    @Test
    public void testComponentB() {
        assertThat(dependencies.getDependencies('B')).isEqualTo("C E F G H");
    }

    @Test
    public void testComponentC() {
        assertThat(dependencies.getDependencies('C')).isEqualTo("G");
    }

    @Test
    public void testComponentD() {
        assertThat(dependencies.getDependencies('D')).isEqualTo("A B C E F G H");
    }

    @Test
    public void testComponentE() {
        assertThat(dependencies.getDependencies('E')).isEqualTo("F H");
    }

    @Test
    public void testComponentF() {
        assertThat(dependencies.getDependencies('F')).isEqualTo("H");
    }

    @Test
    public void testComponentG() {
        assertThat(dependencies.getDependencies('G')).isEqualTo("");
    }
}
