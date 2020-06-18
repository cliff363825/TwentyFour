function foo() {
    let args = arguments
    console.log("Number of arguments: ", args.length);
    if (args.length >= 2) {
        console.log("Second argument is: ", args[1]);
    }

    for (let i in args) {
        console.log(`Argument ${i} is: `, args[i]);
    }
}

foo(1, 2, 3);