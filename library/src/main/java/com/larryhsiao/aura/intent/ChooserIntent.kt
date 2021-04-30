package com.larryhsiao.aura.intent

import android.content.Context
import android.content.Intent
import com.larryhsiao.clotho.Source
import java.lang.IllegalArgumentException

/**
 * Source to generate Intent to promote system action chooser
 * with custom Intent options which may be totally different from each other.
 *
 * e.g.
 * To pick a picture, you can use this class to promote the options with this class,
 * which you will get a Intent that promotes system action chooser dialog
 * with options you provide.
 *
 * ```kotlin
 * startActivity(ChooserIntent(
 *     "Title here",
 *     Intent(MediaStore.ACTION_IMAGE_CAPTURE),
 *     Intent(ACTION_GET_CONTENT).also{
 *          it.type = "images / *"
 *     }
 * ).value())
 * ```
 *
 * @param title The title shows on the chooser dialog.
 *
 * @see Intent.createChooser
 */
class ChooserIntent(
    private val context: Context,
    private val title: String,
    private vararg val intents: Intent
) : Source<Intent> {
    override fun value(): Intent {
        if (intents.isEmpty()) {
            throw IllegalArgumentException("intents should not empty")
        }
        return constructIntent(intents.filter {
            it.resolveActivity(context.packageManager) != null
        })
    }

    private fun constructIntent(array: List<Intent>): Intent {
        return Intent.createChooser(intents[0], title).apply {
            putExtra(Intent.EXTRA_INITIAL_INTENTS, intents.copyOfRange(1, intents.size))
        }
    }
}