function json_encode(value) {
    return JSON.stringify(value)
}

console.log(json_encode({
    a: 1,
    b: 2,
    c: 3,
    d: 4,
    e: 5,
}))
