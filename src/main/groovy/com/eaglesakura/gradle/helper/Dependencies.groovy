package com.eaglesakura.gradle.helper

public class Dependencies {
    /**
     * dependenciesのバージョンを取得する
     *
     * @param dependenciesFile "./gradlew dependencies > dependenciesFile"で出力したファイル名
     * @param basePackageName 'package:module'形式のライブラリ名
     * @param defVerseion 解析に失敗した場合に返却するバージョン
     * @return Dependenciesバージョン
     */
    public
    static String getVersion(File dependenciesFile, String basePackageName, String defVersion) {
        def lines = [];
        if (!dependenciesFile.file) {
            return defVersion;
        }

        dependenciesFile.text.eachLine {
            if (!it.contains(basePackageName) || it.contains("(")) {
                return;
            }

            def tokens = it.split(" ");
            if (tokens == null || tokens.length == 0) {
                return;
            }

            // バージョン固定されている場合は"lib:name:1.0.0"とフルで取得できてしまうので、分割して再度最後を取得する
            def version = tokens[tokens.length - 1];
            if (version.startsWith(basePackageName)) {
                def splitVersions = version.split(":");
                version = splitVersions[splitVersions.length - 1];
            }
            lines += version;
        }
        if (lines.empty) {
            return defVersion;
        } else {
            return lines[0];
        }
    }

}
