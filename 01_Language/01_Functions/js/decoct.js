function decbin(number) {
    return (parseInt(number) >>> 0).toString(8)
}

console.log(decbin(15))
console.log(decbin(-15))
console.log(decbin(264))
