function ctype_upper(text) {
    if (text === null || text === undefined || text === '') return false
    for (let i in text) {
        let codePoint = text.charCodeAt(i)
        if (codePoint >= 65 && codePoint <= 90) {
            continue
        }
        return false
    }
    return true
}
