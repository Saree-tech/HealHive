package com.example.healhive

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: MaterialButton
    private lateinit var btnSkip: MaterialButton
    private lateinit var tabLayout: TabLayout

    private val onboardingItems = listOf(
        OnboardingItem(
            title = "Check Symptoms Instantly",
            description = "Detect possible conditions using AI.",
            image = R.drawable.symptom
        ),
        OnboardingItem(
            title = "Track Your Health Daily",
            description = "Monitor your vitals and progress.",
            image = R.drawable.vitals
        ),
        OnboardingItem(
            title = "Get AI-Powered Recommendations",
            description = "Receive personalized health tips.",
            image = R.drawable.ai_recomends
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.onboardingViewPager)
        btnNext = findViewById(R.id.btnNext)
        btnSkip = findViewById(R.id.btnSkip)
        tabLayout = findViewById(R.id.tabLayout)

        val adapter = OnboardingAdapter(onboardingItems)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btnNext.text = if (position == onboardingItems.size - 1) "Get Started" else "Next"
            }
        })

        btnNext.setOnClickListener {
            if (viewPager.currentItem + 1 < onboardingItems.size) {
                viewPager.currentItem += 1
            } else {
                finishOnboarding()
            }
        }

        btnSkip.setOnClickListener {
            finishOnboarding()
        }
    }

    private fun finishOnboarding() {
        // Directly go to MainActivity for testing
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}

data class OnboardingItem(
    val title: String,
    val description: String,
    val image: Int
)
