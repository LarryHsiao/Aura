package com.silverhetch.aura.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.view.WindowManager.LayoutParams.MATCH_PARENT
import androidx.fragment.app.DialogFragment
import com.silverhetch.aura.R

/**
 * Dialog Fragment that covers the entire screen, just like normal fragment but have numbers of dialog behavior.
 *
 * Extends this class if we need a page that:
 *  - Overlaps current pages (May see thought transparent background)
 *  - Custom padding bound dialog
 *  - Only dismiss dialog by invoking [DialogFragment.dismiss] in subclass implementation.
 */
abstract class FullScreenDialogFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AuraFullScreenDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.apply {
                setBackgroundDrawableResource(android.R.color.transparent)
                attributes?.apply {
                    attributes = this
                }
                setLayout(MATCH_PARENT, MATCH_PARENT)
            }
        }
    }
}