package com.williamnery.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.williamnery.motivation.infra.MotivationConstants
import com.williamnery.motivation.R
import com.williamnery.motivation.data.Mock
import com.williamnery.motivation.infra.SecurityPreferences
import com.williamnery.motivation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Esconder a barra de navegação
        supportActionBar?.hide()
        handleFilter(R.id.image_all) // Resetando imagens
        handleNextPhrase()

        // Eventos
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textUserName.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        handleUserName()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
           handleNextPhrase()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        } else if (view.id == R.id.text_user_name) {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, "")
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    private fun handleNextPhrase() {
        // Captura o idioma do dispositivo (Locale.getDefault().language)
        binding.textPhrase.text = Mock().getPhrase(categoryId, Locale.getDefault().language)
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple)) // 1
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple)) // 2
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple)) // 3

        // when tipo switch/case
        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.SUNNY
            }
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        val hello = getString(R.string.hello)
        binding.textUserName.text = "$hello, $name!"
    }

}