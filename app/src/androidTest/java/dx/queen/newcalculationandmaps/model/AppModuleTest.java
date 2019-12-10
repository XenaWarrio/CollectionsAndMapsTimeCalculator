package dx.queen.newcalculationandmaps.model;

import android.content.Context;

import javax.inject.Inject;

import dagger.Module;
import dx.queen.newcalculationandmaps.model.calculator.TestCalculator;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;

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
