package com.irfanirawansukirman.belajarunittest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainVMTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val memberObserver: Observer<Member> = mock()

    @Captor
    lateinit var memberCaptor: ArgumentCaptor<Member>

    private val membersObserver: Observer<List<Member>> = mock()

    @Captor
    lateinit var membersCaptor: ArgumentCaptor<List<Member>>

    private lateinit var mainVM: MainVM

    @Before
    fun setupBefore() {
        mainVM = MainVM(TestContextProvider()).apply {
            member.observeForever(memberObserver)
            members.observeForever(membersObserver)
        }
    }

    @Test
    fun `state awal dari result mesti bernilai 0`() = mainCoroutineRule.runBlockingTest {
        val expectedResult = 0

        expectedResult `should be equal to` mainVM.getResult()
    }

    @Test
    fun `result bertambah nilai satu dan result tidak boleh 0`() =
        mainCoroutineRule.runBlockingTest {
            // given
            val expectedresult = 1

            // when
            mainVM.setResult()

            // than
            expectedresult `should be equal to` mainVM.getResult()
        }

    @Test
    fun `get data pertama itu adalah ustman`() = mainCoroutineRule.runBlockingTest {
        // given
        val expectedFirstName = "Ustman"

        // when
        mainVM.getMember()

        // then
        memberCaptor.run {
            verify(memberObserver, times(1)).onChanged(capture())
            expectedFirstName shouldBeEqualTo value.name
        }
    }

    @Test
    fun `get all members and data should not null and not empty`() = mainCoroutineRule.runBlockingTest {
        // given
        val expectedMembers = DataFactory.getMembers()

        // when
        mainVM.getMembers()

        // then
        membersCaptor.run {
            verify(membersObserver, times(1)).onChanged(capture())
            expectedMembers shouldBeEqualTo value
        }
    }
}

@ExperimentalCoroutinesApi
fun MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) =
    testDispatcher.runBlockingTest { block() }