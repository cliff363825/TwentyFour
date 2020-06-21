package com.onevgo.springmvc.handlers;

import com.onevgo.springmvc.crud.dao.EmployeeDao;
import com.onevgo.springmvc.crud.entities.Employee;
import com.onevgo.springmvc.entities.User;
import com.onevgo.springmvc.exceptions.UserNameNotMatchPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.*;

@SessionAttributes(value = {"user"})
//@SessionAttributes(types = {User.class})
@RequestMapping(value = "/springmvc")
@Controller
public class SpringMVCTest {
    private static final String SUCCESS = "success";

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam Integer i) {
        if (i != null) {
            String[] vals = new String[10];
            System.out.println(vals[i]);
        }
        return SUCCESS;
    }

    @RequestMapping(value = "/testDefaultHandlerExceptionResolver",method = RequestMethod.POST)
    public String testDefaultHandlerExceptionResolver() {
        System.out.println("testDefaultHandlerExceptionResolver");
        return SUCCESS;
    }

//    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "测试")
    @RequestMapping("/testResponseStatusExceptionResolver")
    public String testResponseStatusExceptionResolver(Integer i) {
        if (i != null && i == 13) {
            throw new UserNameNotMatchPasswordException();
        }
        System.out.println("testResponseStatusExceptionResolver");
        return SUCCESS;
    }

    // 1. 在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数，该参数即对应发生的异常对象
    // 2. @ExceptionHandler 方法的入参中不能传入 Map。若希望把异常信息传到页面上，需要使用 ModelAndView 作为返回值
    // 3. @ExceptionHandler 方法标记的异常有优先级的问题
    // 4. @ControllerAdvice 如果在当前 Handler 中找不到 @ExceptionHandler 方法来处理当前方法出现的异常，
    // 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常。
//    @ExceptionHandler({ArithmeticException.class})
//    public ModelAndView handleArithmeticException(Exception ex) {
//        System.out.println("出异常了: " + ex);
//        ModelAndView mv = new ModelAndView("error");
//        mv.addObject("exception", ex);
//        return mv;
//    }
//
//    @ExceptionHandler({RuntimeException.class})
//    public ModelAndView handleArithmeticException2(Exception ex) {
//        System.out.println("[出异常了]: " + ex);
//        ModelAndView mv = new ModelAndView("error");
//        mv.addObject("exception", ex);
//        return mv;
//    }

    @RequestMapping("/testExceptionHandlerExceptionResolver")
    public String testExceptionHandlerExceptionResolver(@RequestParam Integer i) {
        System.out.println("result: " + (10 / i));
        return SUCCESS;
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam String desc,
                                 @RequestParam MultipartFile file) throws IOException {
        System.out.println("desc: " + desc);
        System.out.println("OriginalFilename: " + file.getOriginalFilename());
        System.out.println("InputStream: " + file.getInputStream());
        return "success";
    }

    @RequestMapping("/i18n")
    public String testI18n(Locale locale) {
        System.out.println(locale);
        System.out.println(messageSource.getClass());
        String val = messageSource.getMessage("i18n.username", null, locale);
        System.out.println(val);
        return "i18n";
    }

    // HttpEntity<T>
    // RequestEntity<T>
    // ResponseEntity<T>
    @RequestMapping("/testHttpEntity")
    public ResponseEntity<String> testHttpEntity(RequestEntity<String> entity) {
        System.out.println("headers: " + entity.getHeaders());
        System.out.println("body: " + entity.getBody());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Token", "123456");

        return new ResponseEntity<String>(entity.getHeaders() + entity.getBody(), headers, HttpStatus.OK);
    }

    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(ServletRequest request) throws IOException {
        byte[] body = null;
        ServletContext servletContext = request.getServletContext();
        InputStream is = servletContext.getResourceAsStream("/login.html");
        body = new byte[is.available()];
        is.read(body);
        is.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=login.html");

        ResponseEntity<byte[]> response = new ResponseEntity<>(body, headers, HttpStatus.OK);
        return response;
    }

    @ResponseBody
    @RequestMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body) {
        System.out.println(body);
        return "hello world! " + new Date();
    }

    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson() {
        Collection<Employee> employees = employeeDao.getAll();
        return employees;
    }

    @RequestMapping("/testJson1")
    public ResponseEntity<Collection<Employee>> testJson1() {
        Collection<Employee> employees = employeeDao.getAll();
        ResponseEntity<Collection<Employee>> responseEntity = new ResponseEntity<>(employees, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping("/testConversionServiceConverter")
    public String testConverter(@RequestParam("employee") Employee employee) {
        System.out.println("save: " + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping("/testForward")
    public String testForward() {
        return "forward:/WEB-INF/views/success.jsp";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect() {
        System.out.println("testRedirect");
        return "redirect:/spring/springmvc/index.jsp";
    }

    @RequestMapping("/testView")
    public String testView() {
        System.out.println("testView");
        return "helloView";
    }

    @RequestMapping("/testViewAndViewResolver")
    public String testViewAndViewResolver() {
        System.out.println("testViewAndViewResolver");
        return SUCCESS;
    }

    // 1. 有 @ModelAttribute 标记的方法，会在每个目标方法执行之前被 SpringMVC 调用！
    // 2. @ModelAttribute 注解也可以来修饰目标方法 POJO 类型的入参，其 value 属性值有如下的作用：
    //   1. SpringMVC 会使用 value 属性值在 implicitModel 中查找对应的对象，若存在则会直接传入到目标方法的入参中
    //   2. SpringMVC 会以 value 为 key，POJO 类型的对象为 value，存入到 request 中
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id,
                        Map<String, Object> map) {
        if (id != null) {
            User user = new User();
            user.setId(1);
            user.setUsername("Tom");
            user.setPassword("123456");
            user.setEmail("tom@onevgo.com");
            user.setAge(12);
            System.out.println("从数据库中获取一个对象，User: " + user);
            map.put("user", user);
        }
    }

    // 运行流程：
    // 1. 执行 @ModelAttribute 注解修饰的方法：从数据库中取出对象，把对象放入到了 Map 中。键为 User
    // 2. SpringMVC 从 Map 中取出 User 对象，并把表单的请求参数赋给 User 对象的对应属性。
    // 3. SpringMVC 把上述对象传入目标方法的参数
    // 注意：在 @ModelAttribute 修饰的方法中，放入到 Map 时的键需要和目标方法入参类型的第一个字母小些的字符串一致！
    // ==================
    // SpringMVC 确定目标方法 POJO 类型入参的过程
    // 1. 确定一个 key
    //   1. 若目标方法的 POJO 类型的参数木有使用 @ModelAttribute 作为修饰，则 key 为 POJO 类名第一个字母的小写。
    //   2. 若使用了 @ModelAttribute 来修饰，则 key 为 @ModelAttribute 注解的 value 属性值。
    // 2. 在 implicitModel 中查找 key 对应的对象，若存在，则作为入参传入
    //   1. 若在 @ModelAttribute 标记的方法中在 Map 中保存过，且 key 和 1 确定的 key 一致，则会获取到
    // 3. 若 implicitModel 中不存在 key 对应的对象，则检查当前的 Handler 使用使用 @SessionAttributes 注解修饰，
    //    若使用了该注解，且 @SessionAttributes 注解的 value 属性值中包含了 key，则会从 HttpSession 中来获取 key 所
    //    对应的 value 值，若存在则直接传入到目标方法的入参中。若不存在则将抛出异常。
    // 4. 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key，则
    //    会通过反射来创建 POJO 类型的参数，传入为目标方法的参数
    // 5. SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中，进而会保存到 request 中
    // ==================
    // 源代码分析的流程
    // 1. 调用 @ModelAttribute 注解修饰的方法，实际上把 @ModelAttribute 方法中的 Map 中的数据放在了 implicitModal 中
    // 2. 解析请求处理其的目标参数，实际上该目标参数来自于 WebDataBinder 对象的 target 属性
    //   1. 创建 WebDataBinder 对象：
    //     1. 确定 objectName 属性：若传入的 attrName 属性值为 ""，则 objectName 为类名第一个字母小写
    //        注意：attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰，则 attrName 值即为 @ModelAttribute 的 value 属性值
    //     2. 确定 target 属性
    //       1. 在 implicitModel 中查找 attrName 对应的属性值。若存在，ok
    //       2. 如果不存在，则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰，如果使用了，则尝试从 Session 中
    //          获取 attrName 所对应的属性值。若 session 中没有对应的属性值，则抛出异常。
    //       3. 若 Handler 没有使用 @sessionAttributes 进行修饰，或 @SessionAttributes 中没有使用 value 值指定的 key
    //          和 attrName 相匹配，则通过反射创建 POJO 对象
    //   2. SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性
    //   3. SpringMVC 会把 WebDataBinder 的 attrName 和 target 给到 implicitModel
    //   4. 把 WebDataBinder 把 target 作为参数传递给目标方法的入参
    @RequestMapping(value = "/testModelAttribute")
    public String testModelAttribute(User user) {
        System.out.println("testModelAttribute, 修改: " + user);
        return SUCCESS;
    }

    // @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外（实际上使用的是 value 属性值）
    // 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中（实际上使用的是 types 属性值）
    // 注意：该注解只能放在类的上面，不能修饰方法
    @RequestMapping(value = "/testSessionAttributes")
    public String testSessionAttributes(Map<String, Object> map) {
        System.out.println("testSessionAttributes");
        User user = new User();
        user.setId(2);
        user.setUsername("Tom");
        user.setPassword("123456");
        user.setEmail("tom@onevgo.com");
        user.setAge(15);
        map.put("user", user);
        return SUCCESS;
    }

    // 目标方法可以添加 Map 类型（实际上也可以是 Model 类型或 ModalMap 类型）的参数
    // Map<String, Object> map = org.springframework.validation.support.BindingAwareModelMap
    @RequestMapping(value = "/testMap")
    public String testMap(Map<String, Object> map) {
        System.out.println(map.getClass());
        map.put("names", Arrays.asList("Tom", "Jerry", "Mike"));
        System.out.println(map);
        return SUCCESS;
    }

    // 目标方法的返回值可以是 ModelAndView 类型
    // 其中可以包含视图和模型信息
    // SpringMVC 会把 ModelAndView 的 model 中的数据放入到 request 域对象中
    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView() {
        String viewName = SUCCESS;
        ModelAndView modelAndView = new ModelAndView(viewName);
        // 添加模型数据到 ModelAndView 中
        modelAndView.addObject("time", new Date());
        return modelAndView;
    }

    // 可以使用 Servlet 原生的 API 作为目标方法的参数
    // 具体支持以下类型
    // 1. HttpServletRequest        ${pageContext.request}
    // 2. HttpServletResponse       ${pageContext.response}
    // 3. HttpSession               ${pageContext.session}
    // 4. java.security.Principal
    // 5. Locale
    // 6. InputStream
    // 7. OutputStream
    // 8. Reader
    // 9. Writer
    @RequestMapping(value = "/testServletAPI")
    public void testServletAPI(ServletRequest request,
                               ServletResponse response,
                               WebRequest webRequest,
                               Writer out) throws IOException {
        System.out.println(((ServletWebRequest) webRequest).getRequest() == request);
        System.out.println("testServletAPI, " + request + ", " + response);
        out.write("hello springmvc");
//        return SUCCESS;
    }

    // Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配，
    // 自动为该对象填充属性值。支持级联属性。
    // 如：dept.deptId、dept.address.tel 等
    @RequestMapping(value = "/testPojo")
    public String testPojo(User user) {
        System.out.println("testPojo, user: " + user);
        return SUCCESS;
    }

    // 了解：
    // @CookieValue: 映射一个 Cookie 值。属性同 @RequestParam
    // ${cookie.JSESSIONID} = @CookieValue(value = "JSESSIONID", required = false) String sessionId
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID", required = false) String sessionId) {
        System.out.println("testCookieValue, JSESSIONID: " + sessionId);
        return SUCCESS;
    }

    // 了解：
    // 映射请求头信息
    // 用法同 @RequestParam
    // ${header.Token} = @RequestHeader(value = "Token", required = false) String token
    @RequestMapping(value = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Token", required = false) String token) {
        System.out.println("testRequestHeader, Token: " + token);
        return SUCCESS;
    }

    // org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException:
    // Failed to convert value of type 'java.lang.String' to required type 'java.util.Map';
    // nested exception is java.lang.IllegalStateException:
    // Cannot convert value of type 'java.lang.String' to required type 'java.util.Map':
    // no matching editors or conversion strategy found
    // 需要实现 Converter<String, Map> 接口
    @RequestMapping(value = "/testRequestParamMap2")
    public String testRequestParamMap2(@RequestParam("map") Map<String, Object> map) {
        System.out.println("testRequestParamMap2, map: " + map);
        return SUCCESS;
    }

    // map.putAll(${param})
    @RequestMapping(value = "/testRequestParamMap")
    public String testRequestParamMap(@RequestParam Map<String, Object> map) {
        System.out.println("testRequestParamMap, map: " + map);
        return SUCCESS;
    }

    // @RequestParam 来映射请求参数
    // value 值即请求参数的参数名
    // required 该参数是否必须。默认为 true
    // defaultValue 请求参数的默认值
    // ${param.name} = @RequestParam("name") String username
    // ${param.age} = @RequestParam(value = "age", required = false, defaultValue = "0") int age
    @RequestMapping(value = "/testRequestParam")
    public String testRequestParam(@RequestParam("name") String username,
                                   @RequestParam(value = "age", required = false, defaultValue = "0") int age) {
        System.out.println("testRequestParam, username: " + username + ", age: " + age);
        return SUCCESS;
    }

    // Rest 风格的 URL
    // 以 CRUD 为例：
    // 新增   /order      POST
    // 修改   /order/1    PUT     update?id=1
    // 获取   /order/1    GET     get?id=1
    // 删除   /order/1    DELETE  delete?id=1
    // 如何发送 PUT 请求和 DELETE 请求
    // 1. 需要配置 HiddenHttpMethodFilter
    // 2. 需要发送 POST 请求
    // 3. 需要在发送 POST 请求是携带一个 name="_method" 的隐藏域，值为 DELETE 或 PUT
    // 在 SpringMVC 的目标方法中如何得到 id 呢？
    // 使用 @PathVariable 注解
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable Integer id) {
        System.out.println("testRest DELETE: " + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
    public String testRestPut(@PathVariable Integer id) {
        System.out.println("testRest PUT: " + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest", method = RequestMethod.POST)
    public String testRestPost() {
        System.out.println("testRest POST");
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
    public String testRestGet(@PathVariable Integer id) {
        System.out.println("testRest GET: " + id);
        return SUCCESS;
    }

    // @PathVariable 可以来映射 URL 中的占位符到目标方法的参数中。
    // {id} = @PathVariable Integer id
    // {name} = @PathVariable("name") String username
    @RequestMapping("/testPathVariable/{id}/{name}")
    public String testPathVariable(@PathVariable Integer id, @PathVariable("name") String username) {
        System.out.println("testPathVariable, id: " + id + ", username: " + username);
        return SUCCESS;
    }

    @RequestMapping("/testAntPath/*/abc")
    public String testAntPath() {
        System.out.println("testAntPath");
        return SUCCESS;
    }

    // 了解：可以使用 params 和 headers 来更加精确的映射请求。params 和 headers 支持简单的表达式。
    @RequestMapping(value = "/testParamsAndHeaders",
            params = {"username", "age!=10"},
            headers = {"Token=123456"}
    )
    public String testParamsAndHeaders() {
        System.out.println("testParamsAndHeaders");
        return SUCCESS;
    }

    // 常用：使用 method 属性来制定请求方式
    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public String testMethod() {
        System.out.println("testMethod");
        return SUCCESS;
    }

    // 1. @RequestMapping 除了修饰方法，还可以修饰类
    // 2.
    //   1) 类定义处：提供初步的请求映射信息。相对于  WEB 应用的根目录
    //   2) 方法处：提供进一步的细分映射信息。相对于类定义处的 URL。若类定义处未标注 @RequestMapping，则方法处标记的 URL 相对于 WEB 应用的根目录
    @RequestMapping("/testRequestMapping")
    public String testRequestMapping() {
        System.out.println("testRequestMapping");
        return SUCCESS;
    }
}
