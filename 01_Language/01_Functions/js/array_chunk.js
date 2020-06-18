function array_chunk(input, size, preserveKeys = false) {
    let res = []

    let itemLen = -1
    let item = null
    for (let i in input) {
        if (itemLen === -1 || itemLen === size) {
            item = preserveKeys ? {} : []
            itemLen = 0
            res.push(item)
        }
        item[preserveKeys ? i : itemLen] = input[i]
        itemLen++
    }

    return res
}

let arr = ['a', 'b', 'c', 'd', 'e']
console.log(array_chunk(arr, 2));

let arr1 = {
    'k1': 'v1',
    'k2': 'v2',
    'k3': 'v3',
    'k4': 'v4',
    'k5': 'v5',
}
console.log(array_chunk(arr1, 2, true))
console.log(array_chunk(arr1, 2, false))
