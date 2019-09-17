package com.silverhetch.aura.uri

import android.webkit.MimeTypeMap
import com.silverhetch.clotho.Source
import android.content.ContentResolver
import android.content.Context
import android.net.Uri

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
        private const val UNRECOGNIZED_MIME_TYPE = ""
    }

    override fun value(): String {
        return if (url.startsWith(ContentResolver.SCHEME_CONTENT)) {
            val cr = context.contentResolver
            cr.getType(Uri.parse(url)) ?: UNRECOGNIZED_MIME_TYPE
        } else {
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                MimeTypeMap.getFileExtensionFromUrl(url).toLowerCase()
            ) ?: UNRECOGNIZED_MIME_TYPE
        }
    }
}