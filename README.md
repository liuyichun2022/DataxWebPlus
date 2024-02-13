# DataXWebPlus
DataX本身作为离线数据同步框架，采用Framework + plugin架构构建。将数据源读取和写入抽象成为Reader/Writer插件，纳入到整个同步框架中。

Reader：Reader为数据采集模块，负责采集数据源的数据，将数据发送给Framework。
Writer： Writer为数据写入模块，负责不断向Framework取数据，并将数据写入到目的端。
Framework：Framework用于连接reader和writer，作为两者的数据传输通道，并处理缓冲，流控，并发，数据转换等核心技术问题。
![img.png](img.png)