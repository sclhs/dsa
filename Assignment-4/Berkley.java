import java.io.*;
import java.time.LocalTime;

public class Berkley {

    // Calculate time difference in seconds
    int getTimeDiffInSeconds(LocalTime server, LocalTime node) {
        return server.toSecondOfDay() - node.toSecondOfDay();
    }

    // Average time difference
    int calculateAverage(int[] diffs, int n) {
        int sum = 0;
        for (int d : diffs) sum += d;
        return sum / (n + 1); // Including server
    }

    // Adjust time by seconds and return updated LocalTime
    LocalTime adjustTime(LocalTime time, int seconds) {
        return time.plusSeconds(seconds);
    }

    public static void main(String[] args) throws IOException {
        Berkley b = new Berkley();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter number of nodes: ");
        int n = Integer.parseInt(br.readLine());

        LocalTime serverTime = LocalTime.now();
        LocalTime[] nodeTimes = new LocalTime[n];
        int[] diffs = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter time for Node " + (i + 1));
            System.out.print("Hours: ");
            int h = Integer.parseInt(br.readLine());
            System.out.print("Minutes: ");
            int m = Integer.parseInt(br.readLine());
            System.out.print("Seconds: ");
            int s = Integer.parseInt(br.readLine());
            nodeTimes[i] = LocalTime.of(h, m, s);
        }

        System.out.println("\nServer time: " + serverTime);
        for (int i = 0; i < n; i++) {
            diffs[i] = b.getTimeDiffInSeconds(serverTime, nodeTimes[i]);
            System.out.println("Node " + (i + 1) + " reported diff: " + diffs[i] + " sec");
        }

        int avg = b.calculateAverage(diffs, n);
        System.out.println("\nAverage time difference: " + avg + " sec");

        // Sync all times
        LocalTime syncedServerTime = b.adjustTime(serverTime, avg);
        System.out.println("\nSynchronized Times:");
        System.out.println("Server -> " + syncedServerTime);

        for (int i = 0; i < n; i++) {
            LocalTime syncedNode = b.adjustTime(nodeTimes[i], avg + diffs[i]);
            System.out.println("Node " + (i + 1) + " -> " + syncedNode);
        }
    }
}