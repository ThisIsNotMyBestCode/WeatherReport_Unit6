import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author John Cigas
 * Put your name and date and description here.
 */
public class WeatherReport {

    public LinkedList<WeatherData> weatherDataList = new LinkedList<WeatherData>();

    // Constructor
    public WeatherReport() {

        String example1 = "39,Birmingham,BHM,33,1/3/2016,46,32,1,0,4.33,Alabama,3,2016";
        String example2 = "39,Huntsville,HSV,32,1/3/2016,47,31,1,0,3.86,Alabama,3,2016";
        String example3 = "46,Mobile,MOB,35,1/3/2016,51,41,1,0.16,9.73,Alabama,3,2016";
        String example4 = "45,Montgomery,MGM,32,1/3/2016,52,38,1,0,6.86,Alabama,3,2016";
        String example5 = "34,Anchorage,ANC,19,1/3/2016,38,29,1,0.01,7.8,Alaska,3,2016";
        String example6 = "38,Annette,ANN,9,1/3/2016,44,31,1,0.09,8.7,Alaska,3,2016";
        String example7 = "30,Bethel,BET,9,1/3/2016,36,24,1,0.05,16.46,Alaska,3,2016";
        String example8 = "22,Bettles,BTT,2,1/3/2016,32,9,1,0.15,3.1,Alaska,3,2016";

        String[] reportline1 = example1.split(",");
        String[] reportline2 = example2.split(",");
        String[] reportline3 = example3.split(",");
        String[] reportline4 = example4.split(",");
        String[] reportline5 = example5.split(",");
        String[] reportline6 = example6.split(",");
        String[] reportline7 = example7.split(",");
        String[] reportline8 = example8.split(",");

        this.weatherDataList.add(new WeatherData(reportline1[10], reportline1[5], reportline1[6]));
        this.weatherDataList.add(new WeatherData(reportline2[10], reportline2[5], reportline2[6]));
        this.weatherDataList.add(new WeatherData(reportline3[10], reportline3[5], reportline3[6]));
        this.weatherDataList.add(new WeatherData(reportline4[10], reportline4[5], reportline4[6]));
        this.weatherDataList.add(new WeatherData(reportline5[10], reportline5[5], reportline5[6]));
        this.weatherDataList.add(new WeatherData(reportline6[10], reportline6[5], reportline6[6]));
        this.weatherDataList.add(new WeatherData(reportline7[10], reportline7[5], reportline7[6]));
        this.weatherDataList.add(new WeatherData(reportline8[10], reportline8[5], reportline8[6]));
    }

    public WeatherReport(String fname) {
        try {
            Scanner scanner = new Scanner(new File(fname));

            scanner.nextLine(); // consume the newline character

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] reportline = line.split(",");

                weatherDataList.add(new WeatherData(reportline[10], reportline[5], reportline[6]));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<StateTemperatureDifference> computeByList() {
        List<StateTemperatureDifference> resultList = new ArrayList<>();

        for (WeatherData data : this.weatherDataList) {
            boolean stateFound = false;
            String currentDataObjState = data.getState();

            // Iterate over the existing states and add the tempDiff
            for (StateTemperatureDifference tempDiffByState : resultList) {
                if (tempDiffByState.getState().equals(currentDataObjState)) {
                    tempDiffByState.addTemperatureDiffs(data.getHigh(), data.getLow());
                    stateFound = true;
                    break;
                }
            }

            // If state has not been added to the list yet, then go add it 
            if (!stateFound) {
                StateTemperatureDifference newStateTempDiff = new StateTemperatureDifference(currentDataObjState);
                newStateTempDiff.addTemperatureDiffs(data.getHigh(), data.getLow());
                resultList.add(newStateTempDiff);
            }
        }

        for (StateTemperatureDifference tempDiffByState : resultList) {
            tempDiffByState.computeAverage();
        }

        return resultList;
    }

    public TreeMap<String, StateTemperatureDifference> computeByTree() {
        TreeMap<String, StateTemperatureDifference> resultMap = new TreeMap<>();

        for (WeatherData data : weatherDataList) {
            StateTemperatureDifference stateTempDiff = resultMap.get(data.getState());

            if (stateTempDiff == null) {
                stateTempDiff = new StateTemperatureDifference(data.getState());
                resultMap.put(data.getState(), stateTempDiff);
            }

            stateTempDiff.addTemperatureDiffs(data.getHigh(),data.getLow());
        }

        for (StateTemperatureDifference stateTempDiff : resultMap.values()) {
            stateTempDiff.computeAverage();
        }

        return resultMap;
    }

}