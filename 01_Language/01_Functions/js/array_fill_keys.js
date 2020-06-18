function array_fill_keys(keys, value) {
    let res = {}
    keys.forEach(k => {
        res[k] = value
    })
    return res
}

let keys = ['foo', 5, 10, 'bar']
console.log(array_fill_keys(keys, 'banana'))
