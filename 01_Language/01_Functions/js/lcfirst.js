function lcfirst(str) {
    if (str === null || str === undefined) {
        return ''
    }
    return str.charAt(0).toLowerCase() + str.substr(1)
}

console.log(lcfirst('HelloWorld'))
console.log(lcfirst('HELLO WORLD'))
console.log(lcfirst(' Abc'))
console.log(lcfirst(null))
