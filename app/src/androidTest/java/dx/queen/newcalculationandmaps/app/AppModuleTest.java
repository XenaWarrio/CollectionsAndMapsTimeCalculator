package dx.queen.newcalculationandmaps.app;

import android.content.Context;

import javax.inject.Inject;

import dagger.Module;
import dx.queen.newcalculationandmaps.dagger_stuff.AppModule;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.testcalculator.TestCalculator;

@Module
public class AppModuleTest extends AppModule {
    public AppModuleTest(Context context) {
        super(context);
    }

    @Inject
    @Override
    public TimeCalculator provideTimeCalculator() {
        return new TestCalculator();
    }
}
