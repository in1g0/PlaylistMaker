package com.practicum.playlistmaker

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        val shareButton: LinearLayout = findViewById(R.id.share)
        shareButton.setOnClickListener {
            shareApp()
        }
        val supportButton: LinearLayout = findViewById(R.id.Support)
        supportButton.setOnClickListener {
            sendSupportEmail()
        }
        val licenseButton: LinearLayout = findViewById(R.id.License)
        licenseButton.setOnClickListener {
            openLicenseLink()
        }
    }

    private fun shareApp() {
        Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message))
            type = "text/plain"
            startActivity(Intent.createChooser(this, getString(R.string.share_through)))
        }
    }

    private fun sendSupportEmail() {
        val supportEmail = getString(R.string.support_email)
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(supportEmail))
            putExtra(
                Intent.EXTRA_SUBJECT,
                getString(R.string.email_theme)
            )
            putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.email_text)
            )
        }
        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(emailIntent)
        } else {
            Toast.makeText(
                this,
                getString(R.string.no_default_email_client),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun openLicenseLink() {
        val licenseUrl = getString(R.string.license_url)
        val licenseIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(licenseUrl))
        if (licenseIntent.resolveActivity(packageManager) != null) {
            startActivity(licenseIntent)
        } else {
            Toast.makeText(
                this,
                getString(R.string.no_default_browser),
                Toast.LENGTH_LONG
            ).show()
        }
    }


}