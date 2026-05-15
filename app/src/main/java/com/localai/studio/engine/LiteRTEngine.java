package com.localai.studio.engine;

public interface LiteRTEngine {
    boolean isAvailable();
    String infer(String prompt, int maxTokens);
}
