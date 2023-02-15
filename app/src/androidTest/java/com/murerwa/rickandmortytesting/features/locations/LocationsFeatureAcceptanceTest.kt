package com.murerwa.rickandmortytesting.features.locations

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.utils.BaseUITest
import org.junit.Test

class LocationsFeatureAcceptanceTest: BaseUITest() {
    @Test
    fun displaysAppropriatePageTitle() {
        Espresso.onView(ViewMatchers.withId(R.id.locations_fragment))
            .perform(ViewActions.click())

        assertDisplayed("Locations")
        assertNotDisplayed("Characters")
    }
}