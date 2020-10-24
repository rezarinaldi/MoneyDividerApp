package com.rezarinaldi.moneydividerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoneyViewModel : ViewModel() {

    // variables corresponding to the editText values. any change in editText is directly linked here
    val jumlahOrang1 = MutableLiveData<String>()
    val jumlahOrang2 = MutableLiveData<String>()

    //as noValue is private, thus another public variable is used to return it's value
    // it's made private because it is MutableLiveData, i.e, the data can be editable
    private var noValue = MutableLiveData<String>()
    val noValueToast: LiveData<String>
        get() = noValue

    private var diff = MutableLiveData<Double>()
    val difference: LiveData<Double>
        get() = diff

    //this method is directly called from the button onClick
    fun hitung() {
        if (jumlahOrang1.value == "" || jumlahOrang2.value == "") {
            noValue.value = "NONE"
        } else {
            diff.value = (jumlahOrang1.value!!.toDouble().minus(jumlahOrang2.value!!.toDouble())).div(2)
        }
    }
}