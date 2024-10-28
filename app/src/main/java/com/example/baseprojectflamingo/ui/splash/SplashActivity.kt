package com.example.baseprojectflamingo.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Handler
import android.os.Looper
import android.text.TextPaint
import com.example.baseprojectflamingo.R
import com.example.baseprojectflamingo.commons.AppConst.TIME_DELAY_SPLASH
import com.example.baseprojectflamingo.commons.PreferencesUtils.isFirstInstall
import com.example.baseprojectflamingo.commons.base.BaseActivity
import com.example.baseprojectflamingo.databinding.ActivitySplashBinding
import com.example.baseprojectflamingo.ui.onb.OnbActivity
import com.example.baseprojectflamingo.ui.pin.auth.PinAuthenticationActivity


@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initVariables() = Unit

    override fun initView() {
        setTextAppNameColorGradient()
        delayAfterGoNextScreen()
    }

    override fun observeData() = Unit

    override fun clickViews() = Unit

    private fun setTextAppNameColorGradient() {
        val paint: TextPaint = binding.txtAppName.paint
        val width = paint.measureText(binding.txtAppName.text.toString().trim())

        val textShader: Shader = LinearGradient(
            0f, 0f, width, binding.txtAppName.textSize,
            intArrayOf(
                getColor(R.color.st_blue),
                getColor(R.color.en_pink),
            ), null, Shader.TileMode.CLAMP
        )
        binding.txtAppName.paint.shader = textShader
    }

    private fun delayAfterGoNextScreen() =
        Handler(Looper.getMainLooper()).postDelayed({
            goToNextScreen()
        }, TIME_DELAY_SPLASH)

    private fun goToNextScreen() {
        if (isFirstInstall) goToOnbScreen()
        else goToPinScreen()
    }

    private fun goToOnbScreen() {
        startActivity(Intent(this, OnbActivity::class.java))
        finish()
    }

    private fun goToPinScreen() {
        startActivity(Intent(this, PinAuthenticationActivity::class.java))
        finish()
    }
}