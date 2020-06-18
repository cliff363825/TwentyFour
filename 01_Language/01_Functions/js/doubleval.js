function doubleval(v) {
    return parseFloat(v) || 0.0
}

console.log(doubleval("122.34343The"))
console.log(doubleval("+122.34343.1"))
console.log(doubleval("-122.34343.1"))
console.log(doubleval("-.34343.1"))
console.log(doubleval("The122.34343"))
