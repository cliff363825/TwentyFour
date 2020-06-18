# coding: utf-8

def array_column(l, column_key, index_key=None):
    res = {} if index_key else []
    for v in l:
        if index_key:
            if column_key:
                res[v[index_key]] = v[column_key]
            else:
                res[v[index_key]] = v
        else:
            res.append(v[column_key])
    return res


if __name__ == '__main__':
    records = [
        {
            'id': 2135,
            'first_name': 'John',
            'last_name': 'Doe',
        },
        {
            'id': 3245,
            'first_name': 'Sally',
            'last_name': 'Smith',
        },
        {
            'id': 5342,
            'first_name': 'Jane',
            'last_name': 'Jones',
        },
        {
            'id': 5623,
            'first_name': 'Peter',
            'last_name': 'Doe',
        }
    ]

    print(array_column(records, 'first_name'))
    print(array_column(records, 'first_name', 'id'))
    print(array_column(records, None, 'id'))
