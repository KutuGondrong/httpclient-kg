package com.kutugondrong.httpclientkg

/**
 * KG KutuGondrong
 * This class as Response after get data from network request
 *
 * @see HttpClientKG.execute
 */
class ResponseKG{
    var url: String? = null
    var responseCode: Int? = null
    var settingRequestKG: SettingRequestKG? = null
    var message: String? = null
    var responseBody: String? = null
    var responseBodyError: String? = null

    val success: Boolean
        get() = responseCode == 200


    override fun toString(): String {
        return "Response: \n" +
                "============================Response==========================\n" +
                "success = $success,\n"+
                "url = $url \n" +
                "responseCode = $responseCode\n" +
                "message = $message\n" +
                "$settingRequestKG"+
                "\n\n++++++Start++++++responseBody++++++Start++++++\n" +
                "$responseBody " +
                "\n++++++End+++++responseBody++++++End+++++++\n\n" +
                "+++++++Start+++++responseBodyError+++++Start+++++++\n" +
                "$responseBodyError" +
                "\n+++++++End+++++responseBodyError++++++End++++++\n" +
                "============================Response==========================\n"
    }


}