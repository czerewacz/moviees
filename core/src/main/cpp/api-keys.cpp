
#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_ai_akun_core_platform_ApiKeyRetriever_getMovieApiKey(JNIEnv* env, jobject /* this */) {
    std::string apiKey = "e94548a8cfdd2a558f15ac140df64f9d";
    return env->NewStringUTF(apiKey.c_str());
}
