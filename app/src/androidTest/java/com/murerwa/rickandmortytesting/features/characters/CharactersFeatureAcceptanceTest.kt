package com.murerwa.rickandmortytesting.features.characters

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import com.murerwa.rickandmortytesting.utils.BaseUITest
import org.hamcrest.core.AllOf
import org.junit.Test
import com.murerwa.rickandmortytesting.R
import org.hamcrest.core.AllOf.*

class CharactersFeatureAcceptanceTest: BaseUITest() {
    @Test
    fun displaysAppropriatePageTitle() {
        assertDisplayed("Characters")
    }

    @Test
    fun displaysCharacterList() {
        assertRecyclerViewItemCount(R.id.recycler_characters, 20)

        onView(allOf(withId(R.id.imageView),
            isDescendantOfA(nthChildOf(withId(R.id.recycler_characters), 1))))
            .check(ViewAssertions.matches(isDisplayed()))

        onView(allOf(withId(R.id.text_character_name),
                isDescendantOfA(nthChildOf(withId(R.id.recycler_characters), 0))))
            .check(ViewAssertions.matches(withText("Rick Sanchez")))
            .check(ViewAssertions.matches(isDisplayed()))

        onView(allOf(withId(R.id.text_character_species),
                isDescendantOfA(nthChildOf(withId(R.id.recycler_characters), 0))))
            .check(ViewAssertions.matches(withText("Human")))
            .check(ViewAssertions.matches(isDisplayed()))

        onView(allOf(withId(R.id.text_character_living_status),
            isDescendantOfA(nthChildOf(withId(R.id.recycler_characters), 0))))
            .check(ViewAssertions.matches(withText("Alive")))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}