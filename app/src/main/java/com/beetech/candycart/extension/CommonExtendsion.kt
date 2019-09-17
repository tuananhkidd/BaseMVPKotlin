package com.beetech.candycart.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.regex.Pattern

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.inflate(res: Int, parent: ViewGroup? = null): View {
    return LayoutInflater.from(this).inflate(res, parent, false)
}

const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
const val EMAIL_TWO_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+\\.[a-z]+"
const val PHONE_PATTERN = "[0-9]{9,10}"
const val STRONG_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{7,}$"


// used for validate if the current String is an email
fun String.isValidEmail(): Boolean {
    val pattern = Pattern.compile(EMAIL_PATTERN)
    return pattern.matcher(this).matches()
}

fun String.isValidEmailTwo(): Boolean {
    val pattern = Pattern.compile(EMAIL_TWO_PATTERN)
    return pattern.matcher(this).matches()
}

fun String.isValidatePhone(): Boolean {
    val pattern = Pattern.compile(PHONE_PATTERN)
    return pattern.matcher(this).matches()
}

fun String.isStrongPass(): Boolean {
    val pattern = Pattern.compile(STRONG_PASSWORD_PATTERN)
    return pattern.matcher(this).matches()
}

fun String?.formatString(): String {
    return if (this.isNullOrEmpty()) {
        ""
    } else {
        this
    }
}