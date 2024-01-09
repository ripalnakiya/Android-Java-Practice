#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("apikeysndk");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("apikeysndk")
//      }
//    }
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ripalnakiya_apikeysndk_MainActivity_getApiKey(JNIEnv *env, jobject thiz) {
    jstring API_KEY = (jstring) "MyAPIKeyInNDK";
    return env->NewStringUTF(reinterpret_cast<const char *>(API_KEY));
}