function gettype(v) {
    return typeof v
}

var data = [1, 1.1, null, {}, 'foo']

for (let d of data) {
    console.log(gettype(d))
}
