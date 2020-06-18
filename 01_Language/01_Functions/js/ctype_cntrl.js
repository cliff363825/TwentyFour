function ctype_cntrl(text) {
    if (text === null || text === undefined || text === '') return false
    for (let i in text) {
        let codePoint = text.charCodeAt(i)
        if ((codePoint >= 0 && codePoint <= 31) ||
            codePoint === 127) {
            continue
        }
        return false
    }
    return true
}

let strings = {'string1': "\n\r\t", 'string2': 'arf12', '': ''}
for (let k in strings) {
    if (ctype_cntrl(strings[k])) {
        console.log("The string '" + k + "' consists of all control characters.");
    } else {
        console.log("The string '" + k + "' does not consist of all control characters.");
    }
}
