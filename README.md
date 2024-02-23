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

### Use your own domain model

The CumulocityCoreLibrary allows custom data models. The following classes are designed to be extensible:

- `Alarm`, `AuditRecord`, `CategoryOptions`, `CustomProperties`, `Event`, `ManagedObject`, `Measurement`, `Operation`

Those classes allow to add an arbitrary number of additional properties as a list of key-value pairs. These properties are known as custom fragments and can be of any type. Each custom fragment is identified by a unique name. Thus, developers can propagate their custom fragments using:

```kotlin
Alarm.Serialization.registerAdditionalProperty(typeName: String, type: Class<*>)
```

Each of the extensible objects contains a dictionary object holding instances of custom fragments. Use the custom fragment's key to access its value.

### Working with errors

HTTP error codes will be forwarded and can be accessed using a `retrofit2.Response`. Error codes can be retrievied by calling `#code()`, respectively `#message()`. The response object also allows access to error objects. Here, a so called response body converter needs to be used. In this context the `Retrofit` instance allows to access the configured converter. Use the companion object `Factory` on each the API interfaces to acces a pre-configured `Retrofit.Builder`.

The error object itself can be converted from the response `errorBody`:

```kotlin
override fun onResponse(call: Call<T>?, response: Response<T>?) {
    // response?.message()
    // response?.code()
    response?.errorBody()?.let {
		val retrofitBuilder: Retrofit.Builder = ...
		val converter = retrofitBuilder.build().responseBodyConverter<Error>(Error::class.java, arrayOf())
		converter.convert(it)
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
CurrentUserApi.Factory.create("endpoint", clientBuilder)
```

### How to use in a Cumulocity Microservice

To use the «clientInfo.name» in a Cumulocity Microservice, you need to add the library and 3rd party dependencies to your `pom.xml` file

```xml
<dependency>
    <groupId>com.cumulocity.???</groupId>
    <artifactId>«clientInfo.name»</artifactId>
    <version>«clientInfo.versionName»-«clientInfo.buildNumber»</version>
</dependency>
<dependency>
    <groupId>com.squareup.retrofit2</groupId>
    <artifactId>retrofit</artifactId>
    <version>2.9.0</version>
</dependency>
<dependency>
    <groupId>com.squareup.retrofit2</groupId>
    <artifactId>converter-jackson</artifactId>
    <version>2.9.0</version>
</dependency>
<dependency>
    <groupId>com.squareup.retrofit2</groupId>
    <artifactId>converter-gson</artifactId>
    <version>2.9.0</version>
</dependency>
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
<dependency>
    <groupId>com.squareup.retrofit2</groupId>
    <artifactId>converter-scalars</artifactId>
    <version>2.9.0</version>
</dependency>
```

To allow an easy annotation based usage of the API classes, you can use this utility class to create the Spring beans for each API class:

```kotlin 
import com.cumulocity.client.api.ChildOperationsApi
import com.cumulocity.client.api.ManagedObjectsApi
import com.cumulocity.microservice.subscription.model.MicroserviceSubscriptionAddedEvent
import com.cumulocity.microservice.subscription.service.MicroserviceSubscriptionsService
import okhttp3.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class C8yApiInitializationUtil {

    @Autowired
    private lateinit var microserviceSubscriptionsService: MicroserviceSubscriptionsService

    @Value("\${C8Y_BASEURL:#{'\${C8Y.baseURL}'}}")
    private val baseUrl: String? = null

    private val clientBuilders = HashMap<String, OkHttpClient.Builder>()

    @EventListener
    private fun onMicroserviceSubscriptionAddedEvent(event: MicroserviceSubscriptionAddedEvent) {
        var clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val credentials = event.credentials
        val tenantId = credentials.tenant

        val authToken = Credentials.basic(tenantId + "/" + credentials.username, credentials.password)
        val headerAuthorizationInterceptor = Interceptor { chain ->
            var request: Request = chain.request()
            val headers: Headers = request.headers.newBuilder().add("Authorization", authToken).build()
            request = request.newBuilder().headers(headers).build()
            chain.proceed(request)
        }
        clientBuilder.addInterceptor(headerAuthorizationInterceptor)
        clientBuilders[tenantId] = clientBuilder
    }

    @Lazy
    @Bean
    private fun getChildOperationsApi(): ChildOperationsApi {
        return ChildOperationsApi.Factory.create(baseUrl!!, clientBuilders[microserviceSubscriptionsService.tenant]!!)
    }

    @Lazy
    @Bean
    private fun getManagedObjectsApi(): ManagedObjectsApi {
        return ManagedObjectsApi.Factory.create(baseUrl!!, clientBuilders[microserviceSubscriptionsService.tenant]!!)
    }
}
```
Obviously you can add more API beans to the util class. The `@Lazy` annotation is used to delay the creation of the beans until they are actually used. This is necessary because the `MicroserviceSubscriptionAddedEvent` is fired before the `MicroserviceSubscriptionsService` is initialized.

To actually use the beans, you can simply autowire them in your service classes:
```kotlin
@Lazy
@Autowired
private val managedObjectsApi: ManagedObjectsApi? = null
```
The `@Lazy` annotation is again required to delay the creation of the bean until the microservice is completely initialized.

## Contribution

If you've spotted something that doesn't work as you'd expect, or if you have a new feature you'd like to add, we're happy to accept contributions and bug reports.

For bug reports, please raise an issue directly in this repository by selecting the `issues` tab and then clicking the `new issue` button. Please ensure that your bug report is as detailed as possible and allows us to reproduce your issue easily.

In the case of new contributions, please create a new branch from the latest version of `main`. When your feature is complete and ready to evaluate, raise a new pull request.

---

These tools are provided as-is and without warranty or support. They do not constitute part of the Software AG product suite. Users are free to use, fork and modify them, subject to the license agreement. While Software AG welcomes contributions, we cannot guarantee to include every contribution in the master project.
