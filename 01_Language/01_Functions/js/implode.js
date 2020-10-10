function implode(glue, pieces) {
    return Object.values(pieces).join(glue)
}

let arr = ["lastname", "email", "phone"]
console.log(implode(",", arr))
console.log(implode("hello", []))
