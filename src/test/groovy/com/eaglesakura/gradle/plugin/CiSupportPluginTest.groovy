package com.eaglesakura.gradle.plugin

import org.gradle.testfixtures.ProjectBuilder

public class CiSupportPluginTest extends GroovyTestCase {

    public void testPluginApply() {
        def project = ProjectBuilder.builder().build();
        project.apply plugin: CiSupportPlugin

        project.tasks.ciClean.execute();
        project.tasks.ciCollectAndroidApps.execute();
    }
}