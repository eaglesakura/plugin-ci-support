package com.eaglesakura.gradle.helper;

public class Maven {
    /**
     * Mavenの格納先を確定する
     */
    static def getArtifacts() {
        if (new File("maven").directory) {
            return new File("maven");
        } else if (System.env.CIRCLE_ARTIFACTS != null) {
            return new File(System.env.CIRCLE_ARTIFACTS);
        } else if (System.env.MAVEN_LOCAL_REPO != null) {
            return new File(System.env.MAVEN_LOCAL_REPO);
        } else {
            return new File("ci-release");
        }
    }
}
