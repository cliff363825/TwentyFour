function chunk_split(body, chunklen = 76, end = "\r\n") {
    let res = ""
    let str = ""
    let start = 0;
    while ((str = body.substring(start, start + chunklen)) !== "") {
        res += str + end
        start += chunklen
    }
    return res;
}

const base64_encode = require("./base64_encode")
// format $data using RFC 2045 semantics
let data = "Returns the chunked string.";
let new_string = chunk_split(base64_encode(data) + base64_encode(data) + base64_encode(data) + base64_encode(data) +base64_encode(data));
console.log(new_string);
