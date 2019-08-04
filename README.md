# customdubbo-docker
customdubbo-docker

自定义的dubbo及使用docker数据容器作为注册中心

dockerfile镜像制作如下：
1.dockerfile内容
#FROM指定基础镜像：表示以该镜像为基础搭建镜像
FROM daocloud.io/library/java

# MAINTAINER：维护者信息
MAINTAINER alexfeng "346364384@qq.com"

# 复制JDK环境
RUN  echo '将当前路径下的jdk1.8.0_191文件复制到镜像根目录下'
COPY jdk1.8.0_191 jdk1.8.0_191

# jar包拷贝  -WORKDIR：工作目录，类似于shell命令中的cd
RUN  echo '从根目录下切换/var/opt/目录，同时将当前目录下的lib文件夹复制到镜像容器opt目录下'
WORKDIR /var/opt
ADD lib jarlib

# 配置环境变量
#ENV JAVA_HOME=./jdk1.8.0_191
ENV JAVA_JAR=/var/opt/jarlib
#ENV PATH=$JAVA_HOME/bin:$PATH
ENV CLASSPATH=.:$JAVA_JAR/customdubbo-1.0-SNAPSHOT.jar:$JAVA_JAR/*

# 端口设置
EXPOSE 8081

#挂卷设置
#VOLUME ["/data1"]

# 执行命令，注意一个Dockefile文件只能有一个ENTRYPOINT，可以有多个CMD
RUN echo '启动一个服务消费者'
CMD java com.feng.rpc.consumer.Comsumer

#RUN echo '启动一个服务提供着'
#CMD java com.feng.rpc.provider.Provider
#ENTRYPOINT java com.feng.rpc.provider.Provider
#ENTRYPOINT java -classpath .:$JAVA_JAR/* com.feng.rpc.provider.Provider
#ENTRYPOINT ["java","-classpath","/jarlib/*","com.feng.rpc.provider.Provider"]

2. 镜像制作：
服务提供者镜像：docker build -t alexfeng/customdubboserver .
服务消费者镜像：docker build -t alexfeng/customdubboconsumer .

3.容器启动
服务提供者容器启动：docker run --name provider -it -d -p 8080:8080 alexfeng/customdubboserver
服务提供者消费者：docker run --name consumer -it --volumes-from provider alexfeng/customdubboconsumer
