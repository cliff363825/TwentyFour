function array_diff_key(o) {
    let res = {}
    for (let i in o) {
        let isDiff = true
        for (let j = 1; j < arguments.length; j++) {
            for (let k in arguments[j]) {
                if (k === i) {
                    isDiff = false
                    break
                }
            }
            if (!isDiff) {
                break
            }
        }
        if (isDiff) {
            res[i] = o[i]
        }
    }
    return res
}

let array1 = {'blue': 1, 'red': 2, 'green': 3, 'purple': 4};
let array2 = {'green': 5, 'yellow': 7, 'cyan': 8};
console.log(array_diff_key(array1, array2));
