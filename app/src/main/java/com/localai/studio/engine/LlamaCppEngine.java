package com.localai.studio.engine;

public class LlamaCppEngine {
    static { System.loadLibrary("localai_engine"); }
    private long nativePtr;

    public void load(String modelPath) { nativePtr = nativeInit(modelPath); }
    public String generate(String prompt, int maxTokens) { return nativeGenerate(nativePtr, prompt, maxTokens); }
    public void close() { nativeFree(nativePtr); nativePtr = 0; }

    private native long nativeInit(String modelPath);
    private native String nativeGenerate(long ptr, String prompt, int maxTokens);
    private native void nativeFree(long ptr);
}
