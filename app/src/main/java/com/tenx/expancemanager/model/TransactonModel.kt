package com.tenx.expancemanager.model

import java.util.Date

class TransactonModel(
    val img: Int,
    val amount: Int,
    val categoryName: String,
    val date: Date,
    val paymentType: Int
)