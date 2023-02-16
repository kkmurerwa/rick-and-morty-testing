package com.murerwa.rickandmortytesting.features.episodes

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.murerwa.rickandmortytesting.utils.BaseUITest
import org.hamcrest.core.AllOf
import org.junit.Test
import com.murerwa.rickandmortytesting.R
import org.hamcrest.core.AllOf.*

class EpisodesFeatureAcceptanceTest: BaseUITest() {
    @Test
    fun displaysAppropriatePageTitle() {
        navigateToEpisodesScreen()

        assertDisplayed("Episodes")
        assertNotDisplayed("Characters")
    }

    @Test
    fun displaysEpisodeList() {
        navigateToEpisodesScreen()

        Thread.sleep(5000)

        onView(allOf(withId(R.id.text_episode_title),
            isDescendantOfA(nthChildOf(withId(R.id.recycler_episodes), 0))))
            .check(matches(withText("Pilot")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.text_episode_air_date),
            isDescendantOfA(nthChildOf(withId(R.id.recycler_episodes), 0))))
            .check(matches(withText("December 2, 2013")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.text_episode_code),
            isDescendantOfA(nthChildOf(withId(R.id.recycler_episodes), 0))))
            .check(matches(withText("S01E01")))
            .check(matches(isDisplayed()))
    }

    private fun navigateToEpisodesScreen() {
        onView(withId(R.id.episodes_fragment))
            .perform(ViewActions.click())
    }
}