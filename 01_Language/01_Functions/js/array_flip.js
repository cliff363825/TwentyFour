function array_flip(arr) {
    let res = {}
    for (let i in arr) {
        res[arr[i]] = i;
    }
    return res
}

let input = ["oranges", "apples", "pears"]
console.log(array_flip(input));

let input2 = {a: 1, b: 1, c: 2}
console.log(array_flip(input2))
