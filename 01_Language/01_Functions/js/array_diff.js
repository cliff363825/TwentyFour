function array_diff(arr) {
    let res = {}

    for (let i in arr) {
        let isDiff = true
        for (let j = 1; j < arguments.length; j++) {
            for (let k in arguments[j]) {
                if (arguments[j][k] === arr[i]) {
                    isDiff = false
                    break
                }
            }
            if (!isDiff) {
                break
            }
        }
        if (isDiff) {
            res[i] = arr[i]
        }
    }

    return res
}

let array1 = ["green", "red", "blue", "red"]
let array2 = ["green", "yellow", "red"]
console.log(array_diff(array1, array2));
