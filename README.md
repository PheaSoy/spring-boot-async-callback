# Callback and Async Sample Solution Design
## Overview

Build the API for scale and reliable might need a lot of things required such as Deployment Process
that be able to scale, failover, Non-Blocking I/O framework, Messaging, Event-driven architecture etc.

Sometime you need to increase amount of CPU/RAM to enable scale or deploy copy of replicas if you are using containerization.
However if you build your API using Blocking IO, when the clients request thousand per second your API will be got pressure
and your system failure was increasing.

If your service call to downstream services by http protocol you're blocking process, network latency is increasing.
How we improve this process more reliable, async way and adopt with event-driven messaging. 

![high-level-design](docs/HLDS.png)

## Quick Introduce Design

![process-flow](docs/process_flow.png)
