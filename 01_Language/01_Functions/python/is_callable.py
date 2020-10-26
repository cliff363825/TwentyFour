# coding: utf-8

def is_callable(var):
    if isinstance(var, str):
        try:
            return callable(globals()[var])
        except KeyError:
            return False

    return callable(var)


if __name__ == '__main__':
    def someFunction():
        pass


    functionVariable = "someFunction"
    print(is_callable(someFunction))
    print(is_callable(functionVariable))


    class someClass(object):
        def someMethod(self):
            pass


    anObject = someClass()
    print(is_callable(anObject.someMethod))
