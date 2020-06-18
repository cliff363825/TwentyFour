function ctype_graph(text) {
    if (text === null || text === undefined || text === '') return false
    for (let i in text) {
        let codePoint = text.charCodeAt(i)
        if (codePoint > 32 && codePoint <= 126) {
            continue
        }
        return false
    }
    return true
}
