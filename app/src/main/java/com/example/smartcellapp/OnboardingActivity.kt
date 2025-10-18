package com.example.smartcellapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: Button
    private lateinit var btnSkip: Button
    private lateinit var dotsLayout: LinearLayout
    private lateinit var adapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        btnNext = findViewById(R.id.btnNext)
        btnSkip = findViewById(R.id.btnSkip)
        dotsLayout = findViewById(R.id.layoutDots)

        // Lista de las 3 páginas
        val items = listOf(
            OnboardingItem(R.drawable.img_cursos, "", ""),
            OnboardingItem(R.drawable.img_profesores, "", ""),
            OnboardingItem(R.drawable.img_redes, "", "")
        )

        adapter = OnboardingAdapter(items)
        viewPager.adapter = adapter

        // Inicializar dots
        addDots(0)

        // Actualizar dots al deslizar
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                addDots(position)
            }
        })

        // Botón siguiente
        btnNext.setOnClickListener {
            if (viewPager.currentItem + 1 < adapter.itemCount) {
                viewPager.currentItem++
            } else {
                irAlInicio()
            }
        }

        // Botón omitir
        btnSkip.setOnClickListener {
            irAlInicio()
        }
    }

    private fun addDots(currentPosition: Int) {
        dotsLayout.removeAllViews()
        val dots = Array(adapter.itemCount) { ImageView(this) }

        for (i in dots.indices) {
            dots[i] = ImageView(this)
            if (i == currentPosition) {
                dots[i].setImageResource(R.drawable.dot_active)
            } else {
                dots[i].setImageResource(R.drawable.dot_inactive)
            }

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dotsLayout.addView(dots[i], params)
        }
    }

    private fun irAlInicio() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

