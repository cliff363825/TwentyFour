function array_replace(arr) {
    let res = {}
    for (let i = 0; i < arguments.length; i++) {
        for (let j in arguments[i]) {
            res[j] = arguments[i][j]
        }
    }
    return res
}

let base = ["orange", "banana", "apple", "raspberry"]
let replacements = {0: "pineapple", 4: "cherry"}
let replacements2 = {0: "grape"}

console.log(array_replace(base, replacements, replacements2));
