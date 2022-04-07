package BussinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t, int simTime) {
        //TODO Auto-generated method stub
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        Server serverMinTimeQueue = null;
        for (Server server : servers) {
            if (min.intValue() > server.getWaitingTime()) {
                min.set(server.getWaitingPeriod().intValue());
                serverMinTimeQueue = server;
            }
        }
        SimulationManager.statistics.addWaitingTime(min.intValue());
        SimulationManager.statistics.incrementWaitingTasks(simTime);

        serverMinTimeQueue.addTask(t);
        AtomicInteger time=new AtomicInteger();
        time.addAndGet(t.getServiceTime()+ serverMinTimeQueue.getWaitingPeriod().intValue());
        serverMinTimeQueue.setWaitingPeriod(time);
    }
}
