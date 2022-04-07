package BussinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t, int simTime){
        //TODO Auto-generated method stub
        int minNrTasks = Integer.MAX_VALUE;
        Server serverMinTask = new Server();
        for (Server server:servers) {
            if(minNrTasks>server.getTasks().size()){
                minNrTasks = server.getTasks().size();
                serverMinTask = server;
            }
        }
        SimulationManager.statistics.addWaitingTime(serverMinTask.getWaitingTime());
        SimulationManager.statistics.incrementWaitingTasks(simTime);

        serverMinTask.addTask(t);
        AtomicInteger time=new AtomicInteger();
        time.addAndGet(t.getServiceTime()+ serverMinTask.getWaitingPeriod().intValue());
        serverMinTask.setWaitingPeriod(time);
    }
}
