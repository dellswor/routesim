# routesim
A simple JAVA simulator for playing with implementing routing logic

# Components
RouteSim tries to follow a very physical and simple analogue for how network routing is configured/done. Users of RouteSim will implement routing Algorithms and then write their own simulation class (e.g. Sim1.java) to configure and run a simulation.
The simulator is responsible for configuring the Network to simulate. A network is composed of Nodes that are connected by Cables. After the nodes and cables have been connected, the simulator can then ask the network to simulate time steps. At each time step, the network informs all nodes that the simulation clock has ticked and delivers all packets currently in the network.
Two flavors of packets are in the current implementation: RoutingPackets, the packets that contain updates for algorithm instances, and DataPackets, the packets that would contain application level data. Currently, DataPackets are unused.

# Addressing
In real networks, identifiers are used to keep track of nodes. In the simulator the nodes themselves are used for addressing.
