package dx.queen.newcalculationandmaps.mvp;

public class MvpContract {

    public interface Presenter<T extends MvpContract.View> {

        void subscribe(T view);

        void unsubscribe();
    }

    public interface View {

        void showToast(int msgResId);
    }
}
