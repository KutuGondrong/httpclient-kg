package com.kutugondrong.httpclientkg

import com.kutugondrong.httpclientkg.collection.RequestMethodTypeKG

/**
 * KG KutuGondrong
 * Setting is class for Required data or optional before using DefaultHttpClient
 * For type request method
 * @see RequestMethodTypeKG
 *
 *
 * @see HttpClientKG.execute
 */
class SettingRequestKG(
    val requestMethod: RequestMethodTypeKG?,
    val pathAndQuery: String = "",
    val jsonBody: String?,
    var properties: MutableList<PropertyKG> = ArrayList()
) {
    override fun toString(): String {
        return "Setting: \n" +
                "\trequestMethod = $requestMethod\n" +
                "\tpathAndQuery ='$pathAndQuery'\n" +
                "\tproperties = \n"+
                properties.let {
                    var log = ""
                    it.forEach{ l ->
                        log += l.toString()
                    }
                    log
                }+
                "\tjsonBody = \n$jsonBody"
    }
}
