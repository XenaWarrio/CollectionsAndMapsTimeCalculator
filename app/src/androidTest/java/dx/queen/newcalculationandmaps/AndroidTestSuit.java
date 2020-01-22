package dx.queen.newcalculationandmaps;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dx.queen.newcalculationandmaps.ui.fragments.CollectionsAndroidTest;
import dx.queen.newcalculationandmaps.ui.fragments.MapsAndroidTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CollectionsAndroidTest.class,
        MapsAndroidTest.class
})
public class AndroidTestSuit {
}
