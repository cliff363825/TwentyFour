function create_function(args, code) {
    try {
        return Function.apply(null, args.split(',').concat(code)) // args + code
    } catch (e) {
        return false
    }
}

let newfunc = create_function('a,b', 'return "ln(a) + ln(b) = " + Math.log(a * b);');
console.log("New anonymous function: " + newfunc);
console.log(newfunc(2, Math.E))
