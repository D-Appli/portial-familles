package fr.dappli.portailfamilles.core.data.remote.utils

import java.io.ByteArrayOutputStream
import java.io.InputStream

fun readBinaryFileFromResources(fileName: String): ByteArray {
    var inputStream: InputStream? = null
    val byteStream = ByteArrayOutputStream()
    try {
        inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName)

        var nextValue = inputStream?.read() ?: -1

        while (nextValue != -1) {
            byteStream.write(nextValue)
            nextValue = inputStream?.read() ?: -1
        }
        return byteStream.toByteArray()
    } finally {
        inputStream?.close()
        byteStream.close()
    }
}
