function ctype_digit(text) {
    if (text === null || text === undefined || text === '') return false
    for (let i in text) {
        let codePoint = text.charCodeAt(i)
        if (codePoint >= 48 && codePoint <= 57) {
            continue
        }
        return false
    }
    return true
}
