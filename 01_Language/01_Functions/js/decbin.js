function decbin(number) {
    return (parseInt(number) >>> 0).toString(2)
}

console.log(decbin(12))
console.log(decbin(-12))
console.log(decbin(26))
