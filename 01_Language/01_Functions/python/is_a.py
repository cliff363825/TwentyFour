# coding: utf-8

if __name__ == '__main__':
    class WidgetFactory(object):
        def __init__(self):
            self.oink = "moo"

    WF = WidgetFactory()
    if isinstance(WF, WidgetFactory):
        print("Yes")
    else:
        print("No")