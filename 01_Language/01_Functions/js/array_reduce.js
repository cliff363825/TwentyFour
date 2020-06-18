function array_reduce(arr, cb) {
    if (arguments.length > 2) {
        return arr.reduce(cb, arguments[2])
    } else {
        return arr.reduce(cb)
    }
}

let list = [1, 2, 3, 4, 5]
console.log(array_reduce(list, function (prev, cur, index, arr) {
    return prev + cur
}));
console.log(array_reduce(list, function (prev, cur, index, arr) {
    return prev * cur
}, 10));
console.log(array_reduce([], function (prev, cur, index, arr) {
    return prev + cur
}, "No data to reduce"));
