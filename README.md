# Queue_Management_System

The main objective of this theme is to implement an application that simulates the assignment of customers to a certain number of queues, so that the waiting time is minimized as much as possible.
## 
![image](https://user-images.githubusercontent.com/79631600/226562562-1e08aa83-ca07-434a-b698-ebd904a080ee.png)
## Implementation
In order for the application to assign customers to different queues, it is necessary for the user to enter valid data for the simulation from the simulation window that will be displayed on the screen. The data entered must be integers, with minArrivalTime less than maxArrivalTime and minServiceTime less than maxServiceTime. The user also has the option to choose the strategy by which customers are added to the queue, either based on the waiting time or the number of existing customers already queued.  
To create the graphical interface I chose to use the JavaFX tool, and the test results are written in a file.
The application is organized in several different packages, which respect the structure M-V-C (Model - View - Controller). The "Application" package contains the "App" class, which contains the main method, through which the execution of the program starts.  
As data structures, we chose to use collections that provide the concept of thread security such as BlockingQueue, Array BlockingQueue or CopyOnWriteArrayList. The AtomicInteger data type is also used for the same reasons mentioned above. In the Server class, the list of tasks on that server at a given time is stored in a BlockingQueue queue.  
In terms of customer deployment strategies, there are two types: ConcreteStrategyQueue, which distributes tasks to the freest queue, and ConcreteStrategyTime, which sends customers to the queue with the shortest waiting time at that time. These 2 classes implement the Strategy interface, with the addTask method.  

