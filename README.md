### 🧵 Java Multithreading Learning 🥇 <br><br>

* Performance in Multithreading (2 main criteria):
    - Latency - The time to  completion of a task. Measured in time units.
    - Throughput - The amount of tasks completed in a given period. Measured in tasks/time unit.

* Small and trivial tasks are not worth breaking and running in parallel. (线程创建, 调度等都需要额外开销)
    - The longer and heavier the original task is, the more worthwhile it is to break it and run it in parallel.
