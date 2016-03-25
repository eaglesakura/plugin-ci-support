package com.eaglesakura.gradle.task

import com.eaglesakura.tool.log.Logger
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class CiCleanTask extends DefaultTask {

    @TaskAction
    def onExecute() {
        Logger.initialize();
        Logger.outLogLevel = 0;

        File dstDirectory = project.eglibrary.ci.releaseDir;

        Logger.out("rm dst(${dstDirectory.absolutePath})")
        dstDirectory.deleteDir();
    }
}