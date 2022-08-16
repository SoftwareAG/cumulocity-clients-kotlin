# CumulocityCoreLibrary

## Usage

To link the module into your Android application, modify your `settings.gradle` and your `gradle` build file as described below.

1. In `settings.gradle`, include the module `CumulocityCoreLibrary`. Optionally, you can reference the source code by linking the `projectDir` relative to your module's root directory.

```groovy
include ':CumulocityCoreLibrary'
project(":CumulocityCoreLibrary").projectDir = new File(rootDir, "relative to your projects dir/CumulocityCoreLibrary")
```

2. Add the module dependency to your applications `build` file:

```groovy
dependencies {
    implementation project(':CumulocityCoreLibrary')
}
``` 

3. Make sure to setup the Kotlin gradle plugin in your project's build file:

```groovy
buildscript {
    ext.kotlin_version = "1.4.21"
    repositories {
        google()
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}
```

### Basic Authentication

The client sends HTTP requests with the `Authorization` header that contains the word `Basic` word followed by a space and a base64-encoded string `username:password`.
Using Retrofit2, the `Authorization` header is added using an `okhttp3.Interceptor`.

```kotlin
val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
val authToken = Credentials.basic("username", "password")
val headerAuthorizationInterceptor = Interceptor { chain ->
	var request: Request = chain.request()
	val headers: Headers = request.headers().newBuilder().add("Authorization", authToken).build()
	request = request.newBuilder().headers(headers).build()
	chain.proceed(request)
}
clientBuilder.addInterceptor(headerAuthorizationInterceptor)
```

The customised `clientBuilder` can than be passed to any service instance using it's `companion` `Factory` object, i.e.:

```kotlin
SystemOptionsApi.Factory.create("endpoint", clientBuilder)
```
