package Model;

import Application.App;
import BussinessLogic.SimulationManager;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod = new AtomicInteger(0);
    private Task currentTask;
    public int serverNumber;
    private boolean running;
    public Thread serverThread = new Thread();
    public Server(BlockingQueue<Task> tasks, AtomicInteger waitingPeriod) {
        this.tasks = tasks;
        this.waitingPeriod=waitingPeriod;
    }
    public Server(int maxTasksPerServer){
        serverNumber = 0;
        running = false;
        tasks = new ArrayBlockingQueue<>(maxTasksPerServer);
        waitingPeriod.set(0);
        serverThread = new Thread(this);
    }
    public Server(){
        this.tasks=new ArrayBlockingQueue<>(100);
        this.waitingPeriod=new AtomicInteger();
    }

    public BlockingQueue<Task> getTask() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }
    public void addTask(Task newTask){
        tasks.add(newTask);
    }

    public void printStatus(){
        App.log("Server [" + serverNumber + "] ");
        if(currentTask !=null){
            App.log("< id: " + currentTask.getID() + "; time left: " + currentTask.getServiceTime() + "(s), arrived at: "+ currentTask.getArrivalTime() +" >  In queue: ");
        } else{
            App.log("< closed > ");
        }
        for(Task task: tasks){
            App.log("task: "+ task.getID() + ", ");
        }
        App.log("\n");
    }

    @Override
    public void run() {
        while (running || tasks.size()>0){
            try {
                currentTask = null;
                currentTask = tasks.take();
                while(currentTask.getServiceTime() > 0){
                    Thread.sleep(1000);
                    currentTask.setServiceTime(currentTask.getServiceTime()-1);
                    waitingPeriod.decrementAndGet();
                }

                SimulationManager.statistics.decrementWaitingTasks();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getWaitingTime() {
        int result = 0;
        for (Task task : tasks) {
            result += task.getServiceTime();
        }
        if(currentTask!=null){
            return result + currentTask.getServiceTime();
        } else return result;
    }

    public List<Task> getTasks(){
        return tasks.stream().toList();
    }

    public void setServerNumber(int serverNumber) {
        this.serverNumber = serverNumber;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}