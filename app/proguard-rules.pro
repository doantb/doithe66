# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#### -- Picasso --
# -keep com.squareup.picasso.**

 #### -- OkHttp --

# -keep com.squareup.okhttp.internal.**

 #### -- Apache Commons --

# -keep org.apache.commons.logging.**

 -keep class com.bumptech.glide.**{ *; }
 -keepnames class com.bumptech.glide.Glide
 -keepnames class com.squareup.picasso.Picasso
 -keepclassmembers class * implements android.os.Parcelable {
     static ** CREATOR;
 }

 # Workaround for ProGuard not recognizing dontobfuscate
 # https://speakerdeck.com/chalup/proguard
 -dontobfuscate
 -dontoptimize
 -optimizations !code/allocation/variable

 -keep class butterknife.** { *; }
 -dontwarn butterknife.internal.**
 -keep class **$$ViewBinder { *; }

 -keepclasseswithmembernames class * {
     @butterknife.* <fields>;
 }

 -keepclasseswithmembernames class * {
     @butterknife.* <methods>;
 }

 -dontwarn retrofit.**
 -keep class retrofit.** { *; }
 -keepattributes Signature
 -keepattributes Exceptions

 -keepattributes Signature
 -keepattributes *Annotation*
 -keep class okhttp3.** { *; }
 -keep interface okhttp3.** { *; }
 -dontwarn okhttp3.**
 -dontnote okhttp3.**

 # Okio
 -keep class sun.misc.Unsafe { *; }
 -dontwarn java.nio.file.*
 -dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement