// https://locutus.io/php/var/is_double/index.html
function is_float(v) { // eslint-disable-line camelcase
    //  discuss at: https://locutus.io/php/is_float/
    // original by: Paulo Freitas
    // bugfixed by: Brett Zamir (https://brett-zamir.me)
    // improved by: WebDevHobo (https://webdevhobo.blogspot.com/)
    // improved by: Rafał Kukawski (https://blog.kukawski.pl)
    //      note 1: 1.0 is simplified to 1 before it can be accessed by the function, this makes
    //      note 1: it different from the PHP implementation. We can't fix this unfortunately.
    //   example 1: is_float(186.31)
    //   returns 1: true
    return +v === v && (!isFinite(v) || !!(v % 1))
}

console.log(is_float(27.25))
console.log(is_float('abc'))
console.log(is_float(23))
console.log(is_float(23.5))
console.log(is_float(1e7))
console.log(is_float(true))
