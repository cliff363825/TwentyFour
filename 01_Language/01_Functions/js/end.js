function end(arr) {
    let res = null
    if (arr === null || arr === undefined) {
        return res
    }
    for (let k in arr) {
        res = arr[k]
    }
    return res
}
