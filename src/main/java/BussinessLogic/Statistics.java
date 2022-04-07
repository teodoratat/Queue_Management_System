package BussinessLogic;


public class Statistics {
    public int totalWaitingTime = 0;
    public float averageWaitingTime = 0;
    public int waitingTasks = 0;
    public int maxWaitingTasks = 0;
    public int peakHour = 0;
    public float averageServiceTime = 0;

    public Statistics(){
    }

    public String logMessage() {
        StringBuilder stringBuilder = new StringBuilder("Statistics:\n");
        stringBuilder.append("AverageTime: " + averageWaitingTime + "\n");
        stringBuilder.append("AverageServiceTime: " + averageServiceTime + "\n");
        stringBuilder.append("PeakTime: " + peakHour + "\n");
        return stringBuilder.toString();
    }

    public synchronized void addWaitingTime(int delta){
        totalWaitingTime += delta;
    }

    public synchronized void incrementWaitingTasks(int time){
        this.waitingTasks++;
        if(waitingTasks > maxWaitingTasks){
            maxWaitingTasks = waitingTasks;
            peakHour = time;
        }
    }

    public synchronized void decrementWaitingTasks(){
        this.waitingTasks--;
    }
}
