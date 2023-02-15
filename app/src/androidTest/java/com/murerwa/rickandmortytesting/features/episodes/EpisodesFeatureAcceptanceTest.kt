package com.murerwa.rickandmortytesting.features.episodes

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.murerwa.rickandmortytesting.utils.BaseUITest
import org.hamcrest.core.AllOf
import org.junit.Test
import com.murerwa.rickandmortytesting.R

class EpisodesFeatureAcceptanceTest: BaseUITest() {
    @Test
    fun displaysAppropriatePageTitle() {
        Espresso.onView(withId(R.id.episodes_fragment))
            .perform(ViewActions.click())

        assertDisplayed("Episodes")
        assertNotDisplayed("Characters")
    }
}