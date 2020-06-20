# coding: utf-8

def fn(self, name='world'):
    print('Hello, %s' % name)


Hello = type('Hello', (object,), {'hello': fn})


# print(Hello)
# hello = Hello()
# print(hello)
# hello.hello()

class ListMetaclass(type):
    def __new__(cls, name, bases, attrs):
        # attrs['add'] = ListMetaclass._add
        attrs['add'] = lambda self, value: self.append(value)
        print(cls, name, bases, attrs)
        return type.__new__(cls, name, bases, attrs)

    @staticmethod
    def _add(self, value):
        self.append(value)


class MyList(list, metaclass=ListMetaclass):
    v1, v2 = None, None
    pass


ml = MyList()
ml.add('123')
print(ml)
