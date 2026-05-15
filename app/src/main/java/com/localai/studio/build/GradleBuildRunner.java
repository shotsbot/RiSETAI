package com.localai.studio.build;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class GradleBuildRunner {
    public interface LogListener { void onLine(String line); }
    public int run(File projectDir, String task, LogListener listener) throws Exception {
        Process process = new ProcessBuilder("sh", "-c", "./gradlew " + task)
                .directory(projectDir).redirectErrorStream(true).start();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line; while ((line = br.readLine()) != null) listener.onLine(line);
        }
        return process.waitFor();
    }
}
