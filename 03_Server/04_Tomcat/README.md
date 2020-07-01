# 服务器篇之Tomcat

## [catalina.home catalina.base 区别](https://blog.csdn.net/xiaohai0504/article/details/7631014)

这两个属性仅在你需要安装多个Tomcat实例而不想安装多个软件备份的时候使用，这样能节省磁盘空间。 



以Tomcat6.0为例，其Tomcat目 录结构如下：

bin (运行脚本）

conf (配置文件）

lib (核心库文件）

logs (日志目录) 

temp (临时目录)

webapps (自动装载的应用程序的目录）

work (JVM临时文件目录[java.io.tmpdir])

 

其中只有 bin 和 lib 目录被多个tomcat示例公用，其它目录conf、logs、temp、webapps和work 每个Tomcat实例必须拥有其自己独立的备份。

明白了上述关系就容易理解catalina.home和catalina.base的用途了:

catalina.home(安装目录)：指向公用信息的位置，就是bin和lib的父目录。

catalina.base(工作目录)：指向每个Tomcat目录私有信息的位置，就是conf、logs、temp、webapps和work的父目录。

仅运行一个Tomcat实例时，这两个属性指向的位置是相同的。

 

附：多实例tomcat公用工作目录实现(linux)

在一台服务器上，可以运行多个tomcat实例，不需要安装多个tomcat，可以采用不同的用户。

1、以test用户为例，拷贝/usr/local/apache-tomcat-6.0.18目录到/home/test下

2、删除/home/test/apache-tomcat-6.0.18/bin子目录（此目录不需要）

3、编辑/home/test/.bash_profile文件,设置CATALINA_HOME指向刚才的安装目录/usr/local/apache-tomcat-6.0.18

4、设置JAVA_HOME指向刚才的安装目录/usr/java/jdk1.6.0_11

5、设置CATALINA_BASE指向/home/test/apache-tomcat-6.0.18

6、设置CATALINA_OPTS跟/root/.bash_profile的一致（jmx管理端口用不同的端口号）

