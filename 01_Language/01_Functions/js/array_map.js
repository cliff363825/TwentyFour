function array_map(cb, arr) {
    return arr.map(cb)
}

let a = [1, 2, 3, 4, 5];
let b = array_map(function (value, index, arr) {
    return value * value * value
}, a);
console.log(b)
