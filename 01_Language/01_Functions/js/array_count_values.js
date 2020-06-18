function array_count_values(arr) {
    let res = {}
    for (let i in arr) {
        let k = arr[i]
        if (res[k] === undefined) {
            res[k] = 0
        }
        res[k]++
    }
    return res
}

let array = [1, "hello", 1, "world", "hello"]
console.log(array_count_values(array));
