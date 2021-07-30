package com.kutugondrong.httpclientkg.exception

import java.lang.RuntimeException
import com.kutugondrong.httpclientkg.HttpClientKG

/**
 * KG KutuGondrong
 * DefaultHttpClientException is exception for network request DefaultHttpClient
 *
 *
 * @see HttpClientKG
 */
class DefaultHttpClientException(message: String) : RuntimeException(message)
