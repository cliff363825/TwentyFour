function array_walk(arr, callback) {
    if (!(typeof callback === 'function')) {
        return false
    }

    for (let i in arr) {
        if (arguments.length > 2) {
            callback(arr[i], i, arr, arguments[2])
        } else {
            callback(arr[i], i, arr)
        }
    }
    return true;
}

let fruits = {"d": "lemon", "a": "orange", "b": "banana", "c": "apple"}

function test_alter(item1, key, arr, prefix) {
    arr[key] = `${prefix}:${item1}`;
}

function test_print(item2, key) {
    console.log(`${key}: ${item2}`)
}

console.log("Before ...:")
array_walk(fruits, test_print);
array_walk(fruits, test_alter, 'fruit');
console.log("... and after:")
array_walk(fruits, test_print);
