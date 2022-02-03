package za.co.lbnkosi.portfolio.domain.model

import okhttp3.ResponseBody
import za.co.lbnkosi.portfolio.util.enums.ResourceStatus

data class Resource<out T>(val resourceStatus: ResourceStatus, val data: T?, val message: Pair<String, ResponseBody?>?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(ResourceStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: Pair<String, ResponseBody?>, data: T?): Resource<T> {
            return Resource(ResourceStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(ResourceStatus.LOADING, data, null)
        }
    }
}
