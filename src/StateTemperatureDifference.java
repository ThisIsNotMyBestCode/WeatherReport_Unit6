import java.util.ArrayList;
import java.util.List;

/**
 * Otabek Aripdjanov
 * This is a StateTemperatureDifference class
 * Used to get avg temp per state
 */

public class StateTemperatureDifference {
    private String state;
    private List<Integer> temperatureDiffs = new ArrayList<Integer>();
    private double avgTemp = 0;

    public StateTemperatureDifference(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void addTemperatureDiffs(int highTemp, int lowTemp) {
        int diff = highTemp - lowTemp;
        this.temperatureDiffs.add(diff);
    }

    public void computeAverage() {
        double sum = 0;
        for (int number : this.temperatureDiffs) {
            sum += number;
        }
        this.avgTemp = sum / this.temperatureDiffs.size();
    }

    public double getAvgTemp() {
        return this.avgTemp;
    }
}
