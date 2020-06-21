# 好脑子不如烂笔头系列之java - JavaEE
===========================
1. Servlet容器：
    1. Tomcat:
        1. 配置任意目录下的Web应用程序
            1. 在Web服务器中可以配置虚拟目录，而虚拟目录所对应的真实目录可以在任何路径下。
            2. 在Tomcat服务器中，主要在 XML 配置文件中通过`<Context>`元素的设置来完成的。一个`<Context>`元素就表示一个Web应用程序，运行在特定的虚拟主机中。 
            3. `<Context>`元素是`<Host>`元素的子元素，可以在${catalina.base}/conf/server.xml文件中设置Context元素。 
            4. `<Context>`元素的常用属性 
                1. docBase: 指定Web应用程序的文档基目录或者WAR文件的路径名。可以制定目录的或WAR文件的绝对路径名，也可以指定相当于Host元素的appBase目录的路径名。该属性是必须的
                2. path: 指定Web应用程序的上下文路径。在一个特定的虚拟主机中，所有的上下文路径都必须是唯一的。如果指定一个上下文路径为空字符串(""),则定义了这个虚拟主机的默认Web应用程序，负责处理所有的没有分配给其他Web应用程序的请求
                3. reloadable: 如果设置为true，Tomcat服务器在运行时，会监视WEB-INF/classes和WEB-INF/lib目录下类的改变，如果发现有类被更新，Tomcat服务器讲自动重新加载该Web应用程序。这个特性在应用的开发阶段非常有用，但是它需要额外的运行时开销，所以在产品发布时不建议使用。该属性的默认值为false
                4. unpackWar: 如果为true，Tomcat在运行Web应用程序前将展开所有压缩的Web应用程序。默认值是true
                ```
                <Host name="localhost" appBase="webapps" unpackWars="true" autoDeploy="true" xmlValidation="false" xmlNamespaceAware="false">
                    <Context path="/test" docBase="c:/test" reloadable="true" />
                </Host>
                ```
            5. 使用docBase属性指定Web应用程序的真实路径。将属性reloadable设置为true，Tomcat在运行时会自动监测Servlet类的改动，如果发现有类被更新，Tomcat服务器将自动重新加载该Web应用程序。这样，在开发时，就不需要频繁重启Tomcat了
            6. 还可以在${catalina.base}/conf目录下依次创建Catalina/localhost目录，然后在localhost目录下为 test 这个Web应用程序建立 test.xml 文件，编辑这个文件输入以下内容 
                ```
                <Context path="/test" docBase="c:/test" reloadable="true" />
                ```
            7. 从Tomcat 5开始，不建议直接在server.xml文件中配置`<Context>`元素，因为server.xml文件作为Tomcat的主要配置文件，一旦Tomcat启动后，将不会再读取这个文件，因此无法在Tomcat服务器启动时发布Web应用程序。如果在其他地方配置`<Context>`元素，那么在Tomcat运行时，也可以发布Web应用程序。
            8. 从Tomcat 5.5开始，在${catalina.base}/conf/Catalina/localhost目录下创建XML配置文件来配置Web应用程序，Tomcat将以XML文件的文件名将作为Web应用程序的上下文路径，而不理会在`<Context>`元素的path属性中指定的上下文路径是什么。由于Tomcat 5.5之后的版本是以XML配置文件的文件名作为Web应用程序的上下文路径的，因此在配置`<Context>`元素时，可以不使用path属性。
    2. Resin
    3. J2EE服务器（如Weblogic）中也提供了内置的Servlet容器
2. javax.servlet.Servlet:
    1. Servlet容器响应客户请求的过程
        1. Servlet引擎检查是否已经装载并创建了该Servlet的实例对象。如果是，则直接执行第4步，否则，执行第2步。
        2. 装载并创建该Servlet的一个实例对象：调用该 Servlet 的构造器 
        3. 调用Servlet实例对象的init()方法。
        4. 创建一个用于封装请求的ServletRequest对象和一个代表响应消息的ServletResponse对象，然后调用Servlet的service()方法并将请求和响应对象作为参数传递进去。
        5. WEB应用程序被停止或重新启动之前，Servlet引擎将卸载Servlet，并在卸载之前调用Servlet的destroy()方法。
    2. Servlet的注册与运行 
        1. Servlet程序必须通过Servlet容器来启动运行，并且储存目录有特殊要求，通需要存储在`<WEB应用程序目录>/WEB-INF/classes/`目录中。 
        2. Servlet程序必须在WEB应用程序的web.xml文件中进行注册和映射其访问路径，才可以被Servlet引擎加载和被外界访问。
        3. 一个`<servlet>`元素用于注册一个Servlet，它包含有两个主要的子元素：`<servlet-name>`和`<servlet-class>`，分别用于设置Servlet的注册名称和Servlet的完整类名。 
        4. 一个`<servlet-mapping>`元素用于映射一个已注册的Servlet的一个对外访问路径，它包含有两个子元素：`<servlet-name>`和`<url-pattern>`，分别用于指定Servlet的注册名称和Servlet的对外访问路径。
    3. Servlet映射的细节
        1. 同一个Servlet可以被映射到多个URL上，即多个`<servlet-mapping>`元素的`<servlet-name>`子元素的设置值可以是同一个Servlet的注册名。 
        2. 在Servlet映射到的URL中也可以使用 `*` 通配符，但是只能有两种固定的格式：一种格式是 `*.扩展名`，另一种格式是以正斜杠（/）开头并以 `/*` 结尾。
            ```
            <servlet-mapping>
                <servlet-name>AnyName</servlet-name>
                <!-- 匹配 Xxx.do, Yyy.do ... -->
                <url-pattern>*.do</url-pattern>
            </servlet-mapping>
            <servlet-mapping>
                <servlet-name>AnyName</servlet-name>
                <!-- 匹配 /action/xxx, /action/yyy ... -->
                <url-pattern>/action/*</url-pattern>
            </servlet-mapping>
            ```
        3. tomcat中的servlet配置
            ```
            <servlet>
                <servlet-name>default</servlet-name>
                <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
                <init-param>
                    <param-name>debug</param-name>
                    <param-value>0</param-value>
                </init-param>
                <init-param>
                    <param-name>listings</param-name>
                    <param-value>false</param-value>
                </init-param>
                <load-on-startup>1</load-on-startup>
            </servlet>
            <!-- The mapping for the default servlet -->
            <servlet-mapping>
                <servlet-name>default</servlet-name>
                <url-pattern>/</url-pattern>
            </servlet-mapping>
        
            <servlet>
                <servlet-name>jsp</servlet-name>
                <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>
                <init-param>
                    <param-name>fork</param-name>
                    <param-value>false</param-value>
                </init-param>
                <init-param>
                    <param-name>xpoweredBy</param-name>
                    <param-value>false</param-value>
                </init-param>
                <load-on-startup>3</load-on-startup>
            </servlet>
            <!-- The mappings for the JSP servlet -->
            <servlet-mapping>
                <servlet-name>jsp</servlet-name>
                <url-pattern>*.jsp</url-pattern>
                <url-pattern>*.jspx</url-pattern>
            </servlet-mapping>
            ```
        4. 匹配优先级
            1. /a/b     精确匹配
            2. /a/*     最长前缀匹配
            3. /*       前缀匹配
            4. *.jsp    扩展名匹配
            5. /        默认匹配
    4. javax.servlet.GenericServlet
        1. 是一个 Servlet. 是 Servlet 接口和 ServletConfig 接口的实现类. 但是一个抽象类. 其中的 service 方法为抽象方法
        2. 如果新建的 Servlet 程序直接继承 GenericServlet 会使开发更简洁.
        3. 具体实现:
            1. 在 GenericServlet 中声明了一个 ServletConfig 类型的成员变量, 在 init(ServletConfig) 方法中对其进行了初始化 
            2. 利用 servletConfig 成员变量的方法实现了 ServletConfig 接口的方法
            3. 还定义了一个 init() 方法, 在 init(ServletConfig) 方法中对其进行调用, 子类可以直接覆盖 init() 在其中实现对 Servlet 的初始化. 
            4. 不建议直接覆盖 init(ServletConfig), 因为如果忘记编写 super.init(config); 而还是用了 ServletConfig 接口的方法,则会出现空指针异常. 
            5. 新建的 init(){} 并非 Servlet 的生命周期方法. 而 init(ServletConfig) 是生命周期相关的方法.
    5. javax.servlet.http.HttpServlet
        1. 是一个 Servlet, 继承自 GenericServlet. 针对于 HTTP 协议所定制. 
        2. 具体实现:
            1. 在 service() 方法中直接把 ServletRequest 和 ServletResponse 转为 HttpServletRequest 和 HttpServletResponse.
            2. 并调用了重载的 service(HttpServletRequest, HttpServletResponse)
            3. 在 service(HttpServletRequest, HttpServletResponse) 获取了请求方式: request.getMethod(). 根据请求方式有创建了 doXxx() 方法(xxx 为具体的请求方式, 比如 doGet, doPost)
3. javax.servlet.ServletConfig
    1. Servlet在有些情况下可能需要访问Servlet容器或借助Servlet容器访问外部的资源，所以，Servlet引擎需要将表示Servlet容器的对象传递给Servlet。另外，在web.xml文件中为某个Servlet设置的友好名称和初始化参数等信息也需要传递给该Servlet
    2. Servlet引擎将代表Servlet容器的对象(ServletContext)和Servlet的配置参数信息一并封装到一个称为ServletConfig的对象中，并在初始化Servlet实例对象时传递给该Servlet。ServletConfig接口则用于定义ServletConfig对象需要对外提供的方法，以便在Servlet程序中可以调用这些方法来获取有关信息。
    3. Servlet引擎调用Servlet的实例对象的init(ServletConfig config)方法将ServletConfig对象传递给Servlet。Servlet.getServletConfig()方法必须返回init(ServletConfig config)方法传递进来的这个ServletConfig对象的引用。
    4. ServletConfig接口的方法
        1. getInitParameterNames 
        2. getInitParameter 
        3. getServletName 
        4. getServletContext 
4. javax.servlet.ServletContext
    1. Servlet引擎为每个WEB应用程序都创建一个对应的ServletContext对象，ServletContext对象被包含在ServletConfig对象中，调用ServletConfig.getServletContext方法可以返回ServletContext对象的引用。
    2. 由于一个WEB应用程序中的所有Servlet都共享同一个ServletContext对象，所以，ServletContext对象被称之为 application 对象（Web应用程序对象）。  
    3. 功能：
        1. 获取WEB应用程序的初始化参数
            1. 为WEB应用程序设置初始化参数的好处在于不需要修改Servlet源程序，就可以改变一些参数信息。
            2. ServletContext.getInitParameterNames方法用于返回一个包含WEB应用程序的所有初始化参数名称的Enumeration集合对象，ServletContext.getInitParameter方法用于返回某个指定名称的初始化参数值。
            3. 在web.xml文件的根元素`<web-app>`中增加`<context-param>`子元素，如下所示：
                ```
                <context-param>
                    <param-name>driver</param-name>
                    <param-value>com.mysql.jdbc.Driver</param-value>
                </context-param>
                ```
        2. 记录日志
        3. application域范围的属性 
        4. 访问资源文件
            1. getResource(String path):
            2. getResourceAsStream(String path): path 的 / 为当前 WEB 应用的根目录，必须以"/"开头
        5. 获取虚拟路径所映射的本地路径
          
            1. getRealPath(String path) 方法: 用于返回某个虚拟路径所映射的本地文件系统路径，必须以"/"开头
        6. WEB应用程序之间的访问 
        7. ServletContext的其他方法
5. javax.servlet.http.HttpServletRequest
    1. javax.servlet.ServletRequest
        1. Servlet API 中定义的 ServletRequest 接口类用于封装请求消息。
        2. HttpServletRequest 是专用于HTTP协议的ServletRequest 子接口，它用于封装 HTTP 请求消息。
        3. 在 service() 方法内部调用 HttpServletRequest 对象的各种方法来获取请求消息。
    2. 获取请求行的相关信息
        1. HTTP请求消息的请求行包括请求方式、资源路径和HTTP协议版本：
          
            ```
            ${method:GET|POST} /${contextPath}/${servletPath}/${pathInfo}?${queryString} HTTP/1.1
            ```
            
        2. getMethod：返回HTTP请求消息中的请求方式。
        
        3. getRequestURI：返回请求行中的资源名部分。
        
        4. getQueryString ：返回请求行中的参数部分。
        
        5. getProtocol：返回请求行中的协议名和版本。
        
        6. getContextPath：返回请求资源所属于的WEB应用程序的路径。
        
        7. getPathInfo：返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
        
        8. getPathTranslated：返回URL中的额外路径信息所对应的资源的真实路径。 
        
        9. getServletPath方法：Servlet的名称或Servlet所映射的路径。
    3. 获取网络连接信息
        1. getRemoteAddr方法返回发出请求的客户机的IP地址，其格式为“192.168.0.3”这种形式的字符文本。
        2. getRemoteHost方法返回发出请求的客户机的完整主机名，即“Cliff-MBP”这种格式。
        3. getRemotePort方法返回发出请求的客户机所使用的网络接口的端口号。
        4. getLocalAddr方法返回WEB服务器上接收当前请求的网络接口的IP地址。
        5. getLocalName方法返回WEB服务器上接收当前请求的网络接口的IP地址所对应的主机名。
        6. getLocalPort方法返回WEB服务器上接收当前请求的网络接口的端口号。
        7. getServerName方法返回当前请求所指向的主机名。 
        8. getServerPort方法返回当前请求所连接的服务器端口号。
        9. getScheme方法返回请求的协议名，例如http、https或ftp。
        10. getRequestURL方法返回客户端发出请求时的完整URL。
    4. 获取请求头信息
        1. getHeader方法
        2. getHeaders方法
        3. getHeaderNames方法
        4. getIntHeader方法
        5. getDateHeader方法
        6. getContentType方法
        7. getContentLength方法
        8. getCharacterEncoding方法 
    5. 获取请求参数
        1. getParameter方法
        2. getParameterValues方法
        3. getParameterNames方法
        4. getParameterMap方法
    6. 请求域属性
        1. 存储在ServletRequest对象中的对象称之为请求域属性，属于同一个请求的多个处理模块之间可以通过请求域属性来传递对象数据。 
        2. 与请求域属性相关的方法：
            1. setAttribute方法
            2. getAttribute方法
            3. removeAttribute方法
            4. getAttributeNames方法 
6. javax.servlet.http.HttpServletResponse
    1. javax.servlet.ServletResponse
        1. Servlet API中定义的ServletResponse接口类用于创建响应消息。
        2. HttpServletResponse是专用于HTTP协议的ServletResponse子接口，它用于封装HTTP响应消息。
    2. 请求重定向与请求转发
        1. RequestDispatcher接口
        2. 用forward方法实现请求转发
        3. 请求转发的运行流程 
        4. 用sendRedirect方法实现请求重定向 
        5. 请求重定向的运行流程 
        6. 请求重定向与请求转发的比较
    3. javax.servlet.RequestDispatcher
        1. RequestDispatcher实例对象是由Servlet引擎创建的，它用于包装一个要被其他资源调用的资源（例如，Servlet、HTML文件、JSP文件等），并可以通过其中的方法将客户端的请求转发给所包装的资源。
        2. RequestDispatcher接口中定义了两个方法：forward方法和include方法。
        3. forward和include方法接收的两个参数必须是传递给当前Servlet的service方法的那两个ServletRequest和ServletResponse对象，或者是对它们进行了包装的ServletRequestWrapper 或ServletResponseWrapper对象。 
        4. 获取RequestDispatcher对象的方法：
            1. ServletContext.getRequestDispatcher （参数只能是以“/”开头的路径）
            2. ServletContext.getNamedDispatcher 
            3. ServletRequest.getRequestDispatcher （参数可以是不以“/”开头的路径）
    4. 用sendRedirect方法实现请求重定向
        1. sendRedirect 方法不仅可以重定向到当前应用程序中的其他资源，它还可以重定向到同一个站点上的其他应用程序中的资源，甚至是使用绝对URL重定向到其他站点的资源。
        2. 如果传递给sendRedirect 方法的相对URL以“/”开头，则是相对于整个WEB站点的根目录，而不是相对于当前WEB应用程序的根目录。
    5. 请求重定向与请求转发的比较
        1. RequestDispatcher.forward方法只能将请求转发给同一个WEB应用中的组件；而HttpServletResponse.sendRedirect 方法还可以重定向到同一个站点上的其他应用程序中的资源，甚至是使用绝对URL重定向到其他站点的资源。 
        2. 如果传递给HttpServletResponse.sendRedirect 方法的相对URL以“/”开头，它是相对于整个WEB站点的根目录；如果创建RequestDispatcher对象时指定的相对URL以“/”开头，它是相对于当前WEB应用程序的根目录。 
        3. 调用HttpServletResponse.sendRedirect方法重定向的访问过程结束后，浏览器地址栏中显示的URL会发生改变，由初始的URL地址变成重定向的目标URL；调用RequestDispatcher.forward 方法的请求转发过程结束后，浏览器地址栏保持初始的URL地址不变。
        4. HttpServletResponse.sendRedirect方法对浏览器的请求直接作出响应，响应的结果就是告诉浏览器去重新发出对另外一个URL的访问请求；RequestDispatcher.forward方法在服务器端内部将请求转发给另外一个资源，浏览器只知道发出了请求并得到了响应结果，并不知道在服务器程序内部发生了转发行为。 
        5. RequestDispatcher.forward方法的调用者与被调用者之间共享相同的request对象和response对象，它们属于同一个访问请求和响应过程；而HttpServletResponse.sendRedirect方法调用者与被调用者使用各自的request对象和response对象，它们属于两个独立的访问请求和响应过程。 
7. JSP:
    1. JSP的运行原理
        1. WEB容器（Servlet引擎）接收到以.jsp为扩展名的URL的访问请求时，它将把该访问请求交给JSP引擎去处理。
        2. 每个JSP 页面在第一次被访问时，JSP引擎将它翻译成一个Servlet源程序，接着再把这个Servlet源程序编译成Servlet的class类文件，然后再由WEB容器（Servlet引擎）像调用普通Servlet程序一样的方式来装载和解释执行这个由JSP页面翻译成的Servlet程序。 
        3. JSP规范也没有明确要求JSP中的脚本程序代码必须采用Java语言，JSP中的脚本程序代码可以采用Java语言之外的其他脚本语言来编写，但是，JSP页面最终必须转换成Java Servlet程序。 
        4. 可以在WEB应用程序正式发布之前，将其中的所有JSP页面预先编译成Servlet程序。
    2. JSP隐式对象
        ```
        public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
                throws java.io.IOException, javax.servlet.ServletException {
            // ...
            final javax.servlet.jsp.PageContext pageContext;
            javax.servlet.http.HttpSession session = null;
            final javax.servlet.ServletContext application;
            final javax.servlet.ServletConfig config;
            javax.servlet.jsp.JspWriter out = null;
            final java.lang.Object page = this;
            // ...
        }
        ```
        1. request: HttpServletRequest 的一个对象. *
        2. response: HttpServletResponse 的一个对象(在 JSP 页面中几乎不会调用 response 的任何方法.)
        3. pageContext: 页面的上下文, 是 PageContext 的一个对象. 可以从该对象中获取到其他 8 个隐含对象. 也可以从中获取到当前页面的其他信息. (学习自定义标签时使用它) *
        4. session: 代表浏览器和服务器的一次会话, 是 HttpSession 的一个对象. 后面详细学习. *
        5. application: 代表当前 WEB 应用. 是 ServletContext 对象. *
        6. config: 当前 JSP 对应的 Servlet 的 ServletConfig 对象(几乎不使用). 若需要访问当前 JSP 配置的初始化参数, 需要通过映射的地址才可以.
        7. out: JspWriter 对象. 调用 out.println() 可以直接把字符串打印到浏览器上. *
        8. page: 指向当前 JSP 对应的 Servlet 对象的引用, 但为 Object 类型, 只能调用 Object 类的方法(几乎不使用) 
        9. exception: 在声明了 page 指令的 isErrorPage="true" 时, 才可以使用. *
    3. 注册与配置JSP页面的访问路径
        ```
        <servlet>
            <servlet-name>SimpleJspServlet</servlet-name>
            <jsp-file>/jsp/simple.jsp</jsp-file>
        </servlet>
        <servlet-mapping>
            <servlet-name>SimpleJspServlet</servlet-name>
            <url-pattern>/xxx/yyy.html</url-pattern>
        </servlet-mapping>
        ```
    4. JSP表达式 `<%= new Date() %>`
    5. JSP脚本片断 `<% java code %>`
    6. JSP声明 `<%! public void foo(){...} %>`
    7. JSP注释 `<%-- ... --%>`
    8. 访问各个域范围中的属性
        1. 在application、session、request、pageContext对象中都可以调用setAttribute方法和getAttribute方法来设置和检索各自域范围内的属性。
        2. 存储在application对象中的属性可以被同一个WEB应用程序中的所有Servlet和JSP页面访问。
        3. 存储在session对象中的属性可以被属于同一个会话的所有Servlet和JSP页面访问。
        4. 存储在request对象中的属性可以被属于同一个请求的所有Servlet和JSP页面访问，例如使用PageContext.forward和PageContext.include方法连接起来的多个Servlet和JSP页面。    
        5. 存储在pageContext对象中的属性仅可以被当前JSP页面的当前响应过程中调用的各个组件访问，例如，正在响应当前请求的JSP页面和它调用的各个自定义标签类。 
        6. PageContext类中还提供了对各个域范围的属性进行统一管理的方法，以简化对各个域范围内的属性的访问。
    9. JSP指令
        1. JSP指令（directive）是为JSP引擎而设计的，它们并不直接产生任何可见输出，而只是告诉引擎如何处理JSP页面中的其余部分。
        2. JSP指令的基本语法格式：`<%@ 指令 属性名="值" %>`
            1. 举例：`<%@ page contentType="text/html;charset=gb2312"%>`
            2. 注意：属性名部分是大小写敏感的
        3. 在目前的JSP 2.0中，定义了page、include和taglib这三种指令，每种指令中又都定义了一些各自的属性。
        4. 如果要在一个JSP页面中设置同一条指令的多个属性，可以使用多条指令语句单独设置每个属性，也可以使用同一条指令语句设置该指令的多个属性。 
            1. 第一种方式：
                ```
                <%@ page contentType="text/html;charset=gb2312"%>
                <%@ page import="java.util.Date"%>
                ```
                
            2. 第二种方式：
              
                ```
                <%@ page contentType="text/html;charset=gb2312" import="java.util.Date"%>
                ```
        5. page指令
            1. page指令用于定义JSP页面的各种属性，无论page指令出现在JSP页面中的什么地方，它作用的都是整个JSP页面，为了保持程序的可读性和遵循良好的编程习惯，page指令最好是放在整个JSP页面的起始位置。
            2. JSP 2.0规范中定义的page指令的完整语法：
                ```
                <%@ page 
                    [ language="java" ] 
                    [ extends="package.class" ] 
                    [ import="{package.class | package.*}, ..." ] 
                    [ session="true | false" ] 
                    [ buffer="none | 8kb | sizekb" ] 
                    [ autoFlush="true | false" ] 
                    [ isThreadSafe="true | false" ] 
                    [ info="text" ] 
                    [ errorPage="relative_url" ] 
                    [ isErrorPage="true | false" ] 
                    [ contentType="mimeType [ ;charset=characterSet ]" | "text/html ; charset=ISO-8859-1" ] 
                    [ pageEncoding="characterSet | ISO-8859-1" ] 
                    [ isELIgnored="true | false" ] 
                %>
                ```
        6. include指令
            1. include指令用于通知JSP引擎在翻译当前JSP页面时将其他文件中的内容合并进当前JSP页面转换成的Servlet源文件中，这种在源文件级别进行引入的方式称之为静态引入，当前JSP页面与静态引入的页面紧密结合为一个Servlet。
            2. 语法：`<%@ include file="relativeURL"%>` 其中的file属性用于指定被引入文件的相对路径。
            3. 细节：
                1. 被引入的文件必须遵循JSP语法，其中的内容可以包含静态HTML、JSP脚本元素、JSP指令和JSP行为元素等普通JSP页面所具有的一切内容。
                2. 被引入的文件可以使用任意的扩展名，即使其扩展名是html，JSP引擎也会按照处理jsp页面的方式处理它里面的内容，为了见明知意，JSP规范建议使用.jspf（JSP fragments）作为静态引入文件的扩展名。
                3. 在将JSP文件翻译成Servlet源文件时，JSP引擎将合并被引入的文件与当前JSP页面中的指令元素（设置pageEncoding属性的page指令除外），所以，除了import和pageEncoding属性之外，page指令的其他属性不能在这两个页面中有不同的设置值。
                4. 除了指令元素之外，被引入的文件中的其他元素都被转换成相应的Java源代码，然后插入进当前JSP页面所翻译成的Servlet源文件中，插入位置与include指令在当前JSP页面中的位置保持一致。  
                5. 引入文件与被引入文件是在被JSP引擎翻译成Servlet的过程中进行合并，而不是先合并源文件后再对合并的结果进行翻译。当前JSP页面的源文件与被引入文件的源文件可以采用不同的字符集编码，即使在一个页面中使用page指令的pageEncoding或contentType属性指定了其源文件的字符集编码，在另外一个页面中还需要用page指令的pageEncoding或contentType属性指定其源文件所使用的字符集。 
                6. Tomcat 5.x在访问JSP页面时，可以检测它所引入的其他文件是否发生了修改，如果发生了修改，则重新编译当前JSP页面
                7. file属性的设置值必须使用相对路径，如果以“/”开头，表示相对于当前WEB应用程序的根目录（注意不是站点根目录），否则，表示相对于当前文件。
    10. JSP标签
        1. JSP还提供了一种称之为Action的元素，在JSP页面中使用Action元素可以完成各种通用的JSP页面功能，也可以实现一些处理复杂业务逻辑的专用功能。
        2. Action元素采用XML元素的语法格式，即每个Action元素在JSP页面中都以XML标签的形式出现。
        3. JSP规范中定义了一些标准的Action元素，这些元素的标签名都以jsp作为前缀，并且全部采用小写，例如，`<jsp:include>、<jsp:forward>`等等。
        4. `<jsp:include>`标签
            1. `<jsp:include>`标签用于把另外一个资源的输出内容插入进当前JSP页面的输出内容之中，这种在JSP页面执行时的引入方式称之为动态引入。
                1. 语法：`<jsp:include page="relativeURL | <%=expression%>" flush="true|false" />`
                2. page属性用于指定被引入资源的相对路径，它也可以通过执行一个表达式来获得。
                3. flush属性指定在插入其他资源的输出内容时，是否先将当前JSP页面的已输出的内容刷新到客户端。  
            2. `<jsp:include>`标签与include指令的比较
                1. `<jsp:include>`标签是在当前JSP页面的执行期间插入被引入资源的输出内容，当前JSP页面与被动态引入的资源是两个彼此独立的执行实体，被动态引入的资源必须是一个能独立被WEB容器调用和执行的资源。include指令只能引入遵循JSP格式的文件，被引入文件与当前JSP文件共同合被翻译成一个Servlet的源文件。
                2. 使用`<jsp:include>`标签和include指令都可以把一个页面的内容分成多个组件来生成，开发者不必再把页眉和页脚部分的相同HTML代码复制到每个JSP文件中，从而可以更轻松地完成维护工作，但是都应注意最终的输出结果内容应遵循HTML语法结构，例如，如果当前页面产生了`<html>、</html>、<body>、</body>`等标记，那么在被引入文件中就不能再输出`<html>、</html>、<body>、</body>`等标记。
                3. `<jsp:include>`标签对JSP引擎翻译JSP页面的过程不起作用，它是在JSP页面的执行期间才被调用，因此不会影响两个页面的编译。由于include指令是在JSP引擎翻译JSP页面的过程中被解释处理的，所以它对JSP引擎翻译JSP页面的过程起作用，如果多个JSP页面中都要用到一些相同的声明，那么就可以把这些声明语句放在一个单独的文件中编写，然后在每个JSP页面中使用include指令将那个文件包含进来。
                4. `<jsp:include>`标签使用page属性指定被引入资源的相对路径，而include指令使用file属性指定被引入资源的相对路径。
        5. `<jsp:forward>`标签
            1. `<jsp:forward>`标签用于把请求转发给另外一个资源。
            2. 语法：`<jsp:forward page="relativeURL | <%=expression%>" />` page属性用于指定请求转发到的资源的相对路径，它也可以通过执行一个表达式来获得。
            3. RequestDispatcher.forward方法、PageContext.forward方法、`<jsp:forward>`标签的区别
                1. 调用RequestDispatcher.forward方法的JSP脚本代码的前后不能有JSP模版内容。
                2. 调用PageContext.forward方法的JSP脚本代码的后面不能有JSP模版内容。
                3. `<jsp:forward>`标签的前后都能有JSP模版内容。
        6. `<jsp:param>`标签
            1. 当使用`<jsp:include>`和`<jsp:forward>`标签引入或将请求转发给的资源是一个能动态执行的程序时，例如Servlet和JSP页面，那么，还可以使用`<jsp:param>`标签向这个程序传递参数信息。
            2. 语法1：
                ```
                <jsp:include page="relativeURL | <%=expression%>">
                    <jsp:param name="parameterName" value="parameterValue|<%= expression %>" />
                </jsp:include>
                ```
            3. 语法2：
                ```
                <jsp:forward page="relativeURL | <%=expression%>">
                    <jsp:param name="parameterName" value="parameterValue|<%= expression %>" />
                </jsp:include>
                ```
            4. `<jsp:param>`标签的name属性用于指定参数名，value属性用于指定参数值。在`<jsp:include>`和`<jsp:forward>`标签中可以使用多个`<jsp:param>`标签来传递多个参数。
    11. JSP中文乱码
        1. 在 JSP 页面上输入中文，请求页面后不出现乱码： 
            1. 保证 contentType="text/html; charset=UTF-8", pageEncoding="UTF-8" charset 和 pageEncoding 的编码一致，且都支持中文。通常建议取值为UTF-8。
            2. 还需保证浏览器的显示的字符编码也和请求的 JSP 页面的编码一致。
        2. 获取中文参数值：默认参数在传输过程中使用的编码为 ISO-8859-1。
            1. 对于 POST 请求：只要在获取请求信息之前（在调用 request.getParameter 或者是 request.getReader 等），调用 `request.setCharacterEncoding("UTF-8");` 即可。
            2. 对于 GET 请求：前面的方式对于 GET 无效。可以通过修改 Tomcat 的 server.xml 文件的方式。
                1. 为 Connector 节点添加 `useBodyEncodingForURI="true"` 属性即可。`<Connector connectionTimeout="20000" port="8989" protocol="HTTP/1.1" redirectPort="8443" useBodyEncodingForURI="true"/>`
8. MVC
9. 会话与状态管理：
    1. Cookie机制
        1. cookie机制采用的是在客户端保持 HTTP 状态信息的方案
        2. Cookie是在浏览器访问WEB服务器的某个资源时，由WEB服务器在HTTP响应消息头中附带传送给浏览器的一个小文本文件。
        3. 一旦WEB浏览器保存了某个Cookie，那么它在以后每次访问该WEB服务器时，都会在HTTP请求头中将这个Cookie回传给WEB服务器。
        4. 底层的实现原理： WEB服务器通过在HTTP响应消息中增加Set-Cookie响应头字段将Cookie信息发送给浏览器，浏览器则通过在HTTP请求消息中增加Cookie请求头字段将Cookie回传给WEB服务器。
        5. 一个Cookie只能标识一种信息，它至少含有一个标识该信息的名称（NAME）和设置值（VALUE）。
        6. 一个WEB站点可以给一个WEB浏览器发送多个Cookie，一个WEB浏览器也可以存储多个WEB站点提供的Cookie。
        7. 浏览器一般只允许存放300个Cookie，每个站点最多存放20个Cookie，每个Cookie的大小限制为4KB。
    2. 在Servlet程序中使用Cookie
        1. Servlet API中提供了一个javax.servlet.http.Cookie类来封装Cookie信息，它包含有生成Cookie信息和提取Cookie信息的各个属性的方法。 
        2. Cookie类的方法：
            1. 构造方法： `public Cookie(String name,String value)`
            2. getName方法
            3. setValue与getValue方法 
            4. setMaxAge与getMaxAge方法
            5. setPath与getPath方法 
        3. HttpServletResponse接口中定义了一个addCookie方法，它用于在发送给浏览器的HTTP响应消息中增加一个Set-Cookie响应头字段。
        4. HttpServletRequest接口中定义了一个getCookies方法，它用于从HTTP请求消息的Cookie请求头字段中读取所有的Cookie项。
    3. Cookie的发送
        1. 创建Cookie对象
        2. 设置最大时效
        3. 将Cookie放入到HTTP响应报头
            1. 如果创建了一个cookie，并将他发送到浏览器，默认情况下它是一个会话级别的cookie; 存储在浏览器的内存中，用户退出浏览器之后被删除。若希望浏览器将该cookie存储在磁盘上，则需要使用maxAge，并给出一个以秒为单位的时间。将最大时效设为0则是命令浏览器删除该cookie。
            2. 发送cookie需要使用HttpServletResponse的addCookie方法，将cookie插入到一个 Set-Cookie　HTTP响应报头中。由于这个方法并不修改任何之前指定的Set-Cookie报头，而是创建新的报头，因此将这个方法称为是addCookie，而非setCookie。
    4. 会话cookie和持久cookie的区别
        1. 如果不设置过期时间，则表示这个cookie生命周期为浏览器会话期间，只要关闭浏览器窗口，cookie就消失了。这种生命期为浏览器会话期的cookie被称为会话cookie。会话cookie一般不保存在硬盘上而是保存在内存里。
        2. 如果设置了过期时间，浏览器就会把cookie保存到硬盘上，关闭后再次打开浏览器，这些cookie依然有效直到超过设定的过期时间。
        3. 存储在硬盘上的cookie可以在不同的浏览器进程间共享，比如两个IE窗口。而对于保存在内存的cookie，不同的浏览器有不同的处理方式。
    5. Cookie的读取
        1. 调用request.getCookies
            - 要获取浏览器发送来的cookie，需要调用HttpServletRequest的getCookies方法，这个调用返回Cookie对象的数组，对应由HTTP请求中Cookie报头输入的值。
        2. 对数组进行循环，调用每个cookie的getName方法，直到找到感兴趣的cookie为止
    6. session在不同环境下的不同含义
        1. session，中文经常翻译为会话，其本来的含义是指有始有终的一系列动作/消息，比如打电话是从拿起电话拨号到挂断电话这中间的一系列过程可以称之为一个session。
        2. session在Web开发环境下的语义又有了新的扩展，它的含义是指一类用来在客户端与服务器端之间保持状态的解决方案。有时候Session也用来指这种解决方案的存储结构。
    7. Session机制
        1. session机制采用的是在服务器端保持 HTTP 状态信息的方案 。
        2. 服务器使用一种类似于散列表的结构(也可能就是使用散列表)来保存信息。
        3. 当程序需要为某个客户端的请求创建一个session时，服务器首先检查这个客户端的请求里是否包含了一个session标识(即sessionId),如果已经包含一个sessionId则说明以前已经为此客户创建过session，服务器就按照session id把这个session检索出来使用(如果检索不到，可能会新建一个，这种情况可能出现在服务端已经删除了该用户对应的session对象，但用户人为地在请求的URL后面附加上一个JSESSION的参数)。如果客户请求不包含sessionId，则为此客户创建一个session并且生成一个与此session相关联的sessionId，这个session id将在本次响应中返回给客户端保存。
    8. 保存session id的几种方式
        1. 保存session id的方式可以采用cookie，这样在交互过程中浏览器可以自动的按照规则把这个标识发送给服务器。
        2. 由于cookie可以被人为的禁用，必须有其它的机制以便在cookie被禁用时仍然能够把session id传递回服务器，经常采用的一种技术叫做URL重写，就是把session id附加在URL路径的后面，附加的方式也有两种，一种是作为URL路径的附加信息，另一种是作为查询字符串附加在URL后面。网络在整个交互过程中始终保持状态，就必须在每个客户端可能请求的路径后面都包含这个session id。
    9. Session cookie
        1. session通过SessionID来区分不同的客户, session是以cookie或URL重写为基础的，默认使用cookie来实现，系统会创造一个名为JSESSIONID的输出cookie，这称之为session cookie,以区别persistent cookies(也就是我们通常所说的cookie),session cookie是存储于浏览器内存中的，并不是写到硬盘上的，通常看不到JSESSIONID，但是当把浏览器的cookie禁止后，web服务器会采用URL重写的方式传递Sessionid，这时地址栏看到
        2. session cookie针对某一次会话而言，会话结束session cookie也就随着消失了，而persistent cookie只是存在于客户端硬盘上的一段文本。
        3. 关闭浏览器，只会是浏览器端内存里的session cookie消失，但不会使保存在服务器端的session对象消失，同样也不会使已经保存到硬盘上的持久化cookie消失。
    10. Session的创建与删除
        1. 一个常见的错误是以为session在有客户端访问时就被创建，然而事实是直到某server端程序(如Servlet)调用HttpServletRequest.getSession(true) 或者 HttpServletRequest.getSession()这样的语句时才会被创建。
        2. session在下列情况下被删除：
            - 程序调用HttpSession.invalidate()
            - 距离上一次收到客户端发送的session id时间间隔超过了session的最大有效时间
            - 服务器进程被停止
        3. 注意：关闭浏览器只会使存储在客户端浏览器内存中的session cookie失效，不会使服务器端的session对象失效。
    11. 两个浏览器窗口访问应用程序会使用同一个session
        1. 通常session cookie是不能跨窗口使用的(IE 8 版本以前)，当你新开了一个浏览器窗口进入相同页面时，系统会赋予你一个新的session id，这样信息共享的目的就达不到了。
        2. 此时可以先把session id保存在persistent cookie中(通过设置cookie的最大有效时间)，然后在新窗口中读出来，就可以得到上一个窗口的session id了，这样通过session cookie和persistent cookie的结合就可以实现了跨窗口的会话跟踪。
    12. Session的超时管理
        1. WEB服务器无法判断当前的客户端浏览器是否还会继续访问，也无法检测客户端浏览器是否关闭，所以，即使客户已经离开或关闭了浏览器，WEB服务器还要保留与之对应的HttpSession对象。
        2. 随着时间的推移而不断增加新的访问客户端，WEB服务器内存中将会因此积累起大量的不再被使用的HttpSession对象，并将最终导致服务器内存耗尽。
        3. WEB服务器采用“超时限制”的办法来判断客户端是否还在继续访问，如果某个客户端在一定的时间之内没有发出后续请求，WEB服务器则认为客户端已经停止了活动，结束与该客户端的会话并将与之对应的HttpSession对象变成垃圾。
        4. 如果客户端浏览器超时后再次发出访问请求，WEB服务器则认为这是一个新的会话的开始，将为之创建新的HttpSession对象和分配新的会话标识号。 
        5. 会话的超时间隔可以在web.xml文件中设置，其默认值由Servlet容器定义。 
            ```
            <session-config>
                <session-timeout>30</session-timeout>
            </session-config>
            ```
    13. HttpSession接口中的方法
        1. getId方法
        2. getCreationTime方法
        3. getLastAccessedTime方法
        4. setMaxInactiveInterval方法
        5. getMaxInactiveInterval方法
        6. isNew方法
            - 如果客户端请求消息中返回了一个与Servlet程序当前获得的HttpSession对象的会话标识号相同的会话标识号，则认为这个HttpSession对象不是新建的。
        7. invalidate方法
        8. getServletContext方法
        9. setAttribute方法
        10. getAttribute方法
        11. removeAttribute方法
        12. getAttributeNames方法
    14. HttpServletRequest接口中的Session方法
        1. getSession方法
            1. public HttpSession getSession(boolean create)
            2. public HttpSession getSession()
        2. isRequestedSessionIdValid方法 
        3. isRequestedSessionIdFromCookie方法 
        4. isRequestedSessionIdFromURL方法 
    15. 利用URL重写实现Session跟踪
        1. Servlet规范中引入了一种补充的会话管理机制，它允许不支持Cookie的浏览器也可以与WEB服务器保持连续的会话。这种补充机制要求在响应消息的实体内容中必须包含下一次请求的超链接，并将会话标识号作为超链接的URL地址的一个特殊参数。
        2. 将会话标识号以参数形式附加在超链接的URL地址后面的技术称为URL重写。如果在浏览器不支持Cookie或者关闭了Cookie功能的情况下，WEB服务器还要能够与浏览器实现有状态的会话，就必须对所有可能被客户端访问的请求路径（包括超链接、form表单的action属性设置和重定向的URL）进行URL重写。 
        3. HttpServletResponse接口中定义了两个用于完成URL重写方法：
            1. encodeURL方法 
            2. encodeRedirectURL方法
    16. HttpSession 的生命周期：
        1. 什么时候创建 HttpSession 对象
            1. 是否浏览器访问服务端的任何一个 JSP 或 Servlet，服务器都会立即创建一个 HttpSession 对象呢？不一定。若当前的 JSP（或 Servlet） 是客户端访问的当前 WEB 应用的第一个资源，且 JSP 的 page 指定的 session 属性值为 false, 则服务器就不会为 JSP 创建一个 HttpSession 对象；若当前 JSP 不是客户端访问的当前 WEB 应用的第一个资源，且其他页面已经创建一个 HttpSession 对象，则当前 JSP 页面会返回一个会话的 HttpSession 对象，而不会创建一个新的 HttpSession 对象
            2. session="false" 到底表示什么意思？当前 JSP 页面禁用 session 隐含变量！但可以使用其他的显式的 HttpSession 对象
            3. 对于 Servlet 而言：若 Servlet 是客户端访问的第一个 WEB 应用的资源，则只有调用了 request.getSession() 或 request.getSession(true) 才会创建 HttpSession 对象
        2. 什么时候销毁 HttpSession 对象
            1. 直接调用 HttpSession 的 invalidate() 方法: 该方法使 HttpSession 失效
            2. 服务器卸载了当前 WEB 应用. 
            3. 超出 HttpSession 的过期时间.
                1. 设置 HttpSession 的过期时间: session.setMaxInactiveInterval(5); 单位为秒
                2. 在 web.xml 文件中设置 HttpSession 的过期时间: 单位为 分钟.
                    ``` 
                    <session-config>
                        <session-timeout>30</session-timeout>
                    </session-config>
                    ```
            4. 并不是关闭了浏览器就销毁了 HttpSession. 
10. JavaBean
    1. JavaBean:
        1. 用作JavaBean的类必须具有一个公共的、无参数的构造方法。
        2. JavaBean的属性与普通Java类的属性的概念不一样，JavaBean的属性是以方法定义的形式出现的。
        3. 用于对属性赋值的方法称为属性修改器或setter方法，用于读取属性值的方法称为属性访问器或getter方法。
        4. 属性修改器必须以小写的set 前缀开始，后跟属性名，且属性名的第一个字母要改为大写，例如，nickName属性的修改器名称为setNickName，password属性的修改器名称为setPassword。
        5. 属性访问器通常以小写的get 前缀开始，后跟属性名，且属性名的第一个字母要改为大写，例如，nickName属性的访问器名称为getNickName，password属性的访问器名称为getPassword。 
        6. JavaBean的属性名是根据setter方法与getter方法的名称来生成的， setter方法或getter方法中除去前缀“set”和“get”后的部分即为属性名，但属性名的首字母必须小写。
    2. `<jsp:useBean>`标签
        1. `<jsp:useBean>`标签用于在某个指定的域范围（application、session、request、page等）中查找一个指定名称的JavaBean对象，如果存在则直接返回该JavaBean对象的引用，如果不存在则实例化一个新的JavaBean对象并将它按指定的名称存储在指定的域范围中。 
        
        2. 常见语法：
          
            ```
            <jsp:useBean id="beanInstanceName" class="package.class" scope="page|request|session|application"/>
            ```
            
            1. class属性用于指定JavaBean的完整类名（即必须带有包名）。
            2. id属性用于指定JavaBean实例对象的引用名称和其存储在域范围中的名称。
            3. scope属性用于指定JavaBean实例对象所存储的域范围，其取值只能是page、request、session和application等四个值中的一个，其默认值是page。
    3. `<jsp:setProperty>`标签
        1. `<jsp:setProperty>`标签用于设置JavaBean对象的属性，也就是调用JavaBean对象的setter方法。 
        2. 语法格式：
            ```
            <jsp:setProperty name="beanInstanceName" 
            { 
                property="propertyName" value="{string | <%= expression %>}" |
                property="propertyName" [ param="parameterName" ] | 
                property= "*" 
            }/>
            ```
            1. name属性用于指定JavaBean实例对象的名称，其值应与`<jsp:useBean>`标签的id属性值相同。 
            2. property属性用于指定JavaBean实例对象的属性名。
            3. value属性用于指定JavaBean实例对象的某个属性的值，其设置值可以是一个字符串，也可以是一个表达式。如果value属性的设置值是一个表达式，那么该表达式的结果类型必须与所要设置的JavaBean属性的类型一致。  
            4. param属性用于将JavaBean实例对象的某个属性值设置为一个请求参数值，它可以将作为字符串类型返回的请求参数值自动转换成要设置的JavaBean属性的类型。 
    4. `<jsp:getProperty>`标签
        1. `<jsp:getProperty>`标签用于读取JavaBean对象的属性，也就是调用JavaBean对象的getter方法，然后将读取的属性值转换成字符串后插入进输出的响应正文中。
        2. 语法: `<jsp:getProperty name="beanInstanceName" property="PropertyName" />`
            1. name属性用于指定JavaBean实例对象的名称，其值应与`<jsp:useBean>`标签的id属性值相同。 
            2. property属性用于指定JavaBean实例对象的属性名。
            3. 如果一个JavaBean实例对象的某个属性的值为null，那么，使用`<jsp:getProperty>`标签输出该属性的结果将是一个内容为“null”的字符串。 
    5. 使用JavaBean的注意事项
        1. JavaBean应放置在JSP页面的类装载器或其父级类装载器所能装载的目录中，通常放置于WEB应用程序下的 WEB-INF/classes目录中。
        2. 有些版本的Tomcat不会自动重新加载修改过的JavaBean，如果JSP页面加载JavaBean以后又修改和重新编译了JavaBean程序，那么需要修改JSP页面或者重新启动Tomcat。 
        3. JavaBean必须带有包名，不能用缺省包名。
        4. 在选择存储JavaBean的域范围时，如果使用request域能够满足需求的话，则不要使用Session域。
11. 简单标签
    1. SimpleTag 接口
        1. setJspContext 方法：该方法把代表 JSP 页面的 pageContext 对象传递给标签处理器对象。
        2. setParent 方法：该方法把父标签处理器对象传递给当前标签处理器对象
        3. getParent 方法：该方法用于获得标签的父标签处理器对象
        4. setJspBody 方法：该方法用于把代表标签体的 JspFragment 对象传递给标签处理器对象
        5. doTag 方法：该方法用于完成所有的标签逻辑。该方法可以抛出 javax.servlet.jsp.SkipPageException 异常，用于通知 web 容器不再执行 JSP 页面中位于结束标记后面的内容。
    2. JspFragment 类
        1. 该类的实例对象代表 JSP 页面中的一段符合 JSP 语法规范的 JSP 片段，这段 JSP 片段不能包含 JSP 脚本元素(`<% … %>`)
        2. JSP 引擎在处理简单标签的标签体时，会把标签体内容用一个 JspFragment  对象表示，并调用标签处理器对象的 setJspBody 方法把 JspFragment 对象传递给标签处理器对象。得到代表标签体的 JspFragment 对象后，标签开发者和就可以在标签处理器中根据需要调用 JspFragment 对象的方法，进而决定如何处理标签体。
        3. getJspContext 方法：该方法用于返回代表调用页面的 JspContext 对象
        4. Invoke 方法(java.io.Writer out)：该方法用于执行 JspFragment 对象所代表的 JSP 代码片段。在 doTag() 方法中可以根据需要调用该方法。
            1. 该方法的参数 out 用于指定将 JspFragment 对象的执行结果写入到哪个输出流对象中。若传递参数 out 的值为 null，则将执行结果写入到  JspContext.geOut() 方法返回的输出流对象中。
            2. 若想在标签处理器中修改标签体内容：需在调用 invoke 方法时指定一个可取出结果数据的输出流对象(如：StringWriter)，让标签体的执行结果输出到该输出流中，然后从该输出流对象中取出数据进行修改后在输出到目标设备
    3. SimpleTagSupport
        1. 为简化简单标签处理器的编写工作，JSP API 中提供了SimpleTag接口的一个实现类SimpleTagSupport。
        2. SimpleTagSupport实现了SimpleTag接口中的方法，它内部以成员变量的形式保存了setJspContext方法和setJspBody方法传递进来的参数。此外，它还定义了如下两个方法、来返回这两个参数：
            1. getJspContext方法：该方法用于返回代表调用页面的JspContext对象
            2. getJspBody方法：该方法用于得到代表标签体的JspFragment对象，
    4. 标签库描述文件
        1. 标签库描述(Tag Library Description)文件简称为 tld 文件，其扩展名为 .tld
        2. 多个标签的集合就形成了一个标签库，标签库中的所有标签都必须在标签文件中进行描述
        3. tld 文件可以放置在 web 应用程序的 WEB-INF 目录及其子目录中，但不能放置在 WEB-INF 目录下的 classes 和 lib 子目录中 。tld 文件也可以放置在 WEB-INF\lib 目录下的 jar 包的 META-INF 目录及其子目录中
        4. `<body-content>`：指定标签体的类型。可能取值有 3 种：
            1. empty：没有标签体	
            2. scriptless：标签体可以包含 el 表达式和 JSP 动作元素，但不能包含 JSP 的脚本元素
            3. tagdependent：表示标签体交由标签本身去解析处理。若指定 tagdependent，在标签体中的所有代码都会原封不动的交给标签处理器，而不是将执行结果传递给标签处理器
    5. 在 JSP 页面引用自定义标签
        1. 在 JSP 页面使用 taglib 指令引入标签库描述文件：`<%@ taglib prefix="" uri="" %>`
        2. uri：属性用于指定所引入的标签库描述(tld)文件中所定义的 `<uri>` 元素的内容；prefix 属性用于为引入的 tld 文件指定一个”引用代号”。Prefix 属性可以由 jsp 文件的作者任意指定，只要与其他 taglib 指令的 prefix 属性值不同就可以。
    6. EL 自定义函数
        1. EL 自定义函数：在 EL 表达式中调用的某个 Java 类的静态方法，这个静态方法需在 web 应用程序中进行配置才可以被 EL 表达式调用。
        2. EL 自定义函数可以扩展 EL 表达式的功能，让 EL 表达式完成普通 Java 程序代码所能完成的功能。
        3. EL 自定义函数开发步骤
            1. 编写 EL 自定义函数映射的Java 类中的静态方法：
                1. 这个 Java 类必须带有 public 修饰符，方法必须是这个类的带有 public 修饰符的静态方法
            2. 编写标签库描述文件(tld 文件), 在 tld 文件中描述自定义函数
                1. 为了能够让一个 Java 类的静态方法可以被 EL 表达式调用，需要在一个标签库描述文件(tld 文件)中对 EL 自定义函数进行描述，以将 Java 类中的静态方法映射成一个 EL 自定义函数
            3. 在 JSP 页面中导入和使用自定义函数
                1. 在标准 JSP 页面中使用 taglib 指令来引入 tld 文件: `<%@ taglib uri="/petrelskyTag" prefix="petrelsky"%>`
                2. uri：属性用于指定所引入的标签库描述(tld)文件中所定义的 `<uri>` 元素的内容；prefix 属性用于为引入的 tld 文件指定一个”引用代号”。Prefix 属性可以由 jsp 文件的作者任意指定，只要与其他 taglib 指令的 prefix 属性值不同就可以。
                3. 调用 EL 自定义函数: `${petrelsky :toGBK (param.username) }`
    7. jstl fn 函数
        1. 为了简化在 JSP 页面操作字符串，JSTL 中提供了一套 EL 自定义函数，这些自定义函数包含了 JSP 页面制经常要用到的字符串操作
        2. 在JSTL的表达是中要使用一个函数，其格式如下 `${ns:methodName(args....)}`
        3. 在使用这些函数之前必须在JSP中引入标准函数的声明 `<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>`
12. 过滤器
    1. Filter(过滤器)简介
        1. Filter 的基本功能是对 Servlet 容器调用 Servlet 的过程进行拦截，从而在 Servlet 进行响应处理的前后实现一些特殊的功能。
        2. 在 Servlet API 中定义了三个接口类来开供开发人员编写 Filter 程序：Filter, FilterChain, FilterConfig
        3. Filter 程序是一个实现了 Filter 接口的 Java 类，与 Servlet 程序相似，它由 Servlet 容器进行调用和执行
        4. Filter 程序需要在 web.xml 文件中进行注册和设置它所能拦截的资源：Filter 程序可以拦截 Jsp, Servlet, 静态图片文件和静态 html 文件
    2. Filter 的基本工作原理
        1. 当在 web.xml 中注册了一个 Filter 来对某个 Servlet 程序进行拦截处理时，这个 Filter 就成了 Servlet 容器与该 Servlet 程序的通信线路上的一道关卡，该 Filter 可以对 Servlet 容器发送给 Servlet 程序的请求和 Servlet 程序回送给 Servlet 容器的相应进行拦截，可以决定是否将请求继续传递给 Servlet 程序，以及对请求和相应信息是否进行修改
        2. 在一个 web 应用程序中可以注册多个 Filter 程序，每个 Filter 程序都可以对一个或一组 Servlet 程序进行拦截。
        3. 若有多个 Filter 程序对某个 Servlet 程序的访问过程进行拦截，当针对该 Servlet 的访问请求到达时，web 容器将把这多个 Filter 程序组合成一个 Filter 链(过滤器链)。Filter 链中各个 Filter 的拦截顺序与它们在应用程序的 web.xml 中映射的顺序一致
    3. Filter 接口
        1. init(FilterConfig filterConfig)throws ServletException：在 web 应用程序启动时，web 服务器将根据 web.xml 文件中的配置信息来创建每个注册的 Filter 实例对象，并将其保存在服务器的内存中。Web容器创建 Filter 对象实例后，将立即调用该 Filter 对象的 init 方法。Init 方法在 Filter 生命周期中仅执行一次，web 容器在调用 init 方法时，会传递一个包含 Filter 的配置和运行环境的 FilterConfig 对象(FilterConfig的用法和ServletConfig类似)。利用FilterConfig对象可以得到ServletContext对象，以及部署描述符中配置的过滤器的初始化参数。在这个方法中，可以抛出ServletException异常，通知容器该过滤器不能正常工作。
        2. destroy()：在Web容器卸载 Filter 对象之前被调用。该方法在Filter的生命周期中仅执行一次。在这个方法中，可以释放过滤器使用的资源
        3. 与开发Servlet不同的是，Filter接口并没有相应的实现类可供继承，要开发过滤器，只能直接实现Filter接口。
        4. doFilter(ServletRequest request,ServletResponse response, FilterChain chain)throws java.io.IOException,ServletException：
            1. doFilter()方法类似于Servlet接口的service()方法。当客户端请求目标资源的时候，容器就会调用与这个目标资源相关联的过滤器的doFilter()方法。其中参数 request, response 为 web 容器或 Filter 链的上一个 Filter 传递过来的请求和相应对象；参数 chain 为代表当前 Filter 链的对象，在特定的操作完成后，可以在当前 Filter 对象的 doFilter 方法内部需要调用 FilterChain 对象的 chain.doFilter(request,response)方法才能把请求交付给 Filter 链中的下一个 Filter 或者目标 Servlet 程序去处理，也可以直接向客户端返回响应信息，或者利用RequestDispatcher的forward()和include()方法，以及HttpServletResponse的sendRedirect()方法将请求转向到其他资源。这个方法的请求和响应参数的类型是ServletRequest和ServletResponse，也就是说，过滤器的使用并不依赖于具体的协议。
    4. FilterChain接口
        1. FilterChain接口：代表当前 Filter 链的对象。由容器实现，容器将其实例作为参数传入过滤器对象的doFilter()方法中。过滤器对象使用FilterChain对象调用过滤器链中的下一个过滤器，如果该过滤器是链中最后一个过滤器，那么将调用目标资源。
        2. doFilter(ServletRequest request,ServletResponse response)throws java.io.IOException：调用该方法将使过滤器链中的下一个过滤器被调用。如果是最后一个过滤器，会调用目标资源。
    5. FilterConfig 接口
        1. javax.servlet.FilterConfig接口：该接口类似于ServletConfig接口，由容器实现。Servlet规范将代表 ServletContext 对象和 Filter 的配置参数信息都封装在该对象中。Servlet 容器将其作为参数传入过滤器对象的init()方法中。
        2. String getFilterName()：得到描述符中指定的过滤器的名字。
        3. String getInitParameter(String name)： 返回在部署描述中指定的名字为name的初始化参数的值。如果不存在返回null.
        4. Enumeration getInitParameterNames()：返回过滤器的所有初始化参数的名字的枚举集合。
        5. public ServletContext getServletContext()：返回Servlet上下文对象的引用。
    6. 过滤器的部署
        1. 在实现一个过滤器后，需要在 web.xml 中进行注册和设置它所能拦截的资源。这可以通过`<filter>`和`<filter-mapping>`元素来完成的。
        2. `<filter>` 元素（注册Filter）
            1. `<filter>`元素用于在Web应用程序中注册一个过滤器。
            2. 在`<filter>`元素内
                1. `<filter-name>`用于为过滤器指定一个名字，该元素的内容不能为空。
                2. `<filter-class>`元素用于指定过滤器的完整的限定类名。
                3. `<init-param>`元素用于为过滤器指定初始化参数，它的子元素`<param-name>`指定参数的名字，`<param-value>`指定参数的值。在过滤器中，可以使用FilterConfig接口对象来访问初始化参数。
        3. `<filter>` 元素（注册Filter）
            1. Servlet容器对部署描述符中声明的每一个过滤器，只创建一个实例。与Servlet类似，容器将在同一个过滤器实例上运行多个线程来同时为多个请求服务，因此，开发过滤器时，也要注意线程安全的问题。
                ```
                <filter>
                    <filter-name>testFitler</filter-name>
                    <filter-class>org.test.TestFiter</filter-class>
                    <init-param>
                        <param-name>word_file</param-name>	
                        <param-value>/WEB-INF/word.txt</param-value>
                    </init-param>
                </filter>
                ```
        4. 映射 Filter
            1. `<filter-mapping>`元素用于设置一个 Filter 所负责拦截的资源。一个Filter拦截的资源可通过两种方式来指定：Servlet 名称和资源访问的请求路径( url样式)
                1. `<filter-name>`子元素用于设置filter的注册名称。该值必须是在`<filter>`元素中声明过的过滤器的名字
                2. `<url-pattern>`设置 filter 所拦截的请求路径(过滤器关联的URL样式)
                3. `<servlet-name>`指定过滤器所拦截的Servlet名称。
                4. `<dispatcher>`指定过滤器所拦截的资源被 Servlet 容器调用的方式，可以是REQUEST,INCLUDE,FORWARD和ERROR之一，默认REQUEST. 可以设置多个`<dispatcher>` 子元素用来指定 Filter 对资源的多种调用方式进行拦截
            2. `<dispatcher>` 子元素可以设置的值及其意义：
                1. REQUEST：当用户直接访问页面时，Web容器将会调用过滤器。如果目标资源是通过RequestDispatcher的include()或forward()方法访问时，那么该过滤器就不会被调用。
                2. INCLUDE：如果目标资源是通过RequestDispatcher的include()方法访问时，那么该过滤器将被调用。除此之外，该过滤器不会被调用。
                3. FORWARD：如果目标资源是通过RequestDispatcher的forward()方法访问时，那么该过滤器将被调用，除此之外，该过滤器不会被调用。
                4. ERROR：如果目标资源是通过声明式异常处理机制调用时，那么该过滤器将被调用。除此之外，过滤器不会被调用。
                    ```
                    <filter-mapping>
                        <filter-name>testFilter</filter-name>
                        <url-pattern>/test.jsp</url-pattern>
                    </filter-mapping>
                    <filter-mapping>
                        <filter-name>testFilter</filter-name>
                        <url-pattern>/index.jsp</url-pattern>
                        <dispatcher>REQUEST</dispatcher>
                        <dispatcher>FORWARD</dispatcher>
                    </filter-mapping>
                    ```
            3. 在同一个 web.xml 文件中可以为同一个 Filter 设置多个映射。若一个 Filter 链中多次出现了同一个 Filter 程序，这个 Filter 程序的拦截处理过程将被多次执行
        5. tomcat中的filter配置
            ```
            <filter>
                <filter-name>setCharacterEncodingFilter</filter-name>
                <filter-class>org.apache.catalina.filters.SetCharacterEncodingFilter</filter-class>
                <init-param>
                    <param-name>encoding</param-name>
                    <param-value>UTF-8</param-value>
                </init-param>
                <async-supported>true</async-supported>
            </filter>
            
            <filter-mapping>
                <filter-name>setCharacterEncodingFilter</filter-name>
                <url-pattern>/*</url-pattern>
            </filter-mapping>
            ```
    7. HttpServletRequestWrapper 类
        1. Servlet API 中提供了一个 HttpServletRequestWrapper 类来包装原始的 request 对象， HttpServletRequestWrapper 类实现了 HttpServletRequest 接口中的所有方法，这些方法的内部实现都是仅仅调用了一下所包装的的 request 对象的对应方法
        2. 相类似 Servlet API 也提供了一个 HttpServletResponseWrapper 类来包装原始的 response 对象
13. Servlet监听器
    1. 简介
        1. 监听器：专门用于对其他对象身上发生的事件或状态改变进行监听和相应处理的对象，当被监视的对象发生情况时，立即采取相应的行动。
        2. Servlet 监听器：Servlet 规范中定义的一种特殊类，它用于监听 web 应用程序中的 ServletContext, HttpSession 和 ServletRequest 等域对象的创建与销毁事件，以及监听这些域对象中的属性发生修改的事件。
    2. Servlet 监听器的分类
        1. 按监听的事件类型 Servlet 监听器可分为如下三种类型：
            1. 监听域对象自身的创建和销毁的事件监听器
            2. 监听域对象中的属性的增加和删除的事件监听器
            3. 监听绑定到 HttpSession 域中的某个对象的状态的事件监听器
    3. 编写 Servlet 监听器
        1. Servlet 规范为每种事件监听器都定义了相应的接口，开发人员编写的事件监听器程序只需实现这些接口，web 服务器根据用户编写的事件监听器所实现的接口把它注册到相应的被监听对象上
        2. 一些 Servlet 事件监听器需要在 web 应用程序的 web.xml 文件中进行注册，一个 web.xml 文件中可以注册多个 Servlet 事件监听器，web 服务器按照它们在 web.xml 文件中的注册顺序来加载和注册这些 Servlet 事件监听器。
        3. Servlet 事件监听器的注册和调用过程都是由 web 容器自动完成的，当发生被监听的对象被创建，修改或销毁事件时，web容器将调用与之相关的 Servlet 事件监听器对象的相关方法，开发人员在在这些方法中编写的事件处理代码即被执行
        4. 由于一个 web 应用程序只会为每个事件监听器创建一个对象，有可能出现多个线程同时调用同一个事件监听器对象的情况，所以，在编写事件监听器类时，应考虑多线程安全的问题
    4. 监听域对象的创建和销毁
        1. 域对象创建和销毁的事件监听器就是用来监听 ServletContext, HttpSession, HttpServletRequest 这三个对象的创建和销毁事件的监听器。
        2. 域对象的创建和销毁时机
        
            |                   | 创建时机                                                     | 销毁时机                                                                  |
            | ----------------- | ----------------------------------------------------------- | ----------------------------------------------------------------------- |
            | ServletContext    | web 服务器启动时为每个 web 应用程序创建相应的 ServletContext 对象 | web 服务器关闭时为每个 web 应用程序销毁相应的 ServletContext 对象             |
            | HttpSession       | 浏览器开始与服务器会话时创建                                    | 调用HttpSession.invalidate();超过了session的最大有效时间间隔;服务器进程被停止  |
            | ServletRequest    | 每次请求开始时创建                                             | 每次访问结束后销毁                                                         |
            
        3. ServletContextListener 接口
            1. ServletContextListener 接口用于监听 ServletContext 对象的创建和销毁事件。
            2. 当 ServletContext 对象被创建时，激发contextInitialized (ServletContextEvent sce)方法
            3. 当 ServletContext 对象被销毁时，激发contextDestroyed(ServletContextEvent sce)方法
        4. HttpSessionListener 接口
            1. HttpSessionListener 接口用于监听HttpSession对象的创建和销毁
            2. 创建一个Session时，激发sessionCreated (HttpSessionEvent se) 方法
            3. 销毁一个Session时，激发sessionDestroyed (HttpSessionEvent se) 方法。 
        5. ServletRequestListener接口
            1. ServletRequestListener 接口用于监听ServletRequest 对象的创建和销毁
            2. 创建一个ServletRequest 对象时，激发requestInitialized(ServletRequestEvent sre)方法
            3. 销毁一个Session时，激发requestDestroyed(ServletRequestEvent sre)方法。
    5. 域对象中属性的变更的事件监听器
        1. 域对象中属性的变更的事件监听器就是用来监听 ServletContext, HttpSession, HttpServletRequest 这三个对象中的属性变更信息事件的监听器。
        2. 这三个监听器接口分别是ServletContextAttributeListener, HttpSessionAttributeListener 和ServletRequestAttributeListener，这三个接口中都定义了三个方法来处理被监听对象中的属性的增加，删除和替换的事件，同一个事件在这三个接口中对应的方法名称完全相同，只是接受的参数类型不同 
        3. attributeAdded 方法
            1. 当向被监听器对象中增加一个属性时，web容器就调用事件监听器的 attributeAdded 方法进行相应，这个方法接受一个事件类型的参数，监听器可以通过这个参数来获得正在增加属性的域对象和被保存到域中的属性对象
            2. 各个域属性监听器中的完整语法定义为：
                1. public void attributeAdded(ServletContextAttributeEvent scae) 
                2. public void attributeAdded(HttpSessionBindingEvent  hsbe) 
                3. public void attributeAdded(ServletRequestAttributeEvent srae)
        4. attributeRemoved 方法
            1. 当删除被监听对象中的一个属性时，web 容器调用事件监听器的这个方法进行相应
            2. 各个域属性监听器中的完整语法定义为：
                1. public void attributeRemoved(ServletContextAttributeEvent scae) 
                2. public void attributeRemoved(HttpSessionBindingEvent  hsbe) 
                3. public void attributeRemoved(ServletRequestAttributeEvent srae)
        5. attributeReplaced 方法
            1. 当监听器的域对象中的某个属性被替换时，web容器调用事件监听器的这个方法进行相应
            2. 各个域属性监听器中的完整语法定义为：
                1. public void attributeReplaced(ServletContextAttributeEvent scae) 
                2. public void attributeReplaced(HttpSessionBindingEvent  hsbe) 
                3. public void attributeReplaced(ServletRequestAttributeEvent srae)
    6. 感知 Session 绑定的事件监听器
        1. 保存在 Session 域中的对象可以有多种状态：绑定到  Session 中；从 Session 域中解除绑定；随 Session 对象持久化到一个存储设备中；随 Session 对象从一个存储设备中恢复
        2. Servlet 规范中定义了两个特殊的监听器接口来帮助 JavaBean 对象了解自己在 Session 域中的这些状态：HttpSessionBindingListener接口和HttpSessionActivationListener接口 ，实现这两个接口的类不需要 web.xml 文件中进行注册
        3. HttpSessionBindingListener接口
            1. 实现了HttpSessionBindingListener接口的 JavaBean 对象可以感知自己被绑定到 Session 中和从 Session 中删除的事件
            2. 当对象被绑定到 HttpSession 对象中时，web 服务器调用该对象的  void valueBound(HttpSessionBindingEvent event) 方法
            3. 当对象从 HttpSession 对象中解除绑定时，web 服务器调用该对象的 void valueUnbound(HttpSessionBindingEvent event)方法
        4. HttpSessionActivationListener接口
            1. 实现了HttpSessionBindingListener接口的 JavaBean 对象可以感知自己被活化和钝化的事件
            2. 当绑定到 HttpSession 对象中的对象将要随 HttpSession 对象被钝化之前，web 服务器调用该对象的  void sessionWillPassivate(HttpSessionBindingEvent event) 方法
            3. 当绑定到 HttpSession 对象中的对象将要随 HttpSession 对象被活化之后，web 服务器调用该对象的 void sessionDidActive(HttpSessionBindingEvent event)方法
14. 文件的上传下载
    1. Enctype 属性
        1. 当表单需要上传文件时，需指定表单 enctype 的值为 multipart/form-data
        2. 在 form 元素的语法中，enctype 属性指定将数据发送到服务器时浏览器使用的编码类型。  
        3. enctype 属性取值: 
            1. application/x-www-form-urlencoded：表单 enctype 属性的默认值。这种编码方案使用有限的字符集，当使用了非字母和数字时，必须用”%HH”代替(H 代表十六进制数字)。对于大容量的二进制数据或包含非 ASCII 字符的文本来说，这种编码不能满足要求。
            2. multipart/form-data：form 设定了enctype=“multipart/form-data”属性后，表示表单以二进制传输数据
    2. Commons-fileupload 组件
        1. Commons-fileupload 组件是 Apache 开源代码组织用来处理表单文件上传的一个子项目，该组件性能优异，可以支持任意大小的文件的上传
        2. Commons-fileupload 组件从 1.1 版本开始依赖 Apache 的另一个项目：commons-io
        3. Commons-fileupload 组件上传的基本原理 
            1. FileUpload组件将页面提交的所有元素(普通form表单域，如text和文件域file)都看作一样的FileItem，这样上传页面提交的 request请求也就是一个FileItem的有序组合，FileUpload组件可以解析该request，并返回一个一个的FileItem。而对每一个FileItem，FileUpload组件可以判断出它是普通form表单域还是文件file域，从而根据不同的类型，采取不同的操作－－如果是表单域，就读出其值，如果是文件域，就保存文件到服务器硬盘上或者内存中。 
        4. Commons-fileupload 组件API
            1. 在 Commons-fileupload 组件中，主要用到以下三个接口和类：
                1. org.apache.commons.fileupload.FileItem
                2. org.apache.commons.fileupload.disk.DiskFileItemFactory
                3. org.apache.commons.fileupload.servlet.ServletFileUpload
            2. ServletFileUpload 负责处理上传的文件数据，并将每部分的数据封装成一到 FileItem 对象中。
            3. DiskFileItemFactory 是创建 FileItem 对象的工厂，在这个工厂类中可以配置内存缓冲区大小和存放临时文件的目录。
            4. ServletFileUpload 在接收上传文件数据时，会将内容保存到内存缓存区中，如果文件内容超过了 DiskFileItemFactory 指定的缓冲区的大小，那么文件将被保存到磁盘上，存储为 DiskFileItemFactory 指定目录中的临时文件。等文件数据都接收完毕后，ServletUpload 在从文件中将数据写入到上传文件目录下的文件中
    3. 文件的下载
        1. 利用程序实现下载需要设置 2 个报头：
            1. Web 服务器需要告诉浏览器其所输出的内容的类型不是普通的文本文件或 HTML 文件，而是一个要保存到本地的下载文件。设置Content-Type 的值为：application/octet-stream
            2. Web 服务器希望浏览器不直接处理相应的实体内容，而是由用户选择将相应的实体内容保存到一个文件中，这需要设置 Content-Disposition 报头。该报头指定了接收程序处理数据内容的方式，在 HTTP 应用中只有 attachment 是标准方式，attachment 表示要求用户干预。在 attachment 后面还可以指定 filename 参数，该参数是服务器建议浏览器将实体内容保存到文件中的文件名称。在设置 Content-Disposition 之前一定要指定 Content-Type.	
            ```
            response.setContentType("application/octet-stream");
            String str = "attachment;filename=" + java.net.URLEncoder.encode(fileName, "utf-8");
            response.setHeader("Content-Disposition", str);
            ```
        2. 因为要下载的文件可以是各种类型的文件，所以要将文件传送给客户端，其相应内容应该被当做二进制来处理，所以应该调用 response.getOutputStream(); 方法返回 ServletOutputStream 对象来向客户端写入文件内容。
            ```
            ServletOutputStream sos = response.getOutputStream();
            byte[] data = new byte[2048];
            int len = -1;
            while ((len = is.read(data)) != -1) {
                sos.write(data, 0, len);
            }
            ```
15. 国际化：
    1. Locale 类
        1. Locale 实例对象代表一个特定的地理，政治或文化上的区域
        2. 一个 Locale 对象本身不会验证它代表的语言和国家地区信息是否正确，只是向本地敏感的类提供本地信息，与国际化相关的格式化和解析任务由本地敏感的类(若JDK中的某个类在运行时需要根据 Locale 对象来调整其功能，这个类就称为本地敏感类)去完成
    2. DateFormat 类
        1. DateFormat 类可以将一个日期/时间对象格式化为表示某个国家地区的日期/时间字符串，也可以将表示某个本地的日期/时间的字符串解析为相应的日期/时间对象
        2. DateFormat 类定义了一些用于描述日期/时间的显示模式的 int 型的常量，包括FULL, LONG, MEDIUM, DEFAULT, SHORT，这些常量用于描述表示日期/时间字符串的长度。这些常量说明表示的日期/时间的确切格式取决于具体的国家和地区
        3. 获取 DateFormat 对象
        4. DateFormat 对象通常不是线程安全的，每个线程都应该创建自己的 DateFormat  实例对象
        5. DateFormat 对象的方法：
            1. format： 将日期/时间对象格式化为符合某个本地环境习惯的字符串
            2. parse：将符合某个本地环境习惯的日期/时间字符串解析为日期/时间对象
    3. NumberFormat 类
        1. NumberFormat 可以将一个数值格式化为符合某个国家地区习惯的数值字符串，也可以将符合某个国家地区习惯的数值字符串解析为对应的数值
        2. NumberFormat 类的方法：
            1. format 方法：将一个数值格式化为符合某个国家地区习惯的数值字符串
            2. parse 方法：符合某个国家地区习惯的数值字符串解析为对应的数值
    4. MessageFormat 类
      
        1. MessageFormat 类提供了一种参数替换模式字符串中的占位符的方式，它将根据模式字符串中包含的占位符产生一系列的格式化对象，然会调用这些格式化对象对参数进行格式化，并用格式化后的结果字符串替换模式字符串中的相应占位符。
    5. 模式字符串与占位符
        1. 模式字符串： On {0}, {1} destroyed {2} houses and caused {3} of damage.
        2. 对模式字符串进行格式化操作时，需要采用数组的方式提供模式字符串中的每个占位符所对应的参数.
        3. 占位符有以下三种方式：
            1. {argumentIndex}: 0-9 之间的数字，表示要格式化对象数据在参数数组中的索引号
            2. {argumentIndex,formatType}: 参数的格式化类型
            3. {argumentIndex,formatType,FormatStyle}: 与指定的格式化类型对应的模式，它的值必须是与相应的格式化类型匹配的合法模式或表示合法模式的字符串
        4. MessageFormat 类可以格式化模式字符串，它根据其中的占位符产生一系列的格式化对象，然后调用这些格式化对象对参数进行格式化，并用格式化后的结果字符串替换模式字符串中的相应占位符。
        5. 格式化模式字符串的步骤：
            1. 创建 MessageFormat 对象：须指定格式化的模式字符串，也可以指定 Locale 对象来按某个国家地区的习惯进行格式化。
            2. 调用 MessageFormat 对象的 format 方法执行格式化操作：须为format 方法传递一个数组类型的参数，数组中的每个元素分别用于代替模式字符串中的与其索引号相对应的占位符
    6. ResourceBundle 类
      
        1. ResourceBundle 类用于描述一个资源包，一个资源包用于包含一组与某个本地环境相关的对象，可以从一个资源包中获取特定于本地环境的对象。对于不同的本地环境，可以有不同的 ResourceBundle 对象与之关联，关联的 ResourceBundle 对象中包含该本地环境下专有的对象
    7. 资源包简介
        1. 在设计一个国际化应用时，应该把程序显示的文本内容从源程序中分离出来，放在独立的资源文件中，并针对不同的本地环境编写不同的资源文件。这些资源文件被称为应用程序的资源包
        2. 应用程序在运行时，将从与用户的本地环境相对应资源文件中读取名称项对应的值的内容，由于同一个名称项在各个资源文件中对应的值内容是随本地环境信息而改变的，这样就实现了程序的静态文本内容的国际化。
        3. 当要为应用程序添加某个新的本地化支持时，只需编写一个适合的本地环境的资源文件即可，不用修改源程序代码
        4. 一个应用程序可以有多个资源包，一个资源包中的每个资源文件都拥有共同的基名。除了基名，每个资源文件的名称中还有标识其本地信息的附加部分。例如：一个资源包的基名是：“myproperties”, 则该资源包中与中文环境相对应的资源文件为: “myproperties_zh.properties”
        5. 一般情况下，每个资源包都有一个默认的资源文件，默认的资源文件不带标识本地信息的附加部分。若应用程序在资源包中找不到某个本地环境匹配的资源文件，最后将选择该资源包中的默认资源文件。
    8. 资源文件的内部格式
        1. 资源文件通常采用 java.util.Properties 类要求的文件格式，其中包含每项资源信息的名称项和值内容，每个名称项用于唯一地标识一个资源信息，值内容用于指定资源信息在某个本地环境下的内容
        2. 一个资源包中的所有资源文件中通常都应包含相同的名称项，与各个本地环境对应的资源文件中为这些名称项设置的值分别是适合该本地环境的内容。
        3. 资源文件完全遵循 java.util.Properties 类要求的文件格式，它要求资源文件中的字符必须全部为有效的 ASCII 字符。若资源文件中要包含非 ASCII 的字符，必须将它们转化成”\uXXXX”形式的转移序列，其中 XXXX 是该字符的 Unicode 编码的十六进制数值
    9. 使用 native2ascii 程序转换字符编码
        1. JDK 中提供了一个 native2ascii 工具程序，它可以将某种本地字符集编码的字符转换成 Unicode 转义序列的形式
        2. DOS 下进入 haha.txt 文件所在目录，运行下面的命令后将在当前目录下生成一个名为 hehe.properties 文件：
            1. native2ascii -encoding gb2312 haha.txt haha.properties
    10. 装载资源包
        1. ResourceBundle 类提供了存放和管理资源包的功能
        2. 当应用程序需要获取特定locale对象关联的资源包时，可以调用ResourceBundle的getBundle方法，并将locale对象作为参数传入。
            ```
            Locale currentLocale = Locale.getDefault();
            ResourceBundle myResources = ResourceBundle.getBundle("myproperties", currentLocale);
            ```
        3. 如果与该locale对象匹配的资源包子类找不到，getBundle将试着查找最匹配的一个子类。如果特定locale对象的语言代码、国家代码和可选变量都是空值，则基名是唯一的候选资源包名称。 
    11. 读取资源信息
        1. 加载资源文件后， ResourceBundle 的实例对象就可以使用 getString 方法获取指定的资源信息名称所对应的值。
        2. `String OkKey = myResources.getString("OkKey");`
    12. Web 应用程序的国际化
        1. 实现 web 应用国际化有两种方式：
            1. 针对不同语言和地区的用户开发出不同的 JSP 网页版本，当用户请求资源时，根据请求消息中携带的本地信息为用户提供合适的版本
            2. 将对本地环境敏感的资源数据(例如：错误提示信息，菜单文字等)从网页中分离出来，放在 .properties 属性资源文件中。对于应用程序中的数值，货币和日期/时间等本地敏感数据，可以通过占位符的方式设置它们的格式类型和格式模式。
    13. 获取 web 应用中的本地信息
        1. 要实现 web 应用的国际化，首先要获得客户端浏览器的本地信息。
        2. 在 Servlet 程序中，调用 HttpServletRequest 对象的 方法获得代表客户端本地信息的 Locale 对象：
            1. getLocale()：返回代表客户端的首选本地信息的 Locale 对象
            2. getLocales()：返回一个包含客户端支持的所有本地信息的 Locale 对象的 Enumeration 对象，这些Locale 对象按照客户端支持的所有本地信息的优先级在集合中一次排列
    14. 国际化格式标签库
        1. 简介
            1. `<fmt:bundle>`标签用于绑定数据源.properties文件(基名)：
                ```
                <fmt:bundle basename="源文件名(且不能带后缀哦)" prefix=""> 
                    语句，代码等 
                </fmt:bundle>
                ```
            2. `<fmt:message>`标签用于从指定的资源文件中把指定的键值取来：
                1. `<fmt:message key="" [var="varname"] [bundle=""] [scope="page|..."] />`
                2. 如果用到var的话就不会在页面直接输出，而需要用到`<c:out>`标签来进行页面的输出
            3. `<fmt:message>`必须和`<fmt:bundle>`搭配使用 
            4. `<fmt:message>`标签可以配合`<fmt:param>`标签来进行设定`<fmt:message>`标签指向键的动态值
            5. `<fmt:setBundle>`标签用于设置默认的数据来源：
              
                1. `<fmt:setBundle basename="" [ var=""]  [scope="" ]  />`
            6. `<fmt:formatNumber>`标签用于根据设定的区域将数据格式化输出；
            7. `<fmt:formatDate>`标签用于格式化输出日期和时间;
            8. `<fmt:parseDate>`标签用于把字符串类型的日期和时间转换成日期型数据类型;
            9. `<fmt:setTimeZone>`标签用于设定默认的时区;
            10. `<fmt:timeZone>`标签用于设定在本签体内有效的时区
16. 关于 ThreadLocal
    1. 通过 ThreadLocal.set() 将对象的引用保存到各线程的自己的一个 map 中，每个线程都有这样一个map，执行ThreadLocal.get()时，各线程从自己的map中取出放进去的对象，因此取出来的是各自自己线程中的对象，ThreadLocal 实例是作为map的key来使用的。
    2. 一般情况下，通过ThreadLocal.set() 到线程中的对象是该线程自己使用的对象，其他线程是不需要访问的
    3. ThreadLocal 不是用来解决共享对象的多线程访问问题的：如果ThreadLocal.set() 进去的东西本来就是多个线程共享的同一个对象，那么多个线程的 ThreadLocal.get() 取得的还是这个共享对象本身，还是有并发访问问题。
    4. ThreadLocal的应用场合：按线程多实例（每个线程对应一个实例）的对象的访问。
