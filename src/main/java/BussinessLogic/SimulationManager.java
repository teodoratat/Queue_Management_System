
package BussinessLogic;

import Application.App;
import Model.Server;
import Model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimulationManager implements Runnable {
    public static FileWriter fileSimulator;
    public int timeLimit;   // maximum processing time - read from UI
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfTasks;
    public int minArrivalTime;
    public int maxArrivalTime;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler = new Scheduler(numberOfServers, numberOfTasks);
    private CopyOnWriteArrayList<Task> generatedTasks = new CopyOnWriteArrayList<>();
    private int simulationTime;

    public static Statistics statistics;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int numberOfServers, int numberOfTasks, int minArrivalTime, int maxArrivalTime, SelectionPolicy selectionPolicy) throws IOException {
        statistics = new Statistics();
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.numberOfServers = numberOfServers;
        this.numberOfTasks = numberOfTasks;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        generateNRandomTasks();
        try {
            fileSimulator = new FileWriter("fileSimulator.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.scheduler = new Scheduler(numberOfServers, numberOfTasks);
        this.scheduler.changeStrategy(selectionPolicy);

    }

    public void sortTasks() {
        Collections.sort(generatedTasks, (t1, t2) -> t1.getArrivalTime() - t2.getArrivalTime());
    }

    public void generateNRandomTasks() {
        for (int i = 0; i < numberOfTasks; i++) {
            Random random = new Random();
            Task task = new Task();
            int serviceTimeGenerated = (int) ((Math.random() * (maxProcessingTime - minProcessingTime)) + minProcessingTime);
            task.setServiceTime(serviceTimeGenerated);
            task.setID(i + 1);
            int arrivalTimeGenerated = (int) ((Math.random() * (maxArrivalTime - minArrivalTime)) + minArrivalTime);
            task.setArrivalTime(arrivalTimeGenerated);
            generatedTasks.add(task);
        }
        int totalServiceTime = 0;
        for(Task task:generatedTasks){
            totalServiceTime += task.getServiceTime();
        }
        statistics.averageServiceTime = (float) totalServiceTime / numberOfTasks;
        sortTasks();
    }
    @Override
    public void run() {
        App.log("Generated tasks:\n");
        for (Task task : generatedTasks) {
            App.log("ID:" + task.getID() + " service time: "+ task.getServiceTime()+ " arrival time: " + task.getArrivalTime()+ "\n");
        }
        simulationTime = 0;
        while (simulationTime < timeLimit) {
            App.log("Time: " + simulationTime + "\n");
            for (Task t : generatedTasks) {
                if (simulationTime == t.getArrivalTime()) {
                    scheduler.dispatchTask(t,simulationTime);
                    generatedTasks.remove(t);
                }
            }
            for (Server server : scheduler.getServers()) {
                server.printStatus();
            }
            App.log("\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            simulationTime++;
        }
        App.log("Finished simulation:\n");

        statistics.averageWaitingTime = (float) statistics.totalWaitingTime / numberOfTasks;
        App.log(statistics.logMessage());

        try {
            fileSimulator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

