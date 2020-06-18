function array_fill(startIndex, num, value) {
    let res = []
    let endIndex = startIndex + num
    for (let i = startIndex; i < endIndex; i++) {
        res[i] = value
    }
    return res
}

let a = array_fill(5, 6, 'banana');
// let b = array_fill(-2, 4, 'pear');
console.log(a, a.length);
// console.log(b, b.length);
