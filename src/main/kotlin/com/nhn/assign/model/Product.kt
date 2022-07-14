package com.nhn.assign.model

import java.lang.reflect.Constructor
import java.time.LocalDateTime

data class Product(
    val productNo: Int,
    val productName: String,
    val registerYmdt: LocalDateTime?,
    val salePrice: Int,
    var updateYmdt: LocalDateTime?,
)