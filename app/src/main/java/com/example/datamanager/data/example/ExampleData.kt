package com.example.datamanager.data.example

sealed class ExampleData {
    abstract val id: Long
}

data class ExampleData1(
        override val id: Long,
        val title: String
): ExampleData()

data class ExampleData2(
        override val id: Long,
        val longTitle: String
): ExampleData()
