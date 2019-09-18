package com.silverhetch.aura.intent

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.Intent.EXTRA_MIME_TYPES
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.KITKAT
import com.silverhetch.clotho.Source

/**
 * Source to generate [Intent] for picking files in given MimeType.
 *
 * ```kotlin
 * startActivity(
 *    PickerIntent(
 *      "image/ *",
 *      "audio/ *"
 *    ).value()
 * )
 * ```
 *
 * Note that the there is no guarantee that the launched app supports all the mimeType
 * we provided but the first provided is guarantee.
 */
class PickerIntent(
    private val mimeType: String,
    private vararg val mimeTypes: String
) : Source<Intent> {
    override fun value(): Intent {
        return Intent(ACTION_GET_CONTENT).also {
            it.type = mimeType

            if (SDK_INT > KITKAT) {
                it.putExtra(EXTRA_MIME_TYPES,
                    mimeTypes.toMutableList().apply {
                        add(mimeType)
                    }.toTypedArray()
                )
            }
        }
    }
}