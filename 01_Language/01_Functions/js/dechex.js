function decbin(number) {
    return (parseInt(number) >>> 0).toString(16)
}

console.log(decbin(10))
console.log(decbin(-10))
console.log(decbin(47))
