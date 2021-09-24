package com.eduardomaxwell.currencyconverter.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduardomaxwell.currencyconverter.data.Currency

class ActivityViewModel : ViewModel() {

    val currencyLiveData: MutableLiveData<Currency> = MutableLiveData()


}