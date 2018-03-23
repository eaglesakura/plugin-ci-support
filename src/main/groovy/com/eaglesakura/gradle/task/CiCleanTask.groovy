package com.eaglesakura.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CiCleanTask extends DefaultTask {

    @TaskAction
    def onExecute() {
        File dstDirectory = project.eglibrary.ci.releaseDir;
        println("rm dst(${dstDirectory.absolutePath})")
        dstDirectory.deleteDir();
    }
}