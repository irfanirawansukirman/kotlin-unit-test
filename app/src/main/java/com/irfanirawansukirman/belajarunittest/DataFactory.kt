package com.irfanirawansukirman.belajarunittest

data class Member(val name: String, val status: String, val job: String)

object DataFactory {

    fun getMembers() = mutableListOf<Member>().apply {
        add(Member("Umar", "Single", "Coach"))
    }

    fun getMember() = Member("Ustman", "Single", "Pilot")
}