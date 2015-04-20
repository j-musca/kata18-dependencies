package org.example;

import com.google.common.base.Joiner;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Dependencies {

    private final Map<Character, Set<Character>> dependenciesMap;

    public Dependencies() {
        dependenciesMap = new HashMap<>();
    }

    public void addDirect(final char component, final Collection<Character> dependencies) {
        dependenciesMap.putIfAbsent(component, new HashSet<>());
        dependenciesMap.get(component).addAll(dependencies);
    }

    public String getDependenciesFor(final char component) {
        final Set<Character> allDependencies = new TreeSet<>(findDependencies(component, new HashSet<>()));

        allDependencies.remove(component);

        return Joiner.on(' ').join(allDependencies);
    }

    private Set<Character> findDependencies(final char component, final Set<Character> alreadyVisitedComponents) {
        final Set<Character> dependencies = new HashSet<>();

        if (!alreadyVisitedComponents.contains(component)) {
            final Set<Character> componentDependencies = dependenciesMap.getOrDefault(component, new HashSet<>());

            alreadyVisitedComponents.add(component);
            dependencies.addAll(componentDependencies);

            for (char dependency : componentDependencies) {
                dependencies.addAll(findDependencies(dependency, alreadyVisitedComponents));
            }
        }

        return dependencies;
    }
}