function array_shift(arr) {
    if (Array.isArray(arr)) {
        return arr.shift()
    }

    let i
    for (i in arr) {
        break
    }
    let res = arr[i]
    delete arr[i]
    return res
}

let stack = ["orange", "banana", "apple", "raspberry"]
console.log(array_shift(stack));

stack = {"k1": "orange", "k2": "banana", 0: "apple", 1: "raspberry"}
console.log(array_shift(stack));
