package com.example.twitter.util

import com.google.common.io.BaseEncoding
import okhttp3.HttpUrl
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object TwitterAuthUtil {

    private const val VERSION_1_0 = "1.0"
    /** The encoding used to represent characters as bytes.  */
    private const val ENCODING = "UTF-8"
    private const val OAUTH_CONSUMER_KEY = "oauth_consumer_key"
    private const val OAUTH_TOKEN = "oauth_token"
    private const val OAUTH_TOKEN_SECRET = "oauth_token_secret"
    private const val OAUTH_SIGNATURE_METHOD = "oauth_signature_method"
    private const val OAUTH_SIGNATURE = "oauth_signature"
    private const val OAUTH_TIMESTAMP = "oauth_timestamp"
    private const val OAUTH_NONCE = "oauth_nonce"
    private const val OAUTH_VERSION = "oauth_version"
    private const val OAUTH_CALLBACK = "oauth_callback"
    private const val OAUTH_CALLBACK_CONFIRMED = "oauth_callback_confirmed"
    private const val OAUTH_VERIFIER = "oauth_verifier"
    private const val HMAC_SHA1 = "HMAC-SHA1"

    private val random = Random()

    fun generateAuthString(
        consumerKey: String, nonce: String, signature: String, signatureMethod: String,
        timestamp: String, token: String, version: String
    ) =     """
            OAuth oauth_consumer_key="$consumerKey", oauth_nonce="$nonce", oauth_signature="$signature", oauth_signature_method="$signatureMethod", oauth_timestamp="$timestamp", oauth_token="$token", oauth_version="$version"
            """.trimIndent()

    fun generateAuthHeader(
        methodHttp: String,
        baseUrl: HttpUrl,
        postParams: TreeMap<String, String> = TreeMap()
    ): String {
        val url = baseUrl.toUrl().toString().replaceAfter("?", "").replaceFirst("?", "")

        val timestamp = getTimestamp()
        val params = mutableMapOf(
            OAUTH_CONSUMER_KEY to AuthTempKeys.TWITTER_OAUTH_CONSUMER_KEY,
            OAUTH_NONCE to getNonce(
                timestamp
            ).toString(),
            OAUTH_TIMESTAMP to timestamp.toString(),
            OAUTH_SIGNATURE_METHOD to HMAC_SHA1,
            OAUTH_TOKEN to AuthTempKeys.TWITTER_OAUTH_ACCESS_TOKEN,
            OAUTH_VERSION to VERSION_1_0
        )
        val queryParams =
            extractQueryParams(baseUrl)

        if (postParams.isNotEmpty()) {
            params.putAll(postParams)
        }

        params.putAll(queryParams)

        val baseString =
            createSignatureBaseString(
                methodHttp,
                url,
                collectParametersString(
                    params
                )
            )

        val customerSecret =
            AuthTempKeys.TWITTER_OAUTH_CONSUMER_SECRET
        val oauthTokenSecret =
            AuthTempKeys.TWITTER_OAUTH_ACCESS_TOKEN_SECRET
        val signature = createSignature(
            baseString,
            createSigningKey(
                customerSecret,
                oauthTokenSecret
            )
        )

        params[OAUTH_SIGNATURE] = signature
        val authHeader =
            generateAuthString(
                percentEncode(params[OAUTH_CONSUMER_KEY]!!),
                percentEncode(params[OAUTH_NONCE]!!),
                percentEncode(params[OAUTH_SIGNATURE]!!),
                percentEncode(params[OAUTH_SIGNATURE_METHOD]!!),
                percentEncode(params[OAUTH_TIMESTAMP]!!),
                percentEncode(params[OAUTH_TOKEN]!!),
                percentEncode(params[OAUTH_VERSION]!!)
            )
        return authHeader
    }

    private fun getTimestamp() = System.currentTimeMillis() / 1000

    private fun getNonce() = getTimestamp() + random.nextInt()

    private fun getNonce(timestamp: Long) = timestamp + random.nextInt()

    private fun collectParametersString(params: Map<String, String>): String {
        val encodedParams = mutableMapOf<String, String>()
        for ((key, value) in params) {
            encodedParams.put(
                percentEncode(
                    key
                ), percentEncode(value)
            )
        }
        val sortedKeys = encodedParams.keys.sorted()
        val sb = StringBuilder()
        for ((index, key) in sortedKeys.withIndex()) {
            sb.append(key).append("=").append(encodedParams[key])
            if (index != sortedKeys.size - 1) {
                sb.append("&")
            }
        }
        return sb.toString()
    }

    private fun extractQueryParams(baseUrl: HttpUrl): Map<String, String> {
        val paramsMap = mutableMapOf<String, String>()
        val parameterNames = baseUrl.queryParameterNames
        for (paramName in parameterNames) {
            val paramValueList = baseUrl.queryParameterValues(paramName)
            paramValueList ?: continue
            val paramValue = paramValueList[0]
            paramValue ?: continue
            paramsMap.put(paramName, paramValue)
        }
        return paramsMap
    }

    private fun createSignatureBaseString(httpMethod: String, baseUrl: String, params: String): String {
        val sb = StringBuilder()
        sb.append(httpMethod.toUpperCase()).append('&')
        sb.append(percentEncode(baseUrl)).append('&')
        sb.append(percentEncode(params))
        return sb.toString()
    }

    private fun createSigningKey(customerSecret: String, oauthSecret: String?): String {
        val sb = StringBuilder()
        sb.append(
            percentEncode(
                customerSecret
            )
        ).append('&')
        oauthSecret?.let {
            sb.append(
                percentEncode(
                    oauthSecret
                )
            )
        }
        return sb.toString()
    }

    private fun createSignature(signatureBaseString: String, signatureKey: String): String {
        val hmacSHA1 = "HmacSHA1"
        val secretKeySpac = SecretKeySpec(signatureKey.toByteArray(Charsets.US_ASCII), hmacSHA1)
        val mac = Mac.getInstance(hmacSHA1)
        mac.init(secretKeySpac)
        val hmac = mac.doFinal(signatureBaseString.toByteArray(Charsets.US_ASCII))
        return BaseEncoding.base64().encode(hmac)
    }

    private fun percentEncode(s: String?): String {
        if (s == null) {
            return ""
        }
        try {
            return URLEncoder.encode(s,
                ENCODING
            )
                .replace("+", "%20")
                .replace("*", "%2A")
                .replace("%7E", "~")
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException(e.message, e)
        }
    }
}