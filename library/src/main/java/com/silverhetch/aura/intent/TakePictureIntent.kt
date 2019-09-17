package com.silverhetch.aura.intent

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import com.silverhetch.clotho.Source

/**
 * Source to build [Intent] that takes picture
 */
class TakePictureIntent(private val tempFileUri: Uri) : Source<Intent> {
    override fun value(): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            it.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri)
        }
    }
}