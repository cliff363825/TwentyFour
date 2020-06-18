function array_pad(arr, size, value) {
    let res = []
    let destLen = Math.abs(size)
    let srcLen = arr.length
    if (srcLen >= destLen) {
        res = res.concat(arr)
    } else {
        for (let i = 0, n = destLen - srcLen; i < n; i++) {
            if (size > 0 && i === 0) {
                res = res.concat(arr)
            }
            res.push(value)
            if (size < 0 && i === n - 1) {
                res = res.concat(arr)
            }
        }
    }
    return res
}

let input = [12, 10, 9]
console.log(array_pad(input, 5, 0));
console.log(array_pad(input, -7, -1));
console.log(array_pad(input, 2, 999));
