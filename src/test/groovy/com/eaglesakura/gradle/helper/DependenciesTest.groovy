package com.eaglesakura.gradle.helper

import org.junit.Assert

class DependenciesTest extends GroovyTestCase {

    void test_依存バージョンが取得できる() throws Exception {
        def file = new File("src/test/resources/dependencies.txt");
        Assert.assertEquals(Dependencies.getVersion(file, "commons-codec:commons-codec", "1.+"), "1.10");
        Assert.assertEquals(Dependencies.getVersion(file, "com.eaglesakura:simple-logger", "1.1.+"), "1.1.23");
    }
}