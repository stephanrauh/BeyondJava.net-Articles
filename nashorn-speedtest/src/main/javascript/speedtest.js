const esprima = require('../resources/esprima');
const fs = require("fs");
const { performance } = require('perf_hooks');

const readFile = (fname) => fs.readFileSync(fname, 'utf-8');

function parse(code) {
    for (let i = 1; i <= 300; ++i) {
        const start = performance.now();
        const tree = esprima.parse(code);
        const tokens = esprima.tokenize(code);
        const stop = performance.now();
        if (i <= 2 || (i % 10 == 0 && i < 30) || (i % 50 == 0)) {
            console.log('#' + i + ':\t', Math.round(stop - start), 'ms');
        }
    }
}

parse(readFile('./src/main/resources/jquery-3.5.1.min.js'));
