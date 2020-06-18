function array_intersect_key(arr) {
    let res = {}
    for (let i in arr) {
        let exists = false
        for (let j = 1; j < arguments.length; j++) {
            exists = false
            for (let k in arguments[j]) {
                if (i === k) {
                    exists = true
                    break
                }
            }
            if (!exists) {
                break
            }
        }
        if (exists) {
            res[i] = arr[i]
        }
    }
    return res
}

let array1 = {'blue': 1, 'red': 2, 'green': 3, 'purple': 4}
let array2 = {'green': 5, 'blue': 6, 'yellow': 7, 'cyan': 8}
console.log(array_intersect_key(array1, array2));
