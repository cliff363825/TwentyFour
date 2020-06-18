function floatval(v) {
    return parseFloat(v) || 0.0
}

console.log(floatval("122.34343The"))
console.log(floatval("+122.34343.1"))
console.log(floatval("-122.34343.1"))
console.log(floatval("-.34343.1"))
console.log(floatval("The122.34343"))
