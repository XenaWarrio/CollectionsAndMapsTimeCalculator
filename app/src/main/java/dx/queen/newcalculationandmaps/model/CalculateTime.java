package dx.queen.newcalculationandmaps.model;

@Deprecated
public class CalculateTime {
    double startTime;

    public void startCalculation() {
        startTime = System.nanoTime();
    }

    public String stopCalculation() {
        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        String time = Double.toString(elapsedTime);

        return time;

    }
}
