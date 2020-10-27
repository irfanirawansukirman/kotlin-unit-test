package com.irfanirawansukirman.belajarunittest

import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CalculatorTest {

    // private val calculator: Calculator = mock()

    // @Mock
    // lateinit var calculator: Calculator

    var calculator: Calculator = mock()

    @Before
    fun setupBefore() {
        // MockitoAnnotations.initMocks(this)
        // calculator = Calculator()
    }

    @Test
    fun `add function 2 tambah 2 mesti sama dengan 4`() {
        val expectedResult = 4

        `when`(calculator.add(2, 2)).thenReturn(expectedResult)

        Assert.assertEquals(expectedResult, calculator.add(2, 2))
    }
}