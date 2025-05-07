import java.util.Scanner;

public class RingAlgorithm {
    public static void main(String[] args) {
        int thisProcessId, numProcesses, failedID;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        numProcesses = scanner.nextInt();

        while (true) {
            System.out.print("Enter the ID of the process that will start the election (between 1 and " + numProcesses + "): ");
            thisProcessId = scanner.nextInt();
            if (thisProcessId <= 0 || thisProcessId > numProcesses) {
                System.out.println("Please enter correct input");
            } else
                break;
        }

        while (true) {
            System.out.print("Enter the ID of the process which failed: ");
            failedID = scanner.nextInt();
            if (failedID <= 0 || failedID > numProcesses) {
                System.out.println("Please enter correct input");
                continue;
            } else
                break;
        }

        RingProcess[] ring = new RingProcess[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            ring[i] = new RingProcess(i + 1);
        }

        for (int i = 0; i < numProcesses; i++) {
            ring[i].setNextProcess(ring[(i + 1) % numProcesses]);
        }

        ring[thisProcessId - 1].startElection(failedID, numProcesses);
        scanner.close();
    }
}

class RingProcess {
    private int processId;
    private RingProcess nextProcess;
    private boolean isLeader;

    public RingProcess(int processId) {
        this.processId = processId;
        this.isLeader = false;
    }

    public void setNextProcess(RingProcess nextProcess) {
        this.nextProcess = nextProcess;
    }

    public void startElection(int failedID, int numProcesses) {
        System.out.println("Process " + processId + " starts the election.");

        if (isLeader) {
            System.out.println("Process " + processId + " is already the leader.");
            return;
        }

        int[] arr = new int[numProcesses];
        int i = 0;
        RingProcess currentProcess = this;

        do {
            if (currentProcess.processId != failedID) {
                arr[i++] = currentProcess.processId;
            }
            currentProcess = currentProcess.nextProcess;
        } while (currentProcess != this);

        int max = arr[0];
        for (int j = 1; j < i; j++) {
            if (arr[j] > max)
                max = arr[j];
        }

        System.out.println(max + " elected as the new leader.");
    }
}
