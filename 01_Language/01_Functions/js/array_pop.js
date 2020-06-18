function array_pop(arr) {
    if (Array.isArray(arr)) {
        return arr.pop()
    }

    let lastKey;
    for (let i in arr) {
        lastKey = i
    }
    if (lastKey === undefined) {
        return null;
    }

    let res = arr[lastKey]
    delete arr[lastKey]
    return res
}

let stack = ["orange", "banana", "apple", "raspberry"]
console.log(array_pop(stack), stack)

let map = {"k1": "orange", "k2": "banana", "k3": "apple", "k4": "raspberry"}
console.log(array_pop(map), map)
