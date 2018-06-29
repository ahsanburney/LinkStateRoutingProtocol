# LinkStateRoutingProtocol

Introduction
The main objective of this project is to simulate Link-state routing algorithm. Our goal is to build a simulator to implement Link-state Routing Protocol which can perform two functions:
 Simulate the process of generating forward table for each router in a given network,
 Compute optimal path with least cost between any two specific routers.
In addition it should also show following functionalities:
1. Create a Network Topology.
2. Build a Forward Table.
3. Find the shortest path to destination router.
4. Modify a Topology (Change the status of the Router).
5. Find the best router for Broadcast
System Features:
Create a Network Topology:
We have an option to give our own network topology as an input. With this file the program is able to perform all expected operations. Such as evaluating cost and connections between all routers. With the help of this user defined topology file, the program carries out operations and stores the data as a matrix.
Build a Forward Table:
The program allows away to see forward table also known as connection table from each router that are in the topology. It shows us connection between source routers to all other router. We need to give the value for source router for generating connection table. The values are calculated using Dijkstra’s algorithm.
Shortest Path to Destination Router:
The program has been built to provide the functionality to calculate the shortest path between source router and destination router along with the cost of path. We need to provide the destination router to the program for calculating the shortest path.
Modify a Topology:
The program allows it user to modify the default topology what it means is that the router in the program can be deleted by using this option. We need to provide the router number which we want t0
remove from the topology. After deleting, the program shows an updated connection table and revised shortest path if the source and destination path was chosen by former last selection.
Best Router for Broadcast:
Selecting this option shows us the router which has shortest paths to all other routers in the network along with the cost. The sum of the costs from this router to all other routers will be minimum and if more than one router satisfies the criteria, then arbitrarily router is chosen.
