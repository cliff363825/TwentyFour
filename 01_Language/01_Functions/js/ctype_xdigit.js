function ctype_xdigit(text) {
    if (text === null || text === undefined || text === '') return false
    for (let i in text) {
        let codePoint = text.charCodeAt(i)
        if ((codePoint >= 48 && codePoint <= 57) ||
            (codePoint >= 65 && codePoint <= 70) ||
            (codePoint >= 97 && codePoint <= 102)) {
            continue
        }
        return false
    }
    return true
}
