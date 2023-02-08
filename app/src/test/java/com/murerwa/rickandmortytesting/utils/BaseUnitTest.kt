package com.murerwa.rickandmortytesting.utils

import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
open class BaseUnitTest {

    @get:Rule
    val coroutinesTestRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

}