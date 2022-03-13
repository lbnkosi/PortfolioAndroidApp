package za.co.lbnkosi.portfolio.domain.model

import okhttp3.ResponseBody
import za.co.lbnkosi.portfolio.domain.enums.ResourceStatus

data class Resource<out T>(val resourceStatus: ResourceStatus, val data: T?, val extraData: Any? = null, val message: Pair<String, ResponseBody?>?) {
    companion object {
        fun <T> success(data: T?, extraData: Any? = null): Resource<T> {
            return Resource(ResourceStatus.SUCCESS, data, null, null)
        }

        fun <T> error(msg: Pair<String, ResponseBody?>, data: T?): Resource<T> {
            return Resource(ResourceStatus.ERROR, data, null, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(ResourceStatus.LOADING, data, null, null)
        }
    }
}
