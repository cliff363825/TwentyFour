function ctype_space(text) {
    if (text === null || text === undefined || text === '') return false
    for (let i in text) {
        let codePoint = text.charCodeAt(i)
        if (codePoint === 32 || codePoint === 9 || codePoint === 11 ||
            codePoint === 10 || codePoint === 13 || codePoint === 12) {
            continue
        }
        return false
    }
    return true
}
