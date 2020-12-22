function preg_match(regex, subject) {
    return new RegExp(regex).test(subject)
}

console.log(preg_match(/php/i, "PHP is the web scripting language of choice."))
