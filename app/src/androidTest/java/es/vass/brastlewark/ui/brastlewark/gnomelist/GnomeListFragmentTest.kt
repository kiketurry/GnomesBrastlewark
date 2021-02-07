package es.vass.brastlewark.ui.brastlewark.gnomelist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import es.vass.brastlewark.R
import es.vass.brastlewark.ui.brastlewark.BrastlewarkActivity
import org.junit.Rule
import org.junit.Test

class GnomeListFragmentTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<BrastlewarkActivity> = ActivityScenarioRule(BrastlewarkActivity::class.java)

    @Test
    fun filterGnomeListTest() {
        onView(withId(R.id.etSearch)).perform(typeText("malbert tink"))
        Thread.sleep(1000)
        onView(withId(R.id.rvGnomeList)).check(RecyclerViewItemCountAssertion(6))
    }

}