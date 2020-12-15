function nl2br(string, isXhtml) {
    isXhtml = typeof isXhtml === 'undefined' || !!isXhtml
    return string.replace(/(\r\n|\n\r|\r|\n)/g, isXhtml ? '<br />$1' : '<br>$1')
}

console.log(nl2br("foo isn't\n bar"))
console.log(nl2br("foo isn't\r bar"))
console.log(nl2br("foo isn't\r\n bar"))
