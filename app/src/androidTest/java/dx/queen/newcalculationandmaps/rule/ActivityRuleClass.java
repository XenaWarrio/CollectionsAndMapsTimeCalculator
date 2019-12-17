package dx.queen.newcalculationandmaps.rule;

import android.app.Activity;

import androidx.test.rule.ActivityTestRule;
import dx.queen.newcalculationandmaps.AppInstance;
import dx.queen.newcalculationandmaps.model.AppComponent;
import dx.queen.newcalculationandmaps.model.AppModuleTest;
import dx.queen.newcalculationandmaps.model.DaggerAppComponent;

public class ActivityRuleClass <T extends Activity> extends ActivityTestRule<T>{

        public ActivityRuleClass(Class<T> activityClass) {
            super(activityClass);
        }

        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            final AppInstance app = AppInstance.getInstance();
            AppComponent provider = DaggerAppComponent
                    .builder()
                    .appModule(new AppModuleTest(app))
                    .build();
            app.setAppComponent(provider);
        }

    }

