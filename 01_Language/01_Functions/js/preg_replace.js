function preg_replace(pattern, replacement, subject) {
    let p = new RegExp(pattern)
    return subject.replace(p, replacement)
}

console.log(preg_replace(/(\w+) (\d+), (\d+)/, '$11,$3', 'April 15, 2003'))
