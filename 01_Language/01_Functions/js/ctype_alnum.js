function ctype_alnum(text) {
    if (text === null || text === undefined || text === '') return false
    for (let i in text) {
        let codePoint = text.charCodeAt(i)
        if ((codePoint >= 65 && codePoint <= 90) ||
            (codePoint >= 97 && codePoint <= 122) ||
            (codePoint >= 48 && codePoint <= 57)) {
            continue
        }
        return false
    }
    return true
}

let strings = ['AbCd1zyZ9', 'foo!#$bar', '', null]
for (let i in strings) {
    if (ctype_alnum(strings[i])) {
        console.log("The string " + strings[i] + " consists of all letters or digits.");
    } else {
        console.log("The string " + strings[i] + " does not consist of all letters or digits.");
    }
}
