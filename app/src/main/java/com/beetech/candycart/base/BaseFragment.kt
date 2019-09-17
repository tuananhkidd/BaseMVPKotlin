package com.beetech.candycart.base


import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beetech.candycart.custom.CarBookingLoadingDialog
import com.beetech.candycart.util.MyApp

abstract class BaseFragment<T : BasePresenter> : androidx.fragment.app.Fragment(), BaseView {
    protected var mPresenter: T? = null
    protected lateinit var mRootView: View
    protected var mContext: Context? = null
    abstract fun getPresenter(): T
    var loadingDialog: CarBookingLoadingDialog? = null
    var lastClickTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        extractParameter(bundle)
        mPresenter = getPresenter()
        loadingDialog = CarBookingLoadingDialog.getInstance(mContext!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(layoutRes, container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter?.start()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }
    protected abstract val layoutRes: Int

    override fun showLoading() {
        loadingDialog!!.show()
    }

    override fun hideLoading() {
        loadingDialog!!.hidden()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mPresenter != null) {
            mPresenter?.destroy()
        }
    }

    protected fun avoidDuplicateClick(): Boolean {
        val now = SystemClock.elapsedRealtime()
        if (now - lastClickTime < MyApp.CLICK_TIME_INTERVAL) {
            return true
        }
        lastClickTime = now
        return false
    }

    fun extractParameter(bundle: Bundle?) {

    }

}