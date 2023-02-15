package com.murerwa.rickandmortytesting.features.characters

import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.murerwa.rickandmortytesting.utils.BaseUITest
import org.junit.Test

class CharactersFeatureAcceptanceTest: BaseUITest() {
    @Test
    fun displaysAppropriatePageTitle() {
        assertDisplayed("Characters")
    }
}