package es.vass.brastlewark.ui.brastlewark.detailgnome

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import es.vass.brastlewark.R
import es.vass.brastlewark.ui.brastlewark.BrastlewarkActivity
import es.vass.brastlewark.ui.brastlewark.gnomelist.adapter.GnomeViewHolder
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class DetailGnomeFragmentTest {
    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<BrastlewarkActivity> = ActivityScenarioRule(BrastlewarkActivity::class.java)

    @Test
    fun detailGnomeTest() {
        onView(withId(R.id.rvGnomeList))
            .perform(RecyclerViewActions.actionOnItem<GnomeViewHolder>(hasDescendant(withText("Tobus Quickwhistle")), click()));

        onView(allOf(withId(R.id.tvName), withParent(withId(R.id.clGnomeDetail)))).check(matches(withText("Tobus Quickwhistle")))
    }
}