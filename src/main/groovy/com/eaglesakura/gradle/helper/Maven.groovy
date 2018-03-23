package com.eaglesakura.gradle.helper;

/**
 * This class shouldn't use for all projects.
 * When next version, this class will remove.
 */
@Deprecated
class Maven {
    static def getArtifacts() {
        if (new File("maven").directory) {
            return new File("maven")
        } else if (System.env.CIRCLE_ARTIFACTS != null) {
            return new File(System.env.CIRCLE_ARTIFACTS)
        } else if (System.env.MAVEN_LOCAL_REPO != null) {
            return new File(System.env.MAVEN_LOCAL_REPO)
        } else {
            return new File("ci-release")
        }
    }
}
