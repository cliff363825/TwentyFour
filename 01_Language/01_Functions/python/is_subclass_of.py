# coding: utf-8

def is_subclass_of(obj, cls):
    if isinstance(obj, type):
        return obj != cls and issubclass(obj, cls)

    return isinstance(obj, cls) and type(obj) != cls


if __name__ == '__main__':
    class WidgetFactory(object):
        pass


    class WidgetFactory_Child(WidgetFactory):
        pass


    WF = WidgetFactory()
    WFC = WidgetFactory_Child()
    if is_subclass_of(WFC, WidgetFactory):
        print("yes, WFC is a subclass of WidgetFactory")
    else:
        print("no, WFC is not a subclass of WidgetFactory")

    if is_subclass_of(WF, WidgetFactory):
        print("yes, WF is a subclass of WidgetFactory")
    else:
        print("no, WF is not a subclass of WidgetFactory")

    if is_subclass_of(WidgetFactory_Child, WidgetFactory):
        print("yes, WidgetFactory_Child is a subclass of WidgetFactory")
    else:
        print("no, WidgetFactory_Child is not a subclass of WidgetFactory")
