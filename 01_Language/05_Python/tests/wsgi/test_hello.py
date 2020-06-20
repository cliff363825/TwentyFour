# coding: utf-8

def application(environ, start_response):
    method = environ['REQUEST_METHOD']
    path = environ['PATH_INFO']
    if method == 'GET' and path == '/':
        # return handle_home(environ, start_response)
        pass
    if method == 'POST' and path == '/signin':
        # return handle_sign(environ, start_response)
        pass
    start_response('200 OK', [('Content-Type', 'text/html')])
    body = '<h1>Hello, %s!</h1>' % (environ['PATH_INFO'][1:] or 'web')
    return [body.encode('utf-8')]
