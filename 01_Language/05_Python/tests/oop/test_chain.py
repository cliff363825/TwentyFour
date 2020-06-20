# coding: utf-8

class Chain(object):
    def __init__(self, path=''):
        self._path = path

    def __getattr__(self, item):
        return Chain('%s/%s' % (self._path, item))

    def __str__(self):
        return self._path

    def __call__(self, *args, **kwargs):
        return Chain('%s/%s' % (self._path, '/'.join(args)))


# Chain().__getattr__('status').__getattr__('user').__getattr__('timeline').__getattr__('list')
print(Chain().status.user.timeline.list)

# Chain().__getattr__('users').__call__('michael').__getattr__('repos')
print(Chain().users('michael').repos)
