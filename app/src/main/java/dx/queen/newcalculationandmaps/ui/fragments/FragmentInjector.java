package dx.queen.newcalculationandmaps.ui.fragments;


import dx.queen.newcalculationandmaps.dto.Modes;
import dx.queen.newcalculationandmaps.model.calculator.TimeCalculatorImpl;
import dx.queen.newcalculationandmaps.model.supplier.CollectionsTasksSupplier;
import dx.queen.newcalculationandmaps.model.supplier.MapsTasksSupplier;

class FragmentInjector {

    static CollectionsPresenter createPresenter(String mode) {
        return new CollectionsPresenter(Modes.COLLECTIONS.equals(mode) ?
                new CollectionsTasksSupplier() : new MapsTasksSupplier(), new TimeCalculatorImpl());
    }
}
