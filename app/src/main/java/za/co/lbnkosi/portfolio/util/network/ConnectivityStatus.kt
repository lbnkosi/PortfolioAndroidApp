package za.co.lbnkosi.portfolio.util.network

import java.io.IOException

object ConnectivityStatus {

    @Throws(InterruptedException::class, IOException::class)
    fun isConnected(): Boolean {
        val command = "ping -c 1 google.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }

}