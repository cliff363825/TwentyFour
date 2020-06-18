function call_user_func(cb) {
    let args = Array.prototype.slice.call(arguments, 1)
    return cb(...args)
}

function barber(type) {
    console.log(`You wanted a ${type} haircut, no problem`);
}

call_user_func(barber, "mushroom");
call_user_func(barber, "shave");
