/**
 * Otabek Aripdjanov
 * This is a driver class for WeatherReport class
 */

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WeatherDriver {

    public static void main(String[] args) {
        System.out.println("Starting simulation");

        String fileName = "./src/weather.txt";

        try {
            WeatherReport wr = new WeatherReport(fileName);

            long startTimeList = System.currentTimeMillis();
            List<StateTemperatureDifference> resultList = wr.computeByList();
            long endTimeList = System.currentTimeMillis();
            long durationList = endTimeList - startTimeList;

            System.out.println("\nList based computation results:");
            for (StateTemperatureDifference tempDiffByState : resultList) {
                System.out.println(tempDiffByState.getState() + ": " + tempDiffByState.getAvgTemp());
            }
            System.out.println("Time taken by computeByList: " + durationList + " milliseconds");

            long startTimeTree = System.currentTimeMillis();
            TreeMap<String, StateTemperatureDifference> resultMap = wr.computeByTree();
            long endTimeTree = System.currentTimeMillis();
            long durationTree = endTimeTree - startTimeTree;

            System.out.println("\nTreeMap based computation results:");
            for (Map.Entry<String, StateTemperatureDifference> entry : resultMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().getAvgTemp());
            }
            System.out.println("Time taken by computeByTree: " + durationTree + " milliseconds");

        } catch (Exception e) {
            System.out.println("Other error: " + e.getMessage());
        }

        System.out.println("\nFinished simulation");
    }

}
