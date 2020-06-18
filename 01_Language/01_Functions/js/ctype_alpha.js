function ctype_alpha(text) {
    if (text === null || text === undefined || text === '') return false
    for (let i in text) {
        let codePoint = text.charCodeAt(i)
        if ((codePoint >= 65 && codePoint <= 90) ||
            (codePoint >= 97 && codePoint <= 122)) {
            continue
        }
        return false
    }
    return true
}

let strings = ['KjgWZC', 'arf12', '', null]
for (let i in strings) {
    if (ctype_alpha(strings[i])) {
        console.log("The string " + strings[i] + " consists of all letters.");
    } else {
        console.log("The string " + strings[i] + " does not consist of all letters.");
    }
}
