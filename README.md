## 🧵 Java Multithreading Learning 🥇 <br><br>

### Performance
* Performance in Multithreading (2 main criteria):
    - Latency - The time to  completion of a task. Measured in time units.
    - Throughput - The amount of tasks completed in a given period. Measured in tasks/time unit.

* Small and trivial tasks are not worth breaking and running in parallel. (线程创建, 调度等等都需要额外开销)
    - The longer and heavier the original task is, the more worthwhile it is to break it and run it in parallel.

* We can get a speed up if we partition a problem into multiple sub-problems (less latency).

* That more threads than number of cores is counterproductive for problems that involve only computations and no blocking calls.
