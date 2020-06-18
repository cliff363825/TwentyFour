function array_search(needle, arr, strict = false) {
    for (let i in arr) {
        if (strict) {
            if (needle === arr[i]) {
                return i;
            }
        } else {
            if (needle == arr[i]) {
                return i;
            }
        }
    }
    return false
}

let list = ["blue", "red", "green", "red"]
console.log(array_search("green", list));
console.log(array_search("red", list));
