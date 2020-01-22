package dx.queen.newcalculationandmaps.dto;

public class CalculationResult {
    public final int labelResId;
    public final double time;
    private boolean showProgress = false;

    public CalculationResult(int labelResId, double time) {
        this.labelResId = labelResId;
        this.time = time;
    }

    public boolean isTimeDefault() {
        return -1 == time;
    }

    public double getTime() {
        return time;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public int getRes() {
        return labelResId;
    }
}
