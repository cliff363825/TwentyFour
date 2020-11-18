function key_exists(key, arr) {
    return key in arr
}

let search_array = {'first': 1, 'second': 4, '': false}
console.log(key_exists('first', search_array));
