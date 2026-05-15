package com.localai.studio.build;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GradleBuildRunner {
    public interface LogListener { void onLine(String line); }
    private static final Set<String> ALLOWED_TASKS = new HashSet<>(Arrays.asList(
            "tasks", "assembleDebug", "assembleRelease", "clean"
    ));

    public int run(File projectDir, String task, LogListener listener) throws Exception {
        if (!ALLOWED_TASKS.contains(task)) {
            throw new IllegalArgumentException("Task not allowed: " + task);
        }
        File gradlew = new File(projectDir, "gradlew");
        if (!gradlew.exists()) {
            throw new IllegalStateException("Gradle wrapper tidak ditemukan pada: " + projectDir.getAbsolutePath());
        }

        String command = "./gradlew " + task;
        listener.onLine("$ " + command);

        Process process = new ProcessBuilder("sh", "-c", command)
                .directory(projectDir)
                .redirectErrorStream(true)
                .start();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                listener.onLine(line);
            }
        }
        return process.waitFor();
    }
}
