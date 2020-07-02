package com.rivia.software.marvelpoc.domain.api

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.*
import com.rivia.software.marvelpoc.utils.MarvelApiConstants.MARVEL_DOMAIN
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.io.IOException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MarvelClient {

    private lateinit var retrofitBuilder: Retrofit.Builder

    constructor() {
        initApiClient(MARVEL_DOMAIN)
    }

    constructor(baseUrl: String, datePattern: String = "yyyy-MM-dd'T'HH:mm:ss") {
        initApiClient(baseUrl, datePattern)
    }

    /**
     * ApiClient initializer.
     * It initializes a Retrofit.Builder instance that will be used to create each service call.
     * @param baseUrl Backend base URL
     */
    private fun initApiClient(baseUrl: String, datePattern: String = "yyyy-MM-dd'T'HH:mm:ss") {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()


        val gBuilder = GsonBuilder().setDateFormat(datePattern).registerTypeAdapter(Date::class.java, DateDeserializer())
        val gson = gBuilder.create()

        retrofitBuilder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(DateStringConverterFactory(GsonConverterFactory.create
                (gson)))
    }

    /**
     * Set a new backend base URL
     * @param baseUrl
     */
    fun setBaseUrl(baseUrl: String) {
        retrofitBuilder.baseUrl(baseUrl)
    }

    /**
     * @param baseUrl Specific base URL for the service being created
     * @param serviceClass Interface Class which has to be implemented by [this#retrofitBuilder]
     * @return Implemented #servicesClass interface, ready to be called
     */
    fun <T> createService(baseUrl: String, serviceClass: Class<T>): T =
        retrofitBuilder
            .baseUrl(baseUrl)
            .build()
            .create(serviceClass)

    /**
     * @param serviceClass Interface Class which has to be implemented by [this#retrofitBuilder]
     * @return Implemented #servicesClass interface, ready to be called
     */
    fun <T> createService(serviceClass: Class<T>): T =
        retrofitBuilder
            .build()
            .create(serviceClass)


    internal class DateDeserializer : JsonDeserializer<Date> {

        @SuppressLint("SimpleDateFormat")
        @Throws(JsonParseException::class)
        override fun deserialize(jsonElement: JsonElement, typeOF: Type, context: JsonDeserializationContext): Date? {
            for (format in DATE_FORMATS) {
                try {
                    return SimpleDateFormat(format).parse(jsonElement.asString)
                } catch (e: ParseException) {
                    Log.d(
                        "PARSE EXCEPTION", "Can't parse date: " + jsonElement.asString + " to ["
                                + format + "] format"
                    )
                }

            }
            Log.e(
                "PARSE EXCEPTION", "Unparseable date: \"" + jsonElement.asString + "\"" + ". " +
                        "Supported formats: " + DATE_FORMATS.contentToString()
            )
            return null
        }

        companion object {

            private val DATE_FORMATS = arrayOf<String>(
                "yyyy-MM-dd'T'HH:mm:ss"
            )
        }
    }


    internal class DateStringConverterFactory(private val delegateFactory: Converter.Factory) : Converter.Factory() {

        override fun stringConverter(
            type: Type,
            annotations: Array<Annotation>?,
            retrofit: Retrofit
        ): Converter<Any, String>? {
            for (annotation in annotations!!) {
                if (annotation is Query) {
                    // NOTE: If you also have a JSON converter factory installed in addition to
                    // this factory,
                    // you can call retrofit.requestBodyConverter(type, annotations) instead of
                    // having a
                    // reference to it explicitly as a field.
                    val delegate = delegateFactory
                        .requestBodyConverter(type, annotations, arrayOfNulls(0), retrofit)
                    return DelegateToStringConverter((delegate as Converter<Any, RequestBody>?)!!)
                }
            }
            return null
        }

    }


    internal class DelegateToStringConverter<Any>(private val delegate: Converter<Any, RequestBody>) :
        Converter<Any, String> {

        @Throws(IOException::class)
        override fun convert(value: Any): String {
            val buffer = okio.Buffer()
            delegate.convert(value)?.writeTo(buffer)
            return when (value) {
                is Date -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.getDefault()).format(value)
                is String -> value.toString()
                else -> buffer.readUtf8()
            }
        }
    }
}