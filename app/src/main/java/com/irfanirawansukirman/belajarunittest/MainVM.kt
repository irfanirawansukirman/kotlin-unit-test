package com.irfanirawansukirman.belajarunittest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainVM(private val testContextProvider: TestContextProvider) : ViewModel() {
    private var result = 0

    fun getResult() = result

    fun setResult() {
        viewModelScope.launch {
            result++
        }
    }

    private val _member = MutableLiveData<Member>()
    val member: LiveData<Member>
        get() = _member

    fun getMember() {
        viewModelScope.launch {
            val fakeMember = DataFactory.getMember()

            withContext(testContextProvider.Main) {
                _member.value = fakeMember
            }
        }
    }

    private val _members = MutableLiveData<List<Member>>()
    val members: LiveData<List<Member>>
        get() = _members

    fun getMembers() {
        viewModelScope.launch {
            val fakeMembers = DataFactory.getMembers()

            withContext(testContextProvider.Main) {
                _members.value = fakeMembers
            }
        }
    }
}