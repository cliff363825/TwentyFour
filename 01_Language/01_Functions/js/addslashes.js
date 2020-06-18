function addslashes(str) {
    return str.replace('\\', '\\\\')
        .replace("'", "\\'")
        .replace('"', '\\"')
        .replace('\0', '\\0')
}

let str = "' \" \\ \0"
console.log(str)
console.log(addslashes(str))
