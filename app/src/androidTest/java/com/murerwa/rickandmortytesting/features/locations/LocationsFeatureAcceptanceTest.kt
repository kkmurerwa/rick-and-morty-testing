package com.murerwa.rickandmortytesting.features.locations

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.utils.BaseUITest
import org.hamcrest.core.AllOf
import org.hamcrest.core.AllOf.*
import org.junit.Test

class LocationsFeatureAcceptanceTest: BaseUITest() {
    @Test
    fun displaysAppropriatePageTitle() {
        navigateToLocationsScreen()

        assertDisplayed("Locations")
        assertNotDisplayed("Characters")
    }

    @Test
    fun displaysLocationList() {
        navigateToLocationsScreen()

        Thread.sleep(5000)

        onView(allOf(withId(R.id.text_location_name),
                isDescendantOfA(nthChildOf(withId(R.id.recycler_locations), 0))))
            .check(matches(withText("Earth (C-137)")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.text_location_type),
                isDescendantOfA(nthChildOf(withId(R.id.recycler_locations), 0))))
            .check(matches(withText("Planet")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.text_location_dimension),
                isDescendantOfA(nthChildOf(withId(R.id.recycler_locations), 0))))
            .check(matches(withText("Dimension C-137")))
            .check(matches(isDisplayed()))

    }

    private fun navigateToLocationsScreen() {
        onView(withId(R.id.locations_fragment))
            .perform(click())
    }
}