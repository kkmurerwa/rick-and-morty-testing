package com.murerwa.rickandmortytesting.features.locations

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.utils.BaseUITest
import org.hamcrest.core.AllOf.allOf
import org.junit.Test

class LocationDetailsFeatureAcceptanceTest: BaseUITest() {
    @Test
    fun displaysAppropriatePageTitle() {
        navigateToLocationDetailsScreen()

        assertDisplayed("Location Details")
        assertNotDisplayed("Characters")
        assertNotDisplayed("Locations")
    }

    @Test
    fun displaysLocationDetails() {
        navigateToLocationDetailsScreen()

        onView(withId(R.id.text_view_location_name))
            .check(matches(withText("Earth (C-137)")))
            .check(matches(isDisplayed()))

        onView(withId(R.id.text_view_location_type))
            .check(matches(withText("Planet")))
            .check(matches(isDisplayed()))

        onView(withId(R.id.text_view_location_dimension))
            .check(matches(withText("Dimension C-137")))
            .check(matches(isDisplayed()))

        onView(withId(R.id.text_view_location_residents))
            .check(matches(withText("27 residents")))
            .check(matches(isDisplayed()))

        onView(withId(R.id.text_view_location_created))
            .check(matches(withText("2017-11-10T12:42:04.162Z")))
            .check(matches(isDisplayed()))

    }

    private fun navigateToLocationDetailsScreen() {
        onView(withId(R.id.locations_fragment))
            .perform(click())
        onView(allOf(withId(R.id.text_location_name),
                isDescendantOfA(nthChildOf(withId(R.id.recycler_locations), 0))))
            .perform(click())
    }
}