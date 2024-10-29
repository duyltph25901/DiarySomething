package com.example.baseprojectflamingo.ui.pin.confirm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.util.Log
import com.example.baseprojectflamingo.R
import com.example.baseprojectflamingo.commons.base.BaseActivity
import com.example.baseprojectflamingo.commons.base.ext.click
import com.example.baseprojectflamingo.commons.base.ext.hide
import com.example.baseprojectflamingo.commons.base.ext.invisible
import com.example.baseprojectflamingo.commons.base.ext.setOnClickAffect
import com.example.baseprojectflamingo.commons.base.ext.show
import com.example.baseprojectflamingo.databinding.ActivityConfirmPinBinding
import com.example.baseprojectflamingo.databinding.DialogLoadingBinding
import com.example.baseprojectflamingo.ui.dialolog.LoadingDialog
import com.example.baseprojectflamingo.ui.pin.se_qs.SecurityQsActivity

class ConfirmPinActivity : BaseActivity<ActivityConfirmPinBinding>(R.layout.activity_confirm_pin) {

    private val dialogLoading: LoadingDialog by lazy {
        LoadingDialog(this)
    }

    private val TIME_DELAY_TRUE_PIN = 1000L

    private var pinCodeReceiver: String = ""
    private var pin = ""

    override fun initVariables() {
        pinCodeReceiver = intent.getStringExtra("set_pin_value") ?: ""
    }

    override fun initView() = Unit

    override fun observeData() = Unit

    override fun clickViews() {
        binding.apply {
            number1.setOnClickAffect { setPin(1) }
            number2.setOnClickAffect { setPin(2) }
            number3.setOnClickAffect { setPin(3) }
            number4.setOnClickAffect { setPin(4) }
            number5.setOnClickAffect { setPin(5) }
            number6.setOnClickAffect { setPin(6) }
            number7.setOnClickAffect { setPin(7) }
            number8.setOnClickAffect { setPin(8) }
            number9.setOnClickAffect { setPin(9) }
            number0.setOnClickAffect { setPin(0) }
            icDelete.setOnClickAffect { deletePin() }
            icDone.click {
                onConfirmPinDone(
                    onTruePin = onTruePin(),
                    onFalsePin = onFalsePin()
                )
            }
            icBack.click {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    private fun setPin(number: Int) {
        if (pin.length < 4) {
            vibrate()
            pin += number
            when (pin.length) {
                1 -> {
                    set1PassActive()
                    hideIcTick()
                }

                2 -> {
                    set2PassActive()
                    hideIcTick()
                }

                3 -> {
                    set3PassActive()
                    hideIcTick()
                }

                else -> {
                    set4PassActive()
                    showIcTick()
                }
            }
        } else Log.e("duylt", "On Set Pin Failure")
    }

    private fun deletePin() {
        if (pin.isNotEmpty()) {
            hideMessageWrongPass()
            vibrate()
            pin = pin.dropLast(1)
            when (pin.length) {
                1 -> {
                    set1PassActive()
                    hideIcTick()
                }

                2 -> {
                    set2PassActive()
                    hideIcTick()
                }

                3 -> {
                    set3PassActive()
                    hideIcTick()
                }

                else -> {
                    clearPassActive()
                    hideIcTick()
                }
            }
        }
    }

    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(125)
    }

    private fun set1PassActive() {
        binding.imgPin1.setImageResource(R.drawable.ic_pin_active)
        binding.imgPin2.setImageResource(R.drawable.ic_pin_un_active)
        binding.imgPin3.setImageResource(R.drawable.ic_pin_un_active)
        binding.imgPin4.setImageResource(R.drawable.ic_pin_un_active)
    }

    private fun set2PassActive() {
        binding.imgPin1.setImageResource(R.drawable.ic_pin_active)
        binding.imgPin2.setImageResource(R.drawable.ic_pin_active)
        binding.imgPin3.setImageResource(R.drawable.ic_pin_un_active)
        binding.imgPin4.setImageResource(R.drawable.ic_pin_un_active)
    }

    private fun set3PassActive() {
        binding.imgPin1.setImageResource(R.drawable.ic_pin_active)
        binding.imgPin2.setImageResource(R.drawable.ic_pin_active)
        binding.imgPin3.setImageResource(R.drawable.ic_pin_active)
        binding.imgPin4.setImageResource(R.drawable.ic_pin_un_active)
    }

    private fun set4PassActive() {
        binding.imgPin1.setImageResource(R.drawable.ic_pin_active)
        binding.imgPin2.setImageResource(R.drawable.ic_pin_active)
        binding.imgPin3.setImageResource(R.drawable.ic_pin_active)
        binding.imgPin4.setImageResource(R.drawable.ic_pin_active)
    }

    private fun clearPassActive() {
        binding.imgPin1.setImageResource(R.drawable.ic_pin_un_active)
        binding.imgPin2.setImageResource(R.drawable.ic_pin_un_active)
        binding.imgPin3.setImageResource(R.drawable.ic_pin_un_active)
        binding.imgPin4.setImageResource(R.drawable.ic_pin_un_active)
    }

    private fun hideIcTick() = binding.icDone.hide()

    private fun showIcTick() = binding.icDone.show()

    private fun onConfirmPinDone(
        onTruePin: () -> Unit,
        onFalsePin: () -> Unit
    ) {
        val isTruePin = pin == pinCodeReceiver
        if (isTruePin) onTruePin.invoke()
        else onFalsePin.invoke()
    }

    private fun onTruePin(): () -> Unit = {
        showLoadingDialog()
        hideMessageWrongPass()
        goToSecurityQuestionScreenAfterDelay()
    }

    private fun onFalsePin(): () -> Unit = {
        vibrateWhenWrongPass()
        showMessageWrongPass()
    }

    private fun vibrateWhenWrongPass() = vibrate()

    private fun hideMessageWrongPass() = binding.txtPinWrong.invisible()

    private fun showMessageWrongPass() = binding.txtPinWrong.show()

    private fun goToSecurityQuestionScreenAfterDelay() =
        Handler(Looper.getMainLooper()).postDelayed({
            hideLoadingDialog()
            startActivity(Intent(this, SecurityQsActivity::class.java))
            finish()
        }, TIME_DELAY_TRUE_PIN)

    private fun showLoadingDialog() = dialogLoading.show()

    private fun hideLoadingDialog() = dialogLoading.dismiss()

}