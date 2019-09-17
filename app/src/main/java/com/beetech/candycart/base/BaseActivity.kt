package com.beetech.candycart.base


import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.beetech.candycart.R
import com.beetech.candycart.custom.CarBookingLoadingDialog
import com.beetech.candycart.extension.gone
import com.beetech.candycart.extension.visible
import com.beetech.candycart.util.MyApp


abstract class BaseActivity<T : BasePresenter> : AppCompatActivity(),
    BaseView {
    var toolbar: View? = null
    var leftButton: ImageView? = null
    var rightButton: ImageView? = null
    var toolbarTitle: TextView? = null
    var toolbarTitleImage: ImageView? = null
    var loadingDialog: CarBookingLoadingDialog? = null
    var lastClickTime = System.currentTimeMillis()

    protected var mPresenter: T? = null
    protected abstract val layoutRes: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        loadingDialog = CarBookingLoadingDialog.getInstance(this)
        initToolbar()
        mPresenter = getPresenter()
        mPresenter?.start()
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun initToolbar() {
//        toolbar = findViewById(R.id.toolbar)
//        leftButton = findViewById(R.id.btn_left)
//        rightButton = findViewById(R.id.btn_right)
//        toolbarTitle = findViewById(R.id.toolbar_title)
//        leftButton?.let { it.setOnClickListener { onMenuLeftClick() } }
//        rightButton?.let { it.setOnClickListener { onButtonRightClick() } }
//        toolbarTitleImage = findViewById(R.id.imv_title)
    }

    protected fun avoidDuplicateClick(): Boolean {
        val now = System.currentTimeMillis()
        if (now - lastClickTime < MyApp.CLICK_TIME_INTERVAL) {
            return true
        }
        lastClickTime = now
        return false
    }

    protected open fun onMenuLeftClick() {
        this.finish()
    }

    protected open fun hideAllToolbar() {
        leftButton!!.gone()
        rightButton!!.gone()
        toolbarTitle!!.gone()
        toolbarTitleImage!!.gone()
    }

    abstract fun getPresenter(): T
    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.destroy()
    }


    override fun showLoading() {
        loadingDialog!!.show()
    }

    override fun hideLoading() {
        loadingDialog!!.hidden()
    }

    open fun setTitleToolbar(title: String) {
        toolbarTitle?.let { it.text = title }
    }

    open fun setTitleToolbar(title: Int) {
        toolbarTitle?.let { it.text = getString(title) }
    }

    open fun setImageRightToolbar(src: Int) {
        rightButton?.visible()
        rightButton?.setImageResource(src)
    }

    open fun hideImageRightToolbar() {
        rightButton?.visibility = View.GONE
    }

    open fun onButtonRightClick() {

    }

    fun startActivityAndClearTask(cls: Class<*>) {
        startActivityAndClearTask(Intent(this, cls))
    }

    protected fun startActivityAndClearTask(intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun startActivityAndClearTaskTop(intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun startActivityAndClearReorder(intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    fun startNewActivityAndClearOldActivity(activity: Activity, intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
        activity.finish()
    }

    fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun sessionLoginExpired(refres: RefreshTokenFailEvent) {
//        DialogUtil.showMessageDialog(this, (R.string.session_login_expired), DialogInterface.OnClickListener { p0, p1 ->
//            CarBookingSharePreference.clearAllPreference()
//            FirebaseMessaging.getInstance().unsubscribeFromTopic("user_" + CarBookingSharePreference.getUserId())
//            FirebaseMessaging.getInstance().unsubscribeFromTopic("driver_" + CarBookingSharePreference.getUserId())
//            startActivityAndClearTask(LoginActivity::class.java)
//        })
//    }

    open protected fun getTrackingScreenName(): String? {
        return null
    }
}