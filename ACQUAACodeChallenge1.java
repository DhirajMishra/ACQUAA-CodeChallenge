import java.util.ArrayList;
import java.io.*;
class BusRoute{
    private
        int stopId;
        String stopName;
        int distFromOrigin;
    public BusRoute(int stopId, String stopName, int distFromOrigin){
        this.stopId = stopId;
        this.stopName = stopName;
        this.distFromOrigin = distFromOrigin;
    }

    public int getStopId() {
        return stopId;
    }
    public String getStopName() {
        return stopName;
    }
    public int getDistFromOrigin() {
        return distFromOrigin;
    }
}
public class ACQUAACodeChallenge1 {
    public static void main(String[] args)  throws IOException {
        ArrayList<BusRoute> busRoutesList = new ArrayList<BusRoute>();
        busRoutesList.add(new BusRoute(1, "H.S.R. Layout", 0));
        busRoutesList.add(new BusRoute(2, "Madiwala", 5));
        busRoutesList.add(new BusRoute(3, "Forum", 11));
        busRoutesList.add(new BusRoute(4, "Shanthinagara", 15));
        busRoutesList.add(new BusRoute(5, "Richmond Circle", 18));
        busRoutesList.add(new BusRoute(6, "Chancery Pavillion", 23));
        busRoutesList.add(new BusRoute(7, "Bowring Institute", 25));
        busRoutesList.add(new BusRoute(8, "Bangalore Club", 27));
        busRoutesList.add(new BusRoute(9, "Indian Express", 29));
        busRoutesList.add(new BusRoute(10, "Vasantanagara", 30));
        busRoutesList.add(new BusRoute(11, "RM Guttahalli", 33));
        busRoutesList.add(new BusRoute(12, "Mekhri Circle", 35));
        busRoutesList.add(new BusRoute(13, "Hebbala", 37));
        busRoutesList.add(new BusRoute(14, "BIAL", 62));
        // etc
        while(true){
            BufferedReader bfn = new BufferedReader(new InputStreamReader(System.in));
            String strInput = bfn.readLine();
            String[] arrOfStrInput = strInput.split(">", 0);
            int startPointDistFromOrigin = busRoutesList.get(Integer.parseInt(arrOfStrInput[0]) - 1).getDistFromOrigin();
            int endPointDistFromOrigin = busRoutesList.get(Integer.parseInt(arrOfStrInput[1]) - 1).getDistFromOrigin();
            int totalDistanceCovered = endPointDistFromOrigin - startPointDistFromOrigin;
            int ticketFare = 3;
            totalDistanceCovered -= 3;
            if(totalDistanceCovered > 0){
                if(totalDistanceCovered <= 17){
                    ticketFare += (totalDistanceCovered * 2);
                } else {
                    ticketFare += (17 * 2);
                }
                totalDistanceCovered -= 17;
            }
            if(totalDistanceCovered > 0) {
                ticketFare += totalDistanceCovered;
            }
            System.out.println(busRoutesList.get(Integer.parseInt(arrOfStrInput[0]) - 1).getStopName()+ " > " + busRoutesList.get(Integer.parseInt(arrOfStrInput[1]) - 1).getStopName() + " = Rs." + ticketFare);
        }
    }
}
