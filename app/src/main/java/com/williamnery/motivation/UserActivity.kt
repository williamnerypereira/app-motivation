package com.williamnery.motivation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.williamnery.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        binding = ActivityUserBinding.inflate(layoutInflater)

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_save) {
            
        }
    }
}