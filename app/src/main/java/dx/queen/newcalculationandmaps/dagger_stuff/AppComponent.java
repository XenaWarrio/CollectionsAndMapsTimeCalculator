package dx.queen.newcalculationandmaps.dagger_stuff;

import javax.inject.Named;

import dagger.Component;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculator;
import dx.queen.newcalculationandmaps.model.supplier.TaskSupplier;

@Component(modules = {AppModule.class})
public interface AppComponent {
    TimeCalculator provideCalculator();

    @Named("Maps")
    TaskSupplier injectMapsTaskSupplier();

    @Named("Collections")
    TaskSupplier injectColletionsTaskSupplier();

}