function base_convert(number, frombase, tobase) {
    if (frombase < 2 || frombase > 36 || tobase < 2 || tobase > 36) {
        return "";
    }

    return parseInt(String(number), frombase).toString(tobase)
}

console.log(base_convert("a37334", 16, 2));
