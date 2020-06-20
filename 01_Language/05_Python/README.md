# 语言篇之Python

1. 整数和浮点数： 
    1. Python的整数没有大小限制，而某些语言的整数根据其存储长度是有大小限制的，例如Java对32位整数的范围限制在-2147483648-2147483647。
    2. Python的浮点数也没有大小限制，但是超出一定范围就直接表示为inf（无限大）。
2. 字符串和编码： 
    1. 编码： str.encode(charset) => bytes 字符串转字节数组。（相当于Java的`String.getBytes(String charsetName)`）
    2. 解码： bytes.decode(charset) => str 字节数组转字符串。（相当于Java的`new String(byte[] bytes, String charsetName)`）
3. Python集合：
    1. list: list是一种有序的集合，可以随时添加和删除其中的元素。（相当于Java的ArrayList）
    2. tuple: tuple和list非常类似，但是tuple一旦初始化就不能修改。（相当于Java的数组Object[]）
    3. list和tuple是Python内置的有序集合，一个可变，一个不可变。
    4. dict: dict全称dictionary，在其他语言中也称为map，使用键-值（key-value）存储，具有极快的查找速度。(相当于Java的HashMap)
        
        1. dict的key必须是不可变对象，否则将会抛出错误。TypeError: unhashable type: 'xxx'
    5. set: set和dict类似，也是一组key的集合，但不存储value。由于key不能重复，所以，在set中，没有重复的key。(相当于Java的HashSet)
    6. 个人小结：
        ```
        Python集合    特点                  Java集合
        tuple       有序可重复不可变对象  数组(Object[])
        list        有序可重复               ArrayList
        set         无序不可重复              HashSet
        dict        无序，key不能重复          HashMap
        ```
4. 函数的参数：
    
    1. Python函数的默认参数一定要用不可变对象，如果是可变对象，程序运行时会有逻辑错误！
5. 生成器：
    
    1. 要理解generator的工作原理，它是在for循环的过程中不断计算出下一个元素，并在适当的条件结束for循环。对于函数改成的generator来说，遇到return语句或者执行到函数体最后一行语句，就是结束generator的指令，for循环随之结束。
6. 迭代器：
    1. 凡是可作用于for循环的对象都是Iterable类型；
    2. 凡是可作用于next()函数的对象都是Iterator类型，它们表示一个惰性计算的序列；
    3. 集合数据类型如list、dict、str等是Iterable但不是Iterator，不过可以通过iter()函数获得一个Iterator对象。
7. 装饰器：
    
    1. 在面向对象（OOP）的设计模式中，decorator被称为装饰模式。OOP的装饰模式需要通过继承和组合来实现，而Python除了能支持OOP的decorator外，直接从语法层次支持decorator。Python的decorator可以用函数实现，也可以用类实现。
8. Python的魔术方法
    ```
    Magic Method                        何时被调用(例子)                       Explanation
    __new__(cls [,...])                 instance = MyClass(arg1, arg2)          __new__ is called on instance creation
    __init__(self [,...])               instance = MyClass(arg1, arg2)          __init__ is called on instance creation
    __cmp__(self, other)                self == other, self > other, etc.      Called for any comparison
    __pos__(self)                       +self                                   Unary plus sign
    __neg__(self)                       -self                                   Unary minus sign
    __invert__(self)                    ~self                                   Bitwise inversion
    __index__(self)                     x[self]                                 Conversion when object is used as index
    __nonzero__(self)                   bool(self)                              Boolean value of the object
    __getattr__(self, name)             self.name # name doesn't exist          Accessing nonexistent attribute
    __setattr__(self, name, val)        self.name = val                         Assigning to an attribute
    __delattr__(self, name)             del self.name                           Deleting an attribute
    __getattribute__(self, name)        self.name                               Accessing any attribute
    __getitem__(self, key)              self[key]                               Accessing an item using an index
    __setitem__(self, key, val)         self[key] = val                         Assigning to an item using an index
    __delitem__(self, key)              del self[key]                           Deleting an item using an index
    __iter__(self)                      for x in self                           Iteration
    __contains__(self, value)           value in self,value not in self         Membership tests using in
    __call__(self [,...])               self(args)                              "Calling" an instance
    __enter__(self)                     with self as x:                         with statement context managers
    __exit__(self, exc, val, trace)     with self as x:                         with statement context managers
    __getstate__(self)                  pickle.dump(pkl_file, self)             Pickling
    __setstate__(self)                  data = pickle.load(pkl_file)            Pickling
    ```
9. 元类metaclass：
    1. 当我们定义了类以后，就可以根据这个类创建出实例，所以：先定义类，然后创建实例。
    2. 但是如果我们想创建出类呢？那就必须根据metaclass创建出类，所以：先定义metaclass，然后创建类。
    3. 连接起来就是：先定义metaclass，就可以创建类，最后创建实例。
    4. 所以，metaclass允许你创建类或者修改类。换句话说，你可以把类看成是metaclass创建出来的“实例”。
10. 文件读写：
    1. 读文件：
        ```
        # 第一种方法：try...finally...
        try:
            f = open('/path/to/file', mode='r', encoding='utf-8', errors='ignore')
            print(f.read())
        finally:
            if f:
                f.close()
        # 第二种方法：with
        with open('/path/to/file', 'r') as f:
            # print(f.read())
            for line in f:
                print(line)
        ```
    2. file-like Object:
        1. 像open()函数返回的这种有个read()方法的对象，在Python中统称为file-like Object。
        2. 除了file外，还可以是内存的字节流，网络流，自定义流等等。file-like Object不要求从特定类继承，只要写个read()方法就行。
        3. StringIO就是在内存中创建的file-like Object，常用作临时缓冲。
    3. 写文件:
        ```
        # 第一种方法：try...finally...
        try:
            f = open('file', 'w')
            f.write('Hello, world!')
        finally:
            if f:
                f.close()
        # 第二种方法：with
        with open('file', 'w') as f:
            f.write('Hello, world!')
        ```
    4. 在Python中，文件读写是通过open()函数打开的文件对象完成的。使用with语句操作文件IO是个好习惯。
    5. StringIO和BytesIO是在内存中操作str和bytes的方法，使得和读写文件具有一致的接口。
11. ThreadLocal：
    1. 一个ThreadLocal变量虽然是全局变量，但每个线程都只能读写自己线程的独立副本，互不干扰。ThreadLocal解决了参数在一个线程中各个函数之间互相传递的问题。
        ```java
        static final ThreadLocal<T> threadLocal = new ThreadLocal<>()
        ```
12. 常用内建模块：datetime collections base64 struct hashlib hmac itertools contextlib urllib XML HTMLParser
13. 常用第三方模块： Pillow requests chardet psutil
14. 网络编程：
    1. TCP编程：
    2. UDP编程：
        1. UDP的使用与TCP类似，但是不需要建立连接。此外，服务器绑定UDP端口和TCP端口互不冲突，也就是说，UDP的9999端口与TCP的9999端口可以各自绑定。
15. 访问数据库：
    1. 在Python中操作数据库时，要先导入数据库对应的驱动，然后，通过Connection对象和Cursor对象操作数据。
    2. 要确保打开的Connection对象和Cursor对象都正确地被关闭，否则，资源就会泄露。`try:...except:...finally:...`
16. 子类调用父类方法：
    ```python
    class Parent(object):
        def some_method(self, *args, **kwargs):
            pass
    class Child(Parent):
        def some_method(self, *args, **kwargs):
            # 第一种：super(Child, self)返回Parent对象，并调用Parent对象的some_method()方法
            super(Child, self).some_method(*args, **kwargs)
            # 第二种：直接调用Parent类的some_method()方法并传入self参数（即当前Child类对象）
            Parent.some_method(self, *args, **kwargs)
    ```
17. 生成器generator:
    1. next(g) 相当于 g.send(None)
    2. return something  相当于 raise StopIteration(something)
    3. yield something 相当于 return something
    4. yield from generator 相当于 for i in generator: yield i
    5. yield from generator 相当于 await (python3.5+)
18. 协程coroutine: (基于generator实现)
    1. @asyncio.coroutine 相当于 async (python3.5+)
    2. yield from coroutine 相当于 await (python3.5+)
19. 常见问题 
    1. 获取当前路径 os.path.abspath('.')
    2. 获取当前模块的文件名 __file__
    3. 获取命令行参数 sys.argv
    4. 获取当前Python命令的可执行文件路径 sys.executable
20. 模块搜索路径:
    1. 默认情况下，Python解释器会搜索当前目录、所有已安装的内置模块和第三方模块，搜索路径存放在sys模块的path变量中：`sys.path`
    2. 添加搜索目录: sys.path.append(...)
21. and、or的短路逻辑（个人理解）：
    1. and: 表达式从左到右运算，直到第一个False值或者最后一个值结束并返回；
    2. or: 表达式从左到右运算，直到第一个True值或者最后一个值结束并返回。
22. 多进程:
    1. 在Unix/Linux下，可以使用fork()调用实现多进程。
    2. 要实现跨平台的多进程，可以使用multiprocessing模块。
    3. 进程间通信是通过Queue、Pipes等实现的。
