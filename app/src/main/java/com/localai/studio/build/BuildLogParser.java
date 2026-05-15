package com.localai.studio.build;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildLogParser {
    private static final Pattern FILE_LINE = Pattern.compile("(.*\\.\\w+):(\\d+):");
    public String extractPrimaryError(String logLine) {
        Matcher m = FILE_LINE.matcher(logLine);
        return m.find() ? ("Error di " + m.group(1) + " line " + m.group(2)) : logLine;
    }
}
