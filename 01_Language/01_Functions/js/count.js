function count(arr) {
    if (arr === null || arr === undefined) {
        return 0
    } else if (Array.isArray(arr)) {
        return arr.length
    } else if (typeof arr === "object") {
        return Object.keys(arr).length
    } else {
        return [arr].length
    }
}

let a = [1, 3, 5]
console.log(count(a))

let b = {0: 7, 5: 9, 10: 11}
console.log(count(b))
console.log(count(null))
console.log(count(false))
