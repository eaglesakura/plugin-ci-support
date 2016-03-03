package com.eaglesakura.gradle.plugin

import com.eaglesakura.gradle.tasks.AndroidCiCollectTask
import com.eaglesakura.gradle.tasks.CiCleanTask
import com.eaglesakura.util.StringUtil
import org.apache.commons.codec.binary.Base64
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Androidアプリ開発用のサポートタスク
 */
public class CiSupportPlugin implements Plugin<Project> {
    void apply(Project target) {
//        println("AndroidSupportPlugin apply!")
        target.extensions.create("eglibrary", ExtensionEglibrary);

        // Jenkins以外から実行されている場合、適当な設定を行う
        if (!StringUtil.isEmpty(System.getenv("BUILD_NUMBER"))) {
            println("Build Jenkins");
            target.eglibrary.ci.ciRunning = true;
            target.eglibrary.ci.buildVersionCode = System.getenv("BUILD_NUMBER");
            target.eglibrary.ci.buildDate = System.getenv("BUILD_ID");
            target.eglibrary.ci.buildVersionName = "ci.${target.eglibrary.ci.buildVersionCode}";
            target.eglibrary.ci.ciType = "Jenkins";

        } else if (!StringUtil.isEmpty(System.getenv("CIRCLE_BUILD_NUM"))) {
            println("Build CircleCI");
            target.eglibrary.ci.ciRunning = true;
            target.eglibrary.ci.buildVersionCode = System.getenv("CIRCLE_BUILD_NUM");
            target.eglibrary.ci.buildDate = new Date().toLocaleString();
            target.eglibrary.ci.buildVersionName = "ci.${target.eglibrary.ci.buildVersionCode}";
            target.eglibrary.ci.ciType = "CircleCI";
        } else {
            println("Build Local");
            target.eglibrary.ci.ciRunning = false;
            target.eglibrary.ci.buildVersionCode = "1";
            target.eglibrary.ci.buildDate = new Date().toLocaleString();
            target.eglibrary.ci.buildVersionName = "build.${System.getenv("USER")}.${target.eglibrary.ci.buildDate}";
            target.eglibrary.ci.ciType = "Local";

        }

        // 規定のタスクを追加
        target.task('ciCollectAndroidApps', type: AndroidCiCollectTask)
        target.task('ciClean', type: CiCleanTask)
    }

    /**
     * ライブラリのデフォルト設定
     */
    public static class ExtensionEglibrary {
        def ci = new ExtensionCi();
    }

    /**
     * Jenkins等のCI環境を構築する
     */
    public static class ExtensionCi {
        File releaseDir = new File("ci-release").absoluteFile;

        /**
         * CI(Jenkins)上で実行されていればtrue
         *
         * それ以外ならばfalseとなる
         */
        boolean ciRunning = true;

        /**
         * ビルド用のバージョンコード
         */
        String buildVersionCode;

        /**
         * ビルド日時
         */
        String buildDate;

        /**
         * バージョン名
         */
        String buildVersionName;

        /**
         * CIの動作対象
         *
         * Jenkins / CircleCI / Local
         */
        String ciType;
    }
}