function intdiv(dividend, divisor) {
    return (dividend / divisor) >> 0
}

console.log(intdiv(3, 2))
console.log(intdiv(-3, 2))
console.log(intdiv(3, -2))
console.log(intdiv(-3, -2))
