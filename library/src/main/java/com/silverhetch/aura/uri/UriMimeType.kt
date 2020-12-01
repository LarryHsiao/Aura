package com.silverhetch.aura.uri

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import com.larryhsiao.clotho.Source
import com.larryhsiao.clotho.file.FileMimeType
import java.io.File
import java.net.URI
import java.util.*

/**
 * Source to generate mime type from uri.
 *
 * This will be empty string if no match found.
 */
class UriMimeType(
    private val context: Context,
    private val url: String
) : Source<String> {
    companion object {
        private const val UNRECOGNIZED_MIME_TYPE = "content/unknown"
    }

    override fun value(): String {
        return if (url.startsWith(ContentResolver.SCHEME_CONTENT)) {
            val cr = context.contentResolver
            cr.getType(Uri.parse(url)) ?: UNRECOGNIZED_MIME_TYPE
        } else {
            if (url.startsWith("file")) {
                FileMimeType(File(URI.create(url))).value()
            } else {
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    MimeTypeMap.getFileExtensionFromUrl(url).toLowerCase(Locale.US)
                ) ?: UNRECOGNIZED_MIME_TYPE
            }
        }
    }
}