function array_product(arr) {
    let res = 1;
    for (let i in arr) {
        res *= arr[i]
    }
    return res
}

let l = [2, 4, 6, 8]
console.log(array_product(l));
console.log(array_product([]));
