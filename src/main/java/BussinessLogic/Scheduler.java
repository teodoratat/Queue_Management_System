package BussinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers = new ArrayList<>();
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy = new ConcreteStrategyTime();

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server(maxTasksPerServer);
            server.setServerNumber(i + 1);
            server.setRunning(true);
            //create thread with the obj - made in constructor
            Thread thread = new Thread(server);
            thread.start();
            servers.add(server);
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        //apply strategy patter to instantiate the strategy corresponding to policy
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t, int simTime) {
        // call the strategy addTask method;
        strategy.addTask(servers, t, simTime);
    }

    public List<Server> getServers() {
        return servers;
    }
}