package com.onevgo.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

@Intercepts(value = {@Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})})
public class MyFirstPlugin implements Interceptor {
    /**
     * intercept 拦截
     * 拦截目标对象的目标方法的执行
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin:拦截 " + invocation.getMethod());
        // 动态的改变一下 sql 运行的参数，以前1号员工，实际从数据库查询3号员工
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象：" + target);
        // 拿到 StatementHandler ==> ParameterHandler ==> parameterObject
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数是：" + value);
        // 修改完sql语句要用的参数
        metaObject.setValue("parameterHandler.parameterObject", 11);
        // 执行目标方法
        Object proceed = invocation.proceed();

        return proceed;
    }

    /**
     * plugin
     * 包装目标对象，包装，为目标对象创建一个代理对象
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstPlugin:包装 " + target);
        // 我们可以记住 Plugin的wrap方法来使用当前 Interceptor 包装我们目标对象
        Object wrap = Plugin.wrap(target, this);
        // 放回为当前 target 创建的动态代理
        return wrap;
    }

    /**
     * setProperties
     * 将插件注册时的property属性设置进来
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("MyFirstPlugin:配置属性 " + properties);
    }
}
