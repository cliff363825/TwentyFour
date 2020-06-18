function array_intersect(arr) {
    let res = {}
    for (let i in arr) {
        let exists = false
        for (let j = 1; j < arguments.length; j++) {
            exists = false
            for (let k in arguments[j]) {
                if (arr[i] === arguments[j][k]) {
                    exists = true;
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

let array1 = ["green", "red", "blue"]
let array2 = ["green", "yellow", "red"]
console.log(array_intersect(array1, array2));
