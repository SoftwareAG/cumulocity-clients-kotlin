package retrofit2.converter.gson

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ReadOnlyProperties(vararg val value: String)
