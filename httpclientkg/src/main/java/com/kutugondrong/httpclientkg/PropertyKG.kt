package com.kutugondrong.httpclientkg

/**
 * KG KutuGondrong
 * Item for set request property
 *
 *
 * @see SettingRequestKG
 */

data class PropertyKG(
    val key: String,
    val value: String
) {
    override fun toString(): String {
        return "\t\tProperty:key='$key', value='$value'\n"
    }
}