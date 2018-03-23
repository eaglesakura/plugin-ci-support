package com.eaglesakura.gradle.task

import com.eaglesakura.gradle.googleplay.GooglePlayConsoleManager
import com.eaglesakura.util.IOUtil
import com.eaglesakura.util.StringUtil
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Google PlayへのアップロードをサポートするServiceを提供する
 *
 * アップロードは必ずServiceAccountを通して行う。
 */
class AndroidGooglePlayPublishTask extends DefaultTask {

    File p12

    File apk

    String applicationId

    String serviceAccountEmail

    String track

    /**
     * listings
     *   * ja-JP/title.txt
     *   * ja-JP/fullDescription.txt
     *   * ja-JP/shortDescription.txt
     */
    File listings

    /**
     * apk update Listings
     *  * ja-JP/apk.txt
     */
    File apkListings


    protected GooglePlayConsoleManager googlePlayConsoleManager;

    protected void onGooglePayTask() {
        googlePlayConsoleManager.autholize();

        if (!StringUtil.isEmpty(track)) {
            googlePlayConsoleManager.uploadApk(track, apkListings);
        }

        if (IOUtil.isDirectory(listings)) {
            googlePlayConsoleManager.updateListings(listings);
        }
    }

    @TaskAction
    def onExecute() {
        println "applicationId       : ${applicationId}"
        println "track               : ${track}"
        println "listings            : ${listings}"
        println "apk                 : ${apk}"
        println "apklistings         : ${apkListings}"
        println "serviceAccountEmail : ${serviceAccountEmail}"

        googlePlayConsoleManager = new GooglePlayConsoleManager()
        googlePlayConsoleManager.p12 = p12
        googlePlayConsoleManager.apk = apk
        googlePlayConsoleManager.applicationId = applicationId
        googlePlayConsoleManager.serviceAccountEmail = serviceAccountEmail

        onGooglePayTask()
    }
}