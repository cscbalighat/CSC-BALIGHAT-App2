package com.cscbalighat.app

import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.util.Locale

class MainActivity : Activity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this, this)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 28, 28, 28)
            setBackgroundColor(0xFFF4F8F6.toInt())
        }
        val title = TextView(this).apply {
            text = "CSC BALIGHAT App 2"
            textSize = 26f
            gravity = Gravity.CENTER
            setTextColor(0xFF075E42.toInt())
        }
        status = TextView(this).apply {
            text = "নমস্কাৰ! CSC BALIGHAT-লৈ স্বাগতম।"
            textSize = 18f
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 24)
        }
        val speak = Button(this).apply {
            text = "অসমীয়াত শুনক"
            setOnClickListener {
                val message = "নমস্কাৰ! CSC BALIGHAT এপলৈ স্বাগতম।"
                status.text = message
                tts?.speak(message, TextToSpeech.QUEUE_FLUSH, null, "welcome")
            }
        }
        val info = TextView(this).apply {
            text = "প্ৰাথমিক সংস্কৰণ 1.0\nHey CSC wake word আৰু PIN recovery এতিয়াও যোগ কৰা হোৱা নাই।"
            textSize = 14f
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 0)
        }
        root.addView(title, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        root.addView(status, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        root.addView(speak)
        root.addView(info)
        setContentView(root)
    }

    override fun onInit(statusCode: Int) {
        if (statusCode == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale("as", "IN"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                tts?.setLanguage(Locale("en", "IN"))
            }
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}
