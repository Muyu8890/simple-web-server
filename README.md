# 让事情变得简单——基于spring-boot的简单的web服务端框架
## 为什么使用simple-web-server
回忆一下我们之前的Java服务端开发是什么样子的。
1.创建数据库表
2.根据表创建实体类
3.创建一个Dao接口类
4.创建一个Dao实现类
5.创建一个Service接口类
6.创建一个Service实现类
7.创建一个Controller类
上面这些貌似没有什么问题，因为有代码生成工具啊。请接着往下看。
8.9.10.11....新增功能，Dao接口新增方法、Dao实现新增方法、Service接口新增方法、Service实现新增方法
只要有新的业务，不管业务多么简单都会一直循环上面的8.9.10.11.
要是业务修改起来包括了方法体的变动也是一步都不能少
上面这些对于有些人可能还不是问题，他说我的代码生成工具就厉害了，不光能生成类，还能直接把里面的常用方法也一起生成了
那我理解写代码生成工具的人可能是从一行代码5毛钱时代过来的，用生成的代码来骗钱呢。
说了这么多最正确的姿势应该是什么样子的呢？当然是<b>不写代码就能实现功能</b>，跟不写代码比较接近的就是<b>写少量代码实现同样的功能</b>咯！

## simple-web-server简介




