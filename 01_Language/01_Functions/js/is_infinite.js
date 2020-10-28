function is_infinite(v) {
    return !isFinite(v) && !isNaN(v)
}

console.log(is_infinite(Math.sqrt(2)))
console.log(is_infinite(Math.log(0)))
console.log(is_infinite(Math.asin(2)))
