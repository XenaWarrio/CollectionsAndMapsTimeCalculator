package dx.queen.newcalculationandmaps.dagger_stuff;

import android.content.Context;

import javax.inject.Inject;

import dagger.Module;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.calculator.TestCalculator;

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
