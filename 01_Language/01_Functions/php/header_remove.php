<?php
header("X-Foo: Bar");
header("X-Bar: Baz");
header_remove("X-Foo");
