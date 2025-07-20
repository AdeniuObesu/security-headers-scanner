package org.adeniuobesu.securityheadersscanner.core.rules;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;

public class HeaderRuleProvider {

    public List<HeaderRule> getRules() {
        Reflections reflections = new Reflections("org.adeniuobesu.securityheadersscanner.core.rules");

        Set<Class<? extends HeaderRule>> ruleClasses = reflections.getSubTypesOf(HeaderRule.class).stream()
            .filter(clazz -> clazz.getSimpleName().endsWith("Rule"))
            .filter(clazz -> !clazz.isInterface() && !java.lang.reflect.Modifier.isAbstract(clazz.getModifiers()))
            .collect(Collectors.toSet());

        return ruleClasses.stream()
            .map(this::safeInstantiate)
            .filter(java.util.Objects::nonNull)
            .collect(Collectors.toList());
    }

    private HeaderRule safeInstantiate(Class<? extends HeaderRule> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("⚠️ Impossible d'instancier la règle : " + clazz.getSimpleName() + " → " + e);
            return null;
        }
    }
}
