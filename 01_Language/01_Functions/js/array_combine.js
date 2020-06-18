function array_combine(keys, values) {
    if (!Array.isArray(keys)) {
        keys = Object.values(keys)
    }
    if (!Array.isArray(values)) {
        values = Object.values(values)
    }
    if (keys.length !== values.length) {
        return false;
    }
    let res = {}
    for (let i in keys) {
        res[keys[i]] = values[i]
    }
    return res
}


let a = ['green', 'red', 'yellow']
let b = ['avocado', 'apple', 'banana']
console.log(array_combine(a, b))

let c = {'c1': 'k1', 'c2': 'k2', 'c3': 'k3'}
let d = {'c2': 'v2', 'c3': 'v3', 'c1': 'v1'}
console.log(array_combine(c, d))
