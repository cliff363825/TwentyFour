function ctype_lower(text) {
    if (text === null || text === undefined || text === '') return false
    for (let i in text) {
        let codePoint = text.charCodeAt(i)
        if (codePoint >= 97 && codePoint <= 122) {
            continue
        }
        return false
    }
    return true
}
