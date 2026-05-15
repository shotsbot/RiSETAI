#include <jni.h>
#include <string>

extern "C" JNIEXPORT jlong JNICALL
Java_com_localai_studio_engine_LlamaCppEngine_nativeInit(JNIEnv *, jobject, jstring modelPath) {
    return reinterpret_cast<jlong>(new std::string("loaded"));
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_localai_studio_engine_LlamaCppEngine_nativeGenerate(JNIEnv *env, jobject, jlong, jstring prompt, jint) {
    const char *cPrompt = env->GetStringUTFChars(prompt, nullptr);
    std::string response = std::string("[LOCAL STREAM MOCK] ") + cPrompt;
    env->ReleaseStringUTFChars(prompt, cPrompt);
    return env->NewStringUTF(response.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_localai_studio_engine_LlamaCppEngine_nativeFree(JNIEnv *, jobject, jlong ptr) {
    delete reinterpret_cast<std::string*>(ptr);
}
