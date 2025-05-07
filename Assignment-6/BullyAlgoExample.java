import java.io.*;
import java.util.Scanner;

class BullyAlgoExample {
    static int numberOfProcess;
    static int[] priorities = new int[100];
    static int[] status = new int[100];
    static int cord;
    static boolean[] visited = new boolean[100];

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter total number of processes:");
            numberOfProcess = sc.nextInt();

            for (int i = 0; i < numberOfProcess; i++) {
                System.out.println("Status for process " + (i + 1) + " (1 = alive, 0 = dead):");
                status[i] = sc.nextInt();
                System.out.println("Priority of process " + (i + 1) + ":");
                priorities[i] = sc.nextInt();
                visited[i] = false;  // reset visited
            }

            int ele;
            while (true) {
                System.out.println("Enter process which will initiate election:");
                ele = sc.nextInt();
                if (status[ele - 1] == 0) {
                    System.out.println("Process is dead. Please enter a live process.");
                } else
                    break;
            }

            electProcess(ele);
            System.out.println("After election, the final coordinator is process " + cord);
        } catch (Exception e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    static void electProcess(int ele) {
        ele = ele - 1;
        visited[ele] = true;
        cord = ele + 1;

        for (int i = 0; i < numberOfProcess; i++) {
            if (priorities[ele] < priorities[i] && status[i] == 1 && !visited[i]) {
                System.out.println("Election message is sent from " + (ele + 1) + " to " + (i + 1));
                electProcess(i + 1);
            }
        }
    }
}
