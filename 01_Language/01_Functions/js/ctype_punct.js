function ctype_punct(text) {
    if (text === null || text === undefined || text === '') return false
    for (let i in text) {
        let codePoint = text.charCodeAt(i)
        if ((codePoint > 32 && codePoint <= 47) ||
            (codePoint >= 58 && codePoint <= 64) ||
            (codePoint >= 91 && codePoint <= 96) ||
            (codePoint >= 123 && codePoint <= 126)) {
            continue
        }
        return false
    }
    return true
}
