function escapeshellarg(arg) {
    return arg.split("'").map(function (value, index, array) {
        return "'" + value + "'"
    }).join("\\'")
}

console.log('ls ' + escapeshellarg("./"))
console.log('ls ' + escapeshellarg("','"))
