function array_keys(arr) {
    if (arguments.length > 1) {
        return array_keys_extra(...arguments)
    }

    let res = []
    for (let i in arr) {
        res.push(i)
    }
    return res
}

function array_keys_extra(arr, value, strict = false) {
    let res = []
    for (let i in arr) {
        if (strict) {
            if (value === arr[i]) {
                res.push(i)
            }
        } else {
            if (value == arr[i]) {
                res.push(i)
            }
        }
    }
    return res
}

let array = {0: 100, "color": "red"}
console.log(array_keys(array));

let array1 = ["blue", "red", "green", "blue", "blue"]
console.log(array_keys(array1, "blue"));

let array2 = {
    "color": ["blue", "red", "green"],
    "size": ["small", "medium", "large"]
}
console.log(array_keys(array2));
