function array_values(arr) {
    if (Array.isArray(arr)) {
        return arr.slice(0);
    }
    let res = []
    let j = 0
    for (let i in arr) {
        res[j++] = arr[i]
    }
    return res;
}

let array = {"size": "XL", "color": "gold"}
console.log(array_values(array));
