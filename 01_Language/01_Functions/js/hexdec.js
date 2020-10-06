function hexdec(hexString) {
    hexString = String(hexString).replace(/[^0-9a-fA-F]/g, "")
    return parseInt(hexString, 16)
}

console.log(hexdec("See"))
console.log(hexdec("ee"))
console.log(hexdec("that"))
console.log(hexdec("a0"))
