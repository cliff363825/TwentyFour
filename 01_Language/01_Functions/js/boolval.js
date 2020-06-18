function boolval(o) {
    // if (o === 0 || o === 0.0) {
    //     return false
    // }
    // if (o === "" || o === "0") {
    //     return false
    // }
    // if (Array.isArray(o) && o.length === 0) {
    //     return false
    // }
    // if (o === null || o === undefined || o === false) {
    //     return false
    // }
    // return true;
    if (o === "0") {
        return false
    }
    if (Array.isArray(o) && o.length === 0) {
        return false
    }
    return !!o
}

console.log('0:        ' + (boolval(0) ? 'true' : 'false'));
console.log('42:       ' + (boolval(42) ? 'true' : 'false'))
console.log('0.0:      ' + (boolval(0.0) ? 'true' : 'false'))
console.log('4.2:      ' + (boolval(4.2) ? 'true' : 'false'))
console.log('"":       ' + (boolval("") ? 'true' : 'false'))
console.log('" ":      ' + (boolval(" ") ? 'true' : 'false'))
console.log('"string": ' + (boolval("string") ? 'true' : 'false'))
console.log('"true":   ' + (boolval("true") ? 'true' : 'false'))
console.log('"false":  ' + (boolval("false") ? 'true' : 'false'))
console.log('"0":      ' + (boolval("0") ? 'true' : 'false'))
console.log('"1":      ' + (boolval("1") ? 'true' : 'false'))
console.log('[1, 2]:   ' + (boolval([1, 2]) ? 'true' : 'false'))
console.log('[]:       ' + (boolval([]) ? 'true' : 'false'))
console.log('stdClass: ' + (boolval(new Object()) ? 'true' : 'false'))
