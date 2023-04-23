/**
 *
 * Stores low and high temperature data for a city/state 
 */
public class WeatherData {
    private String state;
    private int lowTemperature;
    private int highTemperature;

    public WeatherData(String state, String high, String low) {
        this.state = state;
        this.lowTemperature = Integer.parseInt(low);
        this.highTemperature = Integer.parseInt(high);;
    }

    public String getState() {
        return this.state;
    }

    public int getHigh() {
        return this.highTemperature;
    }

    public int getLow() {
        return this.lowTemperature;
    }
}