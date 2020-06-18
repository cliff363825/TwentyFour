function array_filter(arr, cb) {
    cb = cb || function (e) {
        return !!e
    }
    let res = Array.isArray(arr) ? [] : {}
    for (let i in arr) {
        if (cb(arr[i])) {
            res[i] = arr[i]
        }
    }
    return res
}

let array1 = {'a': 1, 'b': 2, 'c': 3, 'd': 4, 'e': 5}
let array2 = [6, 7, 8, 9, 10, 11, 12]

console.log(array_filter(array1, function (e) {
    return e % 2 === 1
}));
console.log(array_filter(array2, function (e) {
    return e % 2 === 0
}));
