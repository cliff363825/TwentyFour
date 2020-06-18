function explode(delimiter, string) {
    let res = []
    if (typeof string === "string") {
        res = string.split(delimiter)
        if (arguments.length > 2) {
            let limit = arguments[2]
            if (limit < 0) {
                res = res.slice(0, limit)
            } else if (limit === 0) {
                res = [string]
            } else if (limit < res.length) {
                res = res.slice(0, limit - 1).concat(res.slice(limit - 1).join(delimiter))
            }
        }
    }
    return res;
}

console.log(explode(" ", "piece1 piece2 piece3 piece4 piece5 piece6"))
console.log(explode(":", "foo:*:1023:1000::/home/foo:/bin/sh"))
console.log(explode("|", "one|two|three|four", 2))
console.log(explode("|", "one|two|three|four", -1))
console.log(explode("|", "one|two|three|four", 0))
