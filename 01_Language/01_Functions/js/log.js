function log(arg, base) {
    if (typeof base === 'undefined') {
        return Math.log(arg)
    } else {
        return Math.log(arg) / Math.log(base)
    }
}

console.log(log(1))
console.log(log(2, 2))
