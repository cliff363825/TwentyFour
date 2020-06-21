package com.onevgo.springboot.cache.service;

import com.onevgo.springboot.cache.bean.Employee;
import com.onevgo.springboot.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * <code>@CacheConfig</code> 提供了一种机制，用于在类级别共享与缓存相关的公共设置。
 * <p>当该注释出现在给定的类上时，它为该类中定义的任何缓存操作提供一组默认设置。
 *
 * <p>1. cacheNames: 用于缓存在带注释的类中定义的操作的默认缓存的名称。
 * <ul>
 * <li>如果在操作级别设置了none，则使用它们而不是默认值。</li>
 * <li>可以用来确定目标缓存(或缓存)，匹配限定符值或特定bean定义的bean名称。</li>
 * </ul>
 *
 * <p>2. keyGenerator: 要用于类的默认 org.springframework.cache.interceptor.KeyGenerator 的bean名。
 * <ul>
 * <li>如果在操作级别上没有设置任何值，则使用此值而不是默认值。</li>
 * <li>密钥生成器与使用自定义密钥是互斥的。当为操作定义这样的键时，将忽略此键生成器的值。</li>
 * </ul>
 *
 * <p>3. cacheManager: 自定义 org.springframework.cache.CacheManager 的bean名。如果还没有设置，那么使用 CacheManager 来创建默认的 org.springframework.cache.interceptor.CacheResolver。
 * <ul>
 * <li>如果在操作级没有设置冲突解决程序和缓存管理器，并且没有通过cacheResolver设置缓存冲突解决程序，那么将使用此冲突解决程序代替默认冲突解决程序。</li>
 * </ul>
 *
 * <p>4. cacheResolver: 要使用的自定义org.springframework.cache.interceptor.CacheResolver 的bean名。
 * <ul>
 * <li>如果在操作级别上没有设置解析器和缓存管理器，则使用此设置而不是默认设置。</li>
 * </ul>
 */
@CacheConfig(cacheNames = "emp", cacheManager = "employeeCacheManager") // 抽取缓存的公共配置
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * <code>@Cacheable</code>: 说明可以缓存调用方法(或类中的所有方法)的结果的注释。
     * <p>每次调用增强的方法时，都会应用缓存行为，检查给定参数是否已经调用了该方法。合理的默认值只是使用方法参数来计算 key，但是SpEL表达式可以通过key属性提供，或者自定义 org.springframework.cache.interceptor.KeyGenerator 实现可以替换默认值(参见keyGenerator)。
     * <p>如果在缓存中找不到计算得到的键的值，那么将调用目标方法，并将返回的值存储在关联的缓存中。注意，Java8的可选返回类型是自动处理的，如果存在，它的内容将存储在缓存中。
     * <p>此注释可以用作元注释，以创建具有属性覆盖的自定义复合注释。
     *
     * <p>1. cacheNames/value: 存储方法调用结果的缓存的名称。
     * <ul>
     * <li>名称可用于确定目标缓存(或缓存)，以匹配特定bean定义的限定符值或bean名称。</li>
     * </ul>
     *
     * <p>2. key: 用于动态计算键的Spring表达式(SpEL)表达式。
     * <ul>
     * <li>默认值是“”，这意味着所有方法参数都被视为键，除非配置了自定义密钥生成器。</li>
     * <li>SpEL表达式根据提供以下元数据的专用上下文计算:</li>
     * <li>#root.method: 方法的引用。</li>
     * <li>#root.target: 目标对象的引用。</li>
     * <li>#root.caches: 受影响的缓存的引用。</li>
     * <li>#root.methodName: 方法名。</li>
     * <li>#root.targetClass: 目标类。</li>
     * <li>方法参数可以通过索引访问。例如，第一个参数可以通过 #root.args[0] #p0 #a0 访问。</li>
     * <li>方法参数可以通过名称访问。例如，参数id可以通过 #id 访问。</li>
     * </ul>
     *
     * <p>3. keyGenerator: 自定义 org.springframework.cache.interceptor.KeyGenerator 的bean名称。
     * <ul>
     * <li>与 key 属性互斥。</li>
     * </ul>
     *
     * <p>4. cacheManager: 自定义 org.springframework.cache.CacheManager 的bean名称。如果还没有设置，那么使用 CacheManager 来创建默认的 org.springframework.cache.interceptor.CacheResolver。
     * <ul>
     * <li>与 cacheResolver 属性互斥。</li>
     * </ul>
     *
     * <p>5. cacheResolver: 自定义 org.springframework.cache.interceptor.CacheResolver 的bean名称。
     *
     * <p>6. condition: Spring表达式语言(SpEL)表达式，用于使方法缓存成为有条件的。
     * <ul>
     * <li>默认值是“”，这意味着方法结果总是被缓存。</li>
     * <li>SpEL表达式根据提供以下元数据的专用上下文计算:</li>
     * <li>#root.method: 方法的引用。</li>
     * <li>#root.target: 目标对象的引用。</li>
     * <li>#root.caches: 受影响的缓存的引用。</li>
     * <li>#root.methodName: 方法名。</li>
     * <li>#root.targetClass: 目标类。</li>
     * <li>方法参数可以通过索引访问。例如，第一个参数可以通过 #root.args[0] #p0 #a0 访问。</li>
     * <li>方法参数可以通过名称访问。例如，参数id可以通过 #id 访问。</li>
     * </ul>
     *
     * <p>7. unless: 用于否决方法缓存的Spring表达式(SpEL)表达式。
     * <ul>
     * <li>与 condition 不同，此表达式是在调用方法之后求值的，因此可以引用结果。</li>
     * <li>默认值是“”，这意味着缓存永远不会被否决。当 unless 指定的条件为true时，方法返回值将不会被缓存。</li>
     * <li>SpEL表达式根据提供以下元数据的专用上下文计算:</li>
     * <li>#result: 方法调用结果的引用。对于受支持的包装器(如Optional)， #result引用实际对象，而不是包装器</li>
     * <li>#root.method: 方法的引用。</li>
     * <li>#root.target: 目标对象的引用。</li>
     * <li>#root.caches: 受影响的缓存的引用。</li>
     * <li>#root.methodName: 方法名。</li>
     * <li>#root.targetClass: 目标类。</li>
     * <li>方法参数可以通过索引访问。例如，第一个参数可以通过 #root.args[0] #p0 #a0 访问。</li>
     * <li>方法参数可以通过名称访问。例如，参数id可以通过 #id 访问。</li>
     * </ul>
     *
     * <p>8. sync: 如果多个线程试图加载同一键的值，则同步底层方法的调用。同步会导致一些限制:
     * <ul>
     * <li>unless() 不受支持</li>
     * <li>只能指定一个缓存</li>
     * <li>不能合并其他与缓存相关的操作</li>
     * <li>这实际上是一个提示，您使用的实际缓存提供程序可能不以同步方式支持它。有关实际语义的更多细节，请查看您的提供者文档。</li>
     * </ul>
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"}
//            key = "#root.targetClass.name + '_' + #root.methodName + '(' + #id + ')'"
//            keyGenerator = "myKeyGenerator"
//            condition = "#a0 > 1",
//            unless = "#a0 == 1"
    )
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        return employeeMapper.getEmpById(id);
    }

    /**
     * <code>@CachePut</code>: 表示一个方法(或类上的所有方法)触发缓存put操作的注释。
     * <p>与@Cacheable注释不同，这个注释不会导致跳过建议的方法。相反，它总是调用方法并将其结果存储在关联的缓存中。注意，Java8的可选返回类型是自动处理的，如果存在，它的内容将存储在缓存中。
     * <p>此注释可以用作元注释，以创建具有属性覆盖的自定义复合注释。
     *
     * <p>1. key: 同 @Cacheable。
     * <ul>
     * <li>#result: 方法调用结果的引用。</li>
     * </ul>
     */
    @CachePut(value = "emp",
//            key = "#employee.id"
            key = "#result.id"
    )
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp:" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * <code>@CacheEvict</code>: 表示一个方法(或类上的所有方法)触发缓存驱逐操作的注释。
     * <p>此注释可以用作元注释，以创建具有属性覆盖的自定义复合注释。
     *
     * <p>1. key: 同 @Cacheable。
     * <ul>
     * <li>#result: 方法调用结果的引用，只有在 beforeInvocation() 为false的情况下才能使用。</li>
     * </ul>
     *
     * <p>2. allEntries: 是否删除缓存中的所有条目。
     * <ul>
     * <li>默认情况下，只删除关联键下的值。</li>
     * <li>注意，不允许将此参数设置为true并指定键。</li>
     * </ul>
     *
     * <p>3. beforeInvocation: 是否应在调用方法之前进行驱逐。
     * <ul>
     * <li>如果将此属性设置为true，则无论方法结果如何（即是否引发异常），都会导致驱逐。</li>
     * <li>默认值为false，这意味着在成功调用建议的方法后（即仅当调用未引发异常时），将执行缓存驱逐操作。</li>
     * </ul>
     */
    @CacheEvict(value = "emp",
            key = "#id"
//            allEntries = true
    )
    public void deleteEmp(Integer id) {
//        employeeMapper.deleteEmpById(id);
    }

    /**
     * <code>@Caching</code>用于多个缓存注释(不同或相同类型)的组注释。
     * <p>此注释可以用作元注释，以创建具有属性覆盖的自定义复合注释。
     *
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(cacheNames = "emp", key = "#lastName")
            },
            put = {
                    @CachePut(cacheNames = "emp", key = "#result.id"),
                    @CachePut(cacheNames = "emp", key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }
}
