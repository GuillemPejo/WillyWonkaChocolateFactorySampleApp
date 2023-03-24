package com.guillem.sample_app_willy_wonka_chocolate_factory.ui.utils

import com.guillem.sample_app_willy_wonka_chocolate_factory.R

private const val MALE = "M"
private const val FEMALE = "F"

fun String?.toGenderName() = when(this){
    MALE -> R.string.male
    FEMALE ->  R.string.female
    else ->  R.string.unknown
}
