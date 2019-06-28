package com.silverhetch.aura.intent

import android.content.Intent
import com.silverhetch.clotho.Source

/**
 * Source to generate Intent to promote system action chooser with custom options.
 *
 * @todo #10 implement this
 */
class MultiPurposeIntent : Source<Intent> {
    override fun value(): Intent {
        return Intent()
    }
}