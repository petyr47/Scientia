package com.aneke.peter.scientia.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.util.*

@RunWith(JUnit4::class)
class PrefStoreTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var prefStore: PrefStore


    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)

    }


    @Test
    fun testThatSholdRefreshDataWouldReturnTrueIfLastSyncTimeIsZero(){
        Mockito.`when`(prefStore.lateSyncTime).thenReturn(
            0
        ).then {
            assertTrue(prefStore.shouldRefreshData())
        }
    }


    @Test
    fun testThatSholdRefreshDataWouldReturnFalseIfLastSyncTimeIsNow(){
        Mockito.`when`(prefStore.lateSyncTime).thenReturn(
            Calendar.getInstance().timeInMillis
        ).then {
            assertFalse(prefStore.shouldRefreshData())
        }
    }

    @Test
    fun testThatSholdRefreshDataWouldReturnFalseIfLastSyncTimeIsTenMinutesBeforeNow(){
        Mockito.`when`(prefStore.lateSyncTime).thenReturn(
            Calendar.getInstance().timeInMillis - (10 * 60 * 1000)
        ).then {
            assertFalse(prefStore.shouldRefreshData())
        }
    }


    @Test
    fun testThatSholdRefreshDataWouldReturnTrueIfLastSyncTimeIsOneDayBeforeNow(){
        Mockito.`when`(prefStore.lateSyncTime).thenReturn(
            Calendar.getInstance().timeInMillis - (24 * 60 * 60 * 1000)
        ).then {
            assertTrue(prefStore.shouldRefreshData())
        }
    }




}