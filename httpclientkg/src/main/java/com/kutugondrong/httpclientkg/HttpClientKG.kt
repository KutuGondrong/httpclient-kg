package com.kutugondrong.httpclientkg

import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import com.kutugondrong.httpclientkg.collection.RequestMethodTypeKG
import com.kutugondrong.httpclientkg.exception.DefaultHttpClientException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * KG KutuGondrong
 * This is main class of network request
 * <pre><code>
 * val httpClient = DefaultHttpClient.dslDefaultHttpClient {
 *          baseUrl = BuildConfig.SERVER_BASE_URL
 *          properties {
 *              property {
 *                  key = "Authorization"
 *                  value = "Client-ID ${BuildConfig.API_KEY}"
 *              }
 *          }
 *      }
 * Set properties before network request
 * @see PropertyKG
 */

class HttpClientKG (builder: Builder) {

    private var baseUrl: String? = null
    private var properties: MutableList<PropertyKG> = ArrayList()


    init {
        baseUrl = builder.baseUrl
        properties.addAll(builder.properties)
    }

    /**
     * KG KutuGondrong
     * This method using class Setting as requirement before network request
     * @see SettingRequestKG
     * This method return class Response after network request
     * @see ResponseKG
     * Main function in method processGetResponse
     * @see processGetResponse
     */
    suspend fun execute(setting : SettingRequestKG): ResponseKG {
        return withContext(Dispatchers.IO) {
            processGetResponse(setting)
        }
    }

    private fun processGetResponse(setting : SettingRequestKG): ResponseKG {
        validation(setting)
        val responseKG = ResponseKG()
        try {
            responseKG.url = "$baseUrl${setting.pathAndQuery}"
            responseKG.settingRequestKG = setting
            val url = URL("$baseUrl${setting.pathAndQuery}")
            (url.openConnection() as? HttpURLConnection)?.run {
                requestMethod = setting.requestMethod?.name
                setting.properties.addAll(properties)
                for (i in setting.properties.indices) {
                    setRequestProperty(setting.properties[i].key, setting.properties[i].value)
                }
                setting.jsonBody?.apply {
                    doInput = true
                    outputStream.write(this.toByteArray())
                }
                responseKG.responseCode = responseCode
                if (inputStream != null) {
                    responseKG.responseBody = inputStream.bufferedReader().use(BufferedReader::readText)
                }
                if (errorStream != null) {
                    responseKG.responseBodyError = errorStream.bufferedReader().use(BufferedReader::readText)
                }
                return responseKG
            }
            responseKG.message = "Cannot open HttpURLConnection"
        } catch (e: Exception) {
            responseKG.message = "$e"
        }
        return  responseKG
    }

    private fun validation(setting: SettingRequestKG) {
        if (baseUrl == null) {
            throw DefaultHttpClientException("Base url should not empty")
        }
        if (setting.requestMethod == null) {
            throw DefaultHttpClientException("Request method should not null use " +
                    "com.kutugondrong.httpclientkg.collection.RequestMethodTypeKG for request method")
        }
    }


    class SettingBuilder {
        var requestMethod: RequestMethodTypeKG? = null
        var pathAndQuery: String = ""
        var jsonBody: String? = null
        var properties = mutableListOf<PropertyKG>()
        fun properties(block: Properties.() -> Unit) {
            properties.addAll(Properties().apply(block))
        }
        fun build() : SettingRequestKG = SettingRequestKG(requestMethod, pathAndQuery, jsonBody, properties)
    }


    companion object {
        inline fun dslDefaultHttpClient (block: Builder.() -> Unit) =
            Builder().apply(block)
                .build()

        inline fun dslSettingBuilder (block: SettingBuilder.() -> Unit) =
            SettingBuilder().apply(block)
                .build()
    }


    class Builder {
        var baseUrl: String? = null
        var properties = mutableListOf<PropertyKG>()

        fun properties(block: Properties.() -> Unit) {
            properties.addAll(Properties().apply(block))
        }

        fun build () = HttpClientKG(this)
    }

    class Properties: ArrayList<PropertyKG>() {
        fun property(block: PropertyBuilder.() -> Unit) {
            add(PropertyBuilder().apply(block).build())
        }
    }

    class  PropertyBuilder{
        var key: String = ""
        var value: String = ""

        fun build() : PropertyKG = PropertyKG(key, value)
    }

    fun properties(block: Properties.() -> Unit) {
        properties.addAll(Properties().apply(block))
    }

}