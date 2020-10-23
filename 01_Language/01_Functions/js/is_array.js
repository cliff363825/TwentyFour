function is_array(v) {
    return Object.prototype.toString.call(v) === "[object Array]"
    //return Array.isArray(v)
}

let yes = ['this', 'is', 'an array']
console.log(is_array(yes))

let no = 'this is a string'
console.log(is_array(no))
