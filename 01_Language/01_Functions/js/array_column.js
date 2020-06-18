function array_column(input, columnKey, indexKey) {
    let res = indexKey ? {} : []

    for (let i in input) {
        if (indexKey) {
            if (columnKey) {
                res[input[i][indexKey]] = input[i][columnKey]
            } else {
                res[input[i][indexKey]] = input[i]
            }
        } else {
            res[i] = input[i][columnKey]
        }
    }

    return res
}

let records = [
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
];

console.log(array_column(records, 'first_name'));
console.log(array_column(records, 'first_name', 'id'));
console.log(array_column(records, null, 'id'));
