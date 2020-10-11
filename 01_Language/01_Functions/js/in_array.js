function in_array(needle, haystack, strict = false) {
    for (let k in haystack) {
        if (strict && haystack[k] === needle) {
            return true
        } else if (!strict && haystack[k] == needle) {
            return true
        }
    }
    return false
}
