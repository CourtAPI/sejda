package org.sejda;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

/**
 * Unit test to test against cycles.
 * 
 */
@AnalyzeClasses(packages = "org.sejda")
public class TestCycles {

    @ArchTest
    public static final ArchRule myRule = SlicesRuleDefinition.slices().matching("org.sejda.(*)..").should()
            .beFreeOfCycles();

}
