function hex2bin(data) {
    if (data.length % 2 === 1) {
        return false
    }
    let result = []
    for (let i = 0; i < data.length; i += 2) {
        let h = parseInt(data[i], 16)
        let l = parseInt(data[i + 1], 16)
        if (isNaN(h) || isNaN(l)) {
            return false
        }
        result.push((h << 4) | l)
    }
    return String.fromCharCode.apply(String, result)
}

let hex = hex2bin("6578616d706c65206865782064617461");
console.log(hex);
