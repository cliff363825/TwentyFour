function json_decode(json) {
    return JSON.parse(json)
}

let json = '{"a":1,"b":2,"c":3,"d":4,"e":5}'
console.log(json_decode(json))
