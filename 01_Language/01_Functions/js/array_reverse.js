function array_reverse(arr, preserveKeys = false) {
    if (Array.isArray(arr) && !preserveKeys) {
        return arr.slice(0).reverse()
    }
    let res = preserveKeys ? {} : []
    let keys = Object.keys(arr)
    let length = keys.length
    for (let i = length - 1; i >= 0; i--) {
        if (preserveKeys) {
            res[keys[i]] = arr[keys[i]]
        } else {
            res.push(arr[keys[i]])
        }
    }
    return res
}

let input = ["php", 4.0, ["green", "red"]]
console.log(array_reverse(input));
console.log(input)
console.log(array_reverse(input, true));
