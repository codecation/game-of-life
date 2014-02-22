# game-of-life

This is a Game of Life implementation. There are many like it, but this one's in ClojureScript.

See it running at [http://costa-rica.github.io/game-of-life/](http://costa-rica.github.io/game-of-life/).

## Usage

### To run

1. Compile the project by running `lein cljsbuild once`.
2. When compilation completes, visit `src/main.html' in your browser.
3. Watch the glider fly across the board.

### To open a REPL

1. Run `lein repl` in the project's directory.
2. Run `(cemerick.austin.repls/exec)` at the REPL prompt.
3. Visit the URL printed after "Browser-REPL ready @..."

## License

Copyright © 2014

Distributed under the MIT license (see LICENSE for details).
